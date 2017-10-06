package com.icecreamshop.creator.data;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.icecreamshop.creator.model.IceCream;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created on 10/5/17.
 *
 * @author Andrii S.
 */

public class IceCreamRepository {

    private final FirebaseDatabase database;

    public IceCreamRepository(FirebaseDatabase database) {
        this.database = database;
    }

    public Observable<Void> addIceCream(IceCream iceCream) {
        final DatabaseReference myRef = database.getReference("ice-creams");

        return Observable.just(iceCream)
                .map(new Func1<IceCream, Map<String, Object>>() {
                    @Override
                    public Map<String, Object> call(IceCream iceCream) {
                        return iceCream.toMap();
                    }
                })
                .map(new Func1<Map<String, Object>, Map<String, Object>>() {
                    @Override
                    public Map<String, Object> call(Map<String, Object> iceCreamMap) {
                        String key = myRef
                                .child(iceCreamMap.get("name").toString()).push().getKey();
                        Map<String, Object> iceCreamJson = new HashMap<>();
                        iceCreamJson.put(key, iceCreamMap);
                        return iceCreamJson;
                    }
                })
                .map(new Func1<Map<String, Object>, Void>() {
                    @Override
                    public Void call(Map<String, Object> stringObjectMap) {
                        Task<Void> task = myRef.updateChildren(stringObjectMap);

                        while (!task.isComplete()) { // not very good solution
                        }

                        return task.getResult();
                    }
                });
    }
}
