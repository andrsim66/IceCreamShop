package com.icecreamshop.viewer.presentation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.icecreamshop.viewer.App;
import com.icecreamshop.viewer.R;
import com.icecreamshop.viewer.di.compontents.DaggerActivityComponent;
import com.icecreamshop.viewer.di.modules.ActivityModule;
import com.icecreamshop.viewer.model.IceCream;
import com.icecreamshop.viewer.presentation.adapter.IceCreamListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ListIceCreamView {

    @BindView(R.id.rv_ice_creams) RecyclerView rvIceCreams;

    private ProgressDialog progress;

    @Inject ListIceCreamPresenter presenter;

    private IceCreamListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);

        init();
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        presenter = null;
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        hideProgress();
        progress.show();
    }

    @Override
    public void hideProgress() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }

    @Override
    public void showIceCreams(List<IceCream> iceCreams) {
        adapter.setIceCreams(iceCreams);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        showMessage(message);
    }

    private void init() {
        progress = new ProgressDialog(MainActivity.this);
        progress.setCancelable(false);
        progress.setIndeterminate(true);

        LinearLayoutManager categoriesLayoutManager =
                new LinearLayoutManager(MainActivity.this);
        rvIceCreams.setLayoutManager(categoriesLayoutManager);

        adapter = new IceCreamListAdapter(MainActivity.this);
        rvIceCreams.setAdapter(adapter);

        DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(new ActivityModule(MainActivity.this))
                .build().inject(MainActivity.this);

        presenter.loadIceCreams();
    }
}
