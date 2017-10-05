package com.icecreamshop.creator.presentation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.icecreamshop.creator.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements AddIceCreamView {

    @BindView(R.id.et_name) EditText etName;
    @BindView(R.id.et_weight) EditText etWeight;
    @BindView(R.id.et_color) EditText etColor;
    @BindView(R.id.et_flavor) EditText etFlavor;
    @BindView(R.id.et_temperature) EditText etTemperature;

    private ProgressDialog progress;

    private AddIceCreamPresenter presenter;

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
    public void resetViews() {
        etName.setText("");
        etWeight.setText("");
        etColor.setText("");
        etFlavor.setText("");
        etTemperature.setText("");
    }

    @Override
    public void showError(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.b_save)
    void onSaveClick() {
        presenter.saveClick(
                etName.getText().toString(),
                etWeight.getText().toString(),
                etColor.getText().toString(),
                etFlavor.getText().toString(),
                etTemperature.getText().toString());
    }

    private void init() {
        progress = new ProgressDialog(MainActivity.this);
        progress.setCancelable(false);
        progress.setIndeterminate(true);

        presenter = new AddIceCreamPresenter(MainActivity.this);
    }
}
