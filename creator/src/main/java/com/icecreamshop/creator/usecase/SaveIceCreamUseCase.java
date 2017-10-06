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

    private final IceCreamRepository repository;

    public SaveIceCreamUseCase(IceCreamRepository repository) {
        this.repository = repository;
    }

    public void saveIceCream(IceCream iceCream,
                             @NonNull final OnSuccessListener<Void> onSuccessListener,
                             @NonNull final OnFailureListener onFailureListener) {
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
