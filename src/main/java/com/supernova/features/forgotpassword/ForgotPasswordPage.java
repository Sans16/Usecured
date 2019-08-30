package com.supernova.features.forgotpassword;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.supernova.R;
import com.supernova.shared.base.base.BaseActivity;
import com.supernova.shared.base.dialog.CustomProgressDialog;
import com.supernova.utils.app.ServerUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgotPasswordPage extends BaseActivity implements ForgotView {

    @BindView(R.id.emailEt)
    AppCompatEditText emailEt;

    private CustomProgressDialog customProgressDialog;
    private ForgotPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);

        initEmailEditTextView();
        initViews();
    }

    public void initViews(){
        customProgressDialog = new CustomProgressDialog(this);
        presenter = new ForgotPresenter(this, new ForgotInteractor());
    }

    @Override
    public void showProgress(boolean show) {
        if(show){
            customProgressDialog.showDialog();
        }else{
            customProgressDialog.hideDialog();
        }
    }

    @OnClick(R.id.submitBtn)
    public void forgotPasswordBtnClicked(){
        String email = emailEt.getText().toString().trim();

        if(!isAuthValid(email)){return;}
        if(ServerUtils.isNetworkUnavailable(this)){
            showErrorDialog(ServerUtils.NO_NETWORK_ERROR_MESSAGE);
            return;
        }
        presenter.proceed(email);
    }


    public boolean isAuthValid(String email){
        if(TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            showErrorMessage(getString(R.string.invalid_email));
            emailEt.requestFocus();
            return false;
        }
        return true;
    }
    @Override
    public void showErrorDialog(String error) {
        showErrorMessage(error);
    }

    @Override
    public void navigate() {
        showErrorMessage("Kindly check your mail for link to reset your password");
    }

    @OnClick(R.id.backBtn)
    public void onBackBtnClicked(){onBackPressed();}

    public void initEmailEditTextView(){
        Drawable drawable = getResources().getDrawable(R.drawable.ic_person_yellow_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this,R.color.icon_color));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        emailEt.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }
}
