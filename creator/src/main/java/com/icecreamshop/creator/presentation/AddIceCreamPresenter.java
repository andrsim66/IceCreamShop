package com.icecreamshop.creator.presentation;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.icecreamshop.creator.model.IceCream;
import com.icecreamshop.creator.usecase.SaveIceCreamUseCase;
import com.icecreamshop.creator.utils.L;

/**
 * Created on 10/5/17.
 *
 * @author Andrii S.
 */

public class AddIceCreamPresenter implements OnFailureListener, OnSuccessListener<Void> {

    private AddIceCreamView view;
    private SaveIceCreamUseCase useCase;

    public AddIceCreamPresenter(@NonNull AddIceCreamView view,
                                @NonNull SaveIceCreamUseCase useCase) {
        this.view = view;
        this.useCase = useCase;
    }

    @Override
    public void onSuccess(Void aVoid) {
        view.hideProgress();
        view.resetViews();
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        L.e(e.getMessage(), e);
        view.hideProgress();
        view.showError(e.getMessage());
    }

    void destroy() {
        view = null;
    }

    void saveClick(String name, String weight, String color, String flavor, String temperature) {
        view.showProgress();
        if (isValid(name, weight, color, flavor, temperature)) {
            IceCream iceCream = new IceCream();
            iceCream.setName(name);
            iceCream.setWeight(Integer.parseInt(weight));
            iceCream.setColor(color);
            iceCream.setFlavor(flavor);
            iceCream.setTemperature(Integer.parseInt(temperature));

            useCase.saveIceCream(iceCream, this, this);
        } else {
            view.hideProgress();
            view.showError("Enter all fields");
        }
    }

    private boolean isValid(String name, String weight, String color, String flavor,
                            String temperature) {
        return !TextUtils.isEmpty(name)
                && !TextUtils.isEmpty(weight)
                && !TextUtils.isEmpty(color)
                && !TextUtils.isEmpty(flavor)
                && !TextUtils.isEmpty(temperature);
    }
}
