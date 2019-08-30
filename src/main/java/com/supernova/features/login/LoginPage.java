package com.supernova.features.login;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.airbnb.lottie.L;
import com.supernova.R;
import com.supernova.features.forgotpassword.ForgotPasswordPage;
import com.supernova.features.main.MainPage;
import com.supernova.features.onboarding.OnboardingPage;
import com.supernova.features.registration.RegistrationPageOne;
import com.supernova.model.request.LoginRequestBody;
import com.supernova.model.response.login.LoginResponseBody;
import com.supernova.shared.base.base.BaseActivity;
import com.supernova.shared.base.dialog.AppSuccessErrorDialog;
import com.supernova.shared.base.dialog.CustomProgressDialog;
import com.supernova.shared.base.types.DialogType;
import com.supernova.utils.app.ServerUtils;
import com.supernova.utils.manager.SessionManager;

import butterknife.BindView;
import butterknife.OnClick;

import static com.supernova.utils.manager.SessionManager.setAccessToken;
import static com.supernova.utils.manager.SessionManager.setSeenOnboardingScreen;

public class LoginPage extends BaseActivity implements LoginView {

    @BindView(R.id.emailEt)
    AppCompatEditText emailEt;

    @BindView(R.id.passwordEt)
    AppCompatEditText passwordEt;

    private LoginPresenter presenter;
    private CustomProgressDialog customProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkIfUserSeenOnboarding();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        initEditTextView();
        initViews();
    }


    public void initViews(){
        customProgressDialog = new CustomProgressDialog(this);
        presenter = new LoginPresenter(this,new LoginInteractor());
    }

    public boolean isAuthValid(String email, String password){
        if(TextUtils.isEmpty(email)||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            showErrorMessage(getString(R.string.invalid_email));
            emailEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(password)){
            showErrorMessage(getString(R.string.password_required));
            passwordEt.requestFocus();
            return false;
        }
        return true;
    }

    @OnClick(R.id.signInBtn)
    public void signInBtnClicked(){
        String email = emailEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();

        if(!isAuthValid(email,password)){return;}
        if(ServerUtils.isNetworkUnavailable(this)){
            showErrorMessage(ServerUtils.NO_NETWORK_ERROR_MESSAGE);
            return;
        }
        presenter.proceed(email,password);
    }

    @Override
    public void showProgress(boolean show) {
        if(show){
            customProgressDialog.showDialog();
        }else{
            customProgressDialog.hideDialog();
        }
    }

    @Override
    public void showErrorDialog(String error) {
        AppSuccessErrorDialog appSuccessErrorDialog = new AppSuccessErrorDialog(this,
                DialogType.TYPE_ERROR, error);
        appSuccessErrorDialog.showDialog();
        //showErrorMessage(error);
    }

    @Override
    public void navigateHomePage(LoginResponseBody data) {
        SessionManager.setAccessToken(data.getAccessToken());
        startActivity(new Intent(LoginPage.this,MainPage.class));
       // showSuccessMessageToast(data.getAccessToken());
    }

    @OnClick(R.id.linerContainerTwo)
    public void navigateToRegPage(){
        startActivity(new Intent(LoginPage.this, RegistrationPageOne.class));
    }

    @OnClick(R.id.forgotPasswordTv)
    public void navigateToForgotPasswordPage(){
        startActivity(new Intent(LoginPage.this, ForgotPasswordPage.class));
    }

    public void initEditTextView(){
        initEmailEditTextView();
        initPasswordEditTextView();
    }

    public void initEmailEditTextView(){
        Drawable drawable = getResources().getDrawable(R.drawable.ic_person_yellow_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this,R.color.icon_color));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        emailEt.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }

    public void initPasswordEditTextView(){
        Drawable drawable = getResources().getDrawable(R.drawable.ic_lock_red_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this,R.color.icon_color));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        passwordEt.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }

    private void checkIfUserSeenOnboarding() {
        if (!SessionManager.isSeenOnboardingScreen()) {
            setSeenOnboardingScreen();
            startActivity(new Intent(this, OnboardingPage.class));
            finish();
        }
    }
}
