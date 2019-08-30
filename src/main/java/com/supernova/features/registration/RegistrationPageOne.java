package com.supernova.features.registration;
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
import com.supernova.shared.base.dialog.AppSuccessErrorDialog;
import com.supernova.shared.base.dialog.CustomProgressDialog;
import com.supernova.shared.base.types.DialogType;
import com.supernova.utils.app.ServerUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegistrationPageOne extends BaseActivity implements RegisterView {

    @BindView(R.id.firstNameEt)
    AppCompatEditText firstNameEt;

    @BindView(R.id.lastNameEt)
    AppCompatEditText lastNameEt;

    @BindView(R.id.emailEt)
    AppCompatEditText emailEt;

    @BindView(R.id.phoneNumberEt)
    AppCompatEditText phoneNumberEt;

    private CustomProgressDialog customProgressDialog;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page_one);

        initEditTextViews();
        initViews();
    }

    @Override
    public void showProgress(boolean show) {
        if(show){
            customProgressDialog.showDialog();
        }else{
            customProgressDialog.hideDialog();
        }
    }

    public void initEditTextViews(){
        initFirstNameEditTextView();
        initLastNameEditTextView();
        initEmailEditTextView();
        initPhoneNumberEditTextView();
    }

    public void initViews(){
        customProgressDialog = new CustomProgressDialog(this);
        presenter = new RegisterPresenter(this, new RegisterInteractor());
    }

    @Override
    public void showErrorDialog(String message) {
        showErrorMessage(message);
    }

    @OnClick(R.id.registerBtn)
    public void registerUser(){

       String firstName = firstNameEt.getText().toString().trim();
       String lastName = lastNameEt.getText().toString().trim();
       String email = emailEt.getText().toString().trim();
       String phoneNumber = phoneNumberEt.getText().toString().trim();

       if(!isAuthValid(firstName,lastName,email,phoneNumber)){return;}

       if(ServerUtils.isNetworkUnavailable(this)){
           showErrorMessage(ServerUtils.NO_NETWORK_ERROR_MESSAGE);
           return;
       }

       presenter.processRegister(firstName,lastName,email,phoneNumber);
    }

    public boolean isAuthValid(String firstName, String lastName, String email,String phoneNumber){
        if(TextUtils.isEmpty(firstName)){
            showErrorMessage(getString(R.string.first_name_required));
            firstNameEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(lastName)){
            showErrorMessage(getString(R.string.last_name_required));
            lastNameEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            showErrorMessage(getString(R.string.invalid_email));
            emailEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(phoneNumber)){
            showErrorMessage(getString(R.string.phone_number));
            phoneNumberEt.requestFocus();
            return false;
        }
        return true;
    }

    public void initFirstNameEditTextView(){
        Drawable drawable = getResources().getDrawable(R.drawable.ic_person_yellow_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this,R.color.icon_color));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        firstNameEt.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }

    public void initLastNameEditTextView(){
        Drawable drawable = getResources().getDrawable(R.drawable.ic_person_yellow_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this,R.color.icon_color));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        lastNameEt.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }

    public void initEmailEditTextView(){
        Drawable drawable = getResources().getDrawable(R.drawable.ic_email_red_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this,R.color.icon_color));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        emailEt.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }

    public void initPhoneNumberEditTextView(){
        Drawable drawable = getResources().getDrawable(R.drawable.ic_call_red_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this,R.color.icon_color));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        phoneNumberEt.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }

    @OnClick(R.id.backBtn)
    public void onBackBtnClicked(){onBackPressed();}

    @Override
    public void navigateContactPage() {
        showSuccessMessageToast("Registration successfully");
        AppSuccessErrorDialog appSuccessErrorDialog = new AppSuccessErrorDialog(this,
                DialogType.TYPE_SUCCESS,"Kindly check your email for your password");
        appSuccessErrorDialog.showDialog();
        //onBackPressed();
    }
}
