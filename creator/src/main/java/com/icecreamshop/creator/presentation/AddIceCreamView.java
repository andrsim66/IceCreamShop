package com.icecreamshop.creator.presentation;

/**
 * Created on 10/5/17.
 *
 * @author Andrii S.
 */

public interface AddIceCreamView {

    void showProgress();

    void hideProgress();

    void resetViews();

    void showError(String message);
}
