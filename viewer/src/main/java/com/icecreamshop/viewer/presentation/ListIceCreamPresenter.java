package com.icecreamshop.viewer.presentation;

import android.support.annotation.NonNull;

import com.icecreamshop.viewer.model.IceCream;
import com.icecreamshop.viewer.usecase.GetIceCreamListUseCase;
import com.icecreamshop.viewer.usecase.ListUpdateListener;

import java.util.List;

/**
 * Created on 10/5/17.
 *
 * @author Andrii S.
 */

public class ListIceCreamPresenter implements ListUpdateListener {

    private ListIceCreamView view;
    private GetIceCreamListUseCase useCase;

    public ListIceCreamPresenter(@NonNull ListIceCreamView view,
                                 @NonNull GetIceCreamListUseCase useCase) {
        this.view = view;
        this.useCase = useCase;
    }

    @Override
    public void onUpdateSuccess(List<IceCream> iceCreams) {
        view.hideProgress();
        view.showIceCreams(iceCreams);
        view.showMessage("List updated");
    }

    @Override
    public void onUpdateFail(Exception e) {
        view.hideProgress();
        view.showError(e.getMessage());
    }

    void destroy() {
        view = null;
    }

    void loadIceCreams() {
        view.showProgress();
        useCase.getIceCreamList(ListIceCreamPresenter.this);
    }
}
