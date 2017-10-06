package com.icecreamshop.viewer.data;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.icecreamshop.viewer.model.IceCream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

/**
 * Created on 10/5/17.
 *
 * @author Andrii S.
 */

public class IceCreamRepository {

    private final FirebaseDatabase database;

    private List<IceCream> currentIceCreams;

    public IceCreamRepository(FirebaseDatabase database) {
        this.database = database;
        this.currentIceCreams = new ArrayList<>();
    }

    public Observable<List<IceCream>> getIceCreamList() {
        final DatabaseReference myRef = database.getReference("ice-creams");
        final Query query = myRef.orderByKey();

        return observeValueEvent(query)
                .filter(new Func1<DataSnapshot, Boolean>() {
                    @Override
                    public Boolean call(DataSnapshot dataSnapshot) {
                        return dataSnapshot != null &&
                                dataSnapshot.exists() && dataSnapshot.hasChildren();
                    }
                })
                .map(new Func1<DataSnapshot, Iterable<DataSnapshot>>() {
                    @Override
                    public Iterable<DataSnapshot> call(DataSnapshot dataSnapshot) {
                        return dataSnapshot.getChildren();
                    }
                })
                .map(new Func1<Iterable<DataSnapshot>, List<IceCream>>() {
                    @Override
                    public List<IceCream> call(Iterable<DataSnapshot> dataSnapshots) {
                        Iterator<DataSnapshot> iterator = dataSnapshots.iterator();
                        List<IceCream> iceCreams = new ArrayList<>();
                        while (iterator.hasNext()) {
                            iceCreams.add(iterator.next().getValue(IceCream.class));
                        }
                        return iceCreams;
                    }
                })
                .filter(new Func1<List<IceCream>, Boolean>() {
                    @Override
                    public Boolean call(List<IceCream> iceCreams) {
                        boolean dataChanged =
                                currentIceCreams != null && !currentIceCreams.equals(iceCreams);
                        currentIceCreams = iceCreams;
                        return dataChanged;
                    }
                });
    }

    @NonNull
    private Observable<DataSnapshot> observeValueEvent(final Query query) {
        return Observable.create(new Observable.OnSubscribe<DataSnapshot>() {
            @Override
            public void call(final Subscriber<? super DataSnapshot> subscriber) {
                final ValueEventListener valueEventListener = query.addValueEventListener(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (!subscriber.isUnsubscribed()) {
                                    subscriber.onNext(dataSnapshot);
                                }
                            }

                            @Override
                            public void onCancelled(final DatabaseError error) {
                                if (!subscriber.isUnsubscribed()) {
                                    subscriber.onError(error.toException());
                                }
                            }
                        });

                subscriber.add(Subscriptions.create(new Action0() {
                    @Override
                    public void call() {
                        query.removeEventListener(valueEventListener);
                    }
                }));
            }
        });
    }
}
