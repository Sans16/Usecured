package com.supernova.features.changepassword;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatEditText;

import com.supernova.R;
import com.supernova.shared.base.base.BaseActivity;
import com.supernova.shared.base.dialog.CustomProgressDialog;
import com.supernova.utils.app.ServerUtils;
import com.supernova.utils.manager.SessionManager;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePasswordPage extends BaseActivity implements ChangePasswordView{

    @BindView(R.id.oldPasswordEt)
    AppCompatEditText oldPasswordEt;

    @BindView(R.id.newPasswordEt)
    AppCompatEditText newPasswordEt;

    @BindView(R.id.confirmPasswordEt)
    AppCompatEditText confirmPasswordEt;

    private CustomProgressDialog customProgressDialog;
    private ChangePasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_page);

        initViews();
    }

    public void initViews(){
        customProgressDialog = new CustomProgressDialog(this);
        presenter = new ChangePasswordPresenter(new ChangePasswordInteractor(),this);
    }

    @OnClick(R.id.submitBtn)
    public void submitBtnClicked(){
        String oldPassword = oldPasswordEt.getText().toString().trim();
        String newPassword = newPasswordEt.getText().toString().trim();
        String confirmPassword = confirmPasswordEt.getText().toString().trim();
        String accessToken = "bearer "+ SessionManager.getAccessToken().trim();

        if(!isAuthValid(oldPassword,newPassword,confirmPassword)){return;}
        if(ServerUtils.isNetworkUnavailable(this)){
            showErrorMessage(ServerUtils.NO_NETWORK_ERROR_MESSAGE);
            return;
        }
        presenter.proceed(oldPassword,newPassword,accessToken);
    }

    public boolean isAuthValid(String oldPassword, String newPassword, String confirmPassword){

        if(TextUtils.isEmpty(oldPassword)){
            showErrorMessage(getString(R.string.old_password_required));
            oldPasswordEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(newPassword)){
            showErrorMessage(getString(R.string.new_password_required));
            newPasswordEt.requestFocus();
            return false;
        }

        if(TextUtils.getTrimmedLength(newPassword)<8){
            showErrorMessage(getString(R.string.minimum_password_characters_required));
            newPasswordEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(confirmPassword)){
            showErrorMessage(getString(R.string.confirm_password_not_match));
            confirmPasswordEt.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void showProgress(boolean state) {
        if(state){
            customProgressDialog.showDialog();
        }else{
            customProgressDialog.hideDialog();
        }
    }

    @Override
    public void showErrorDialog(String error) {
        showErrorMessage(error);
    }

    @Override
    public void navigateBack(String successMessage) {
        showSuccessMessageToast(successMessage);
        onBackPressed();
    }

    @OnClick(R.id.backBtn)
    public void backBtnClicked(){
        onBackPressed();
    }

}
