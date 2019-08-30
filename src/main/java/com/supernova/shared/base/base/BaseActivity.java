package com.supernova.shared.base.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    public void showErrorMessage(String message){
        Toasty.error(this,message, Toast.LENGTH_SHORT,true).show();
    }

    public void showPlainMessageToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showSuccessMessageToast(String msg) {
        Toasty.success(this, msg, Toast.LENGTH_SHORT, true).show();
    }

    public void showWarningMessageToast(String msg) {
        Toasty.warning(this, msg, Toast.LENGTH_SHORT, true).show();
    }

    public void showInfoMessageToast(String msg) {
        Toasty.info(this, msg, Toast.LENGTH_SHORT, true).show();
    }


}
