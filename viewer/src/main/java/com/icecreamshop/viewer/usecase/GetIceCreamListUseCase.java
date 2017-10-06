package com.icecreamshop.viewer.usecase;

import android.support.annotation.NonNull;

import com.icecreamshop.viewer.data.IceCreamRepository;
import com.icecreamshop.viewer.model.IceCream;
import com.icecreamshop.viewer.utils.L;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 10/5/17.
 *
 * @author Andrii S.
 */

public class GetIceCreamListUseCase {

    private final IceCreamRepository repository;

    public GetIceCreamListUseCase(IceCreamRepository repository) {
        this.repository = repository;
    }

    public void getIceCreamList(@NonNull final ListUpdateListener updateListener) {
        repository.getIceCreamList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<IceCream>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.e(e.getMessage(), e);
                        updateListener.onUpdateFail(new Exception(e));
                    }

                    @Override
                    public void onNext(List<IceCream> iceCreams) {
                        updateListener.onUpdateSuccess(iceCreams);
                    }
                });
    }
}
