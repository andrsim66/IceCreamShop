package com.icecreamshop.viewer.presentation;

import com.icecreamshop.viewer.model.IceCream;

import java.util.List;

/**
 * Created on 10/5/17.
 *
 * @author Andrii S.
 */

public interface ListIceCreamView {

    void showProgress();

    void hideProgress();

    void showIceCreams(List<IceCream> iceCreams);

    void showMessage(String message);

    void showError(String message);
}
