package com.icecreamshop.viewer.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.icecreamshop.viewer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 10/5/17.
 *
 * @author Andrii S.
 */

public class IceCreamItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_item_name) TextView tvName;
    @BindView(R.id.tv_item_flavor) TextView tvFlavor;
    @BindView(R.id.tv_item_temperature) TextView tvTemperature;
    @BindView(R.id.tv_item_color) TextView tvColor;

    IceCreamItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
