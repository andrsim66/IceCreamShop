package com.icecreamshop.viewer.usecase;

import com.icecreamshop.viewer.model.IceCream;

import java.util.List;

/**
 * Created on 10/5/17.
 *
 * @author Andrii S.
 */

public interface ListUpdateListener {

    void onUpdateSuccess(List<IceCream> iceCreams);

    void onUpdateFail(Exception e);
}
