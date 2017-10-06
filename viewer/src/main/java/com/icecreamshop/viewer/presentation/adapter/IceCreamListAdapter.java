package com.icecreamshop.viewer.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icecreamshop.viewer.R;
import com.icecreamshop.viewer.model.IceCream;

import java.util.Collections;
import java.util.List;

/**
 * Created on 10/5/17.
 *
 * @author Andrii S.
 */

public class IceCreamListAdapter extends RecyclerView.Adapter<IceCreamItemViewHolder> {

    private List<IceCream> iceCreams;
    private LayoutInflater layoutInflater;

    public IceCreamListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.iceCreams = Collections.emptyList();
    }

    @Override
    public IceCreamItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_ice_cream, parent, false);
        return new IceCreamItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IceCreamItemViewHolder holder, int position) {
        IceCream iceCream = iceCreams.get(position);
        holder.tvName.setText(iceCream.getName());
        holder.tvFlavor.setText(iceCream.getFlavor());
        holder.tvColor.setText(iceCream.getColor());
        holder.tvTemperature.setText(String.format("%s^C", iceCream.getTemperature()));
    }

    @Override
    public int getItemCount() {
        return iceCreams != null ? iceCreams.size() : 0;
    }

    public void setIceCreams(List<IceCream> iceCreams) {
        validateList(iceCreams);
        this.iceCreams = iceCreams;
        notifyDataSetChanged();
    }

    private void validateList(List<IceCream> iceCreams) {
        if (iceCreams == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
}