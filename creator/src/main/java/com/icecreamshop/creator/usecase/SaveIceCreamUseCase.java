package com.icecreamshop.creator.usecase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.icecreamshop.creator.data.IceCreamRepository;
import com.icecreamshop.creator.model.IceCream;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 10/5/17.
 *
 * @author Andrii S.
 */

public class SaveIceCreamUseCase {

    private IceCreamRepository repository;

    private OnSuccessListener<Void> onSuccessListener;
    private OnFailureListener onFailureListener;

    public SaveIceCreamUseCase(@NonNull OnSuccessListener<Void> onSuccessListener,
                               @NonNull OnFailureListener onFailureListener) {
        this.onSuccessListener = onSuccessListener;
        this.onFailureListener = onFailureListener;

        this.repository = new IceCreamRepository();
    }

    public void saveIceCream(IceCream iceCream) {
        repository.addIceCream(iceCream)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onFailureListener.onFailure(new Exception(e));
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        onSuccessListener.onSuccess(aVoid);
                    }
                });
    }
}
