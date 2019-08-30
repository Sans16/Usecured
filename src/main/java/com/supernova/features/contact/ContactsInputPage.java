package com.supernova.features.contact;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.supernova.R;
import com.supernova.model.request.emergencycontact.Contacts;
import com.supernova.model.request.emergencycontact.EmergencyContactRequestBody;
import com.supernova.model.response.EmergencyContactsResponseBody;
import com.supernova.shared.base.base.BaseActivity;
import com.supernova.shared.base.dialog.CustomProgressDialog;
import com.supernova.utils.app.ServerUtils;
import com.supernova.utils.manager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ContactsInputPage extends BaseActivity implements ContactsInputView{

    @BindView(R.id.contactOneNameEt)
    AppCompatEditText contactOneNameEt;

    @BindView(R.id.contactTwoNameEt)
    AppCompatEditText contactTwoNameEt;

    @BindView(R.id.contactThreeNameEt)
    AppCompatEditText contactThreeNameEt;

    @BindView(R.id.contactOneEmailEt)
    AppCompatEditText contactOneEmailEt;

    @BindView(R.id.contactTwoEmailEt)
    AppCompatEditText contactTwoEmailEt;

    @BindView(R.id.contactThreeEmailEt)
    AppCompatEditText contactThreeEmailEt;

    @BindView(R.id.contactOnePhoneEt)
    AppCompatEditText contactOnePhoneEt;

    @BindView(R.id.contactTwoPhoneEt)
    AppCompatEditText contactTwoPhoneEt;

    @BindView(R.id.contactThreePhoneEt)
    AppCompatEditText contactThreePhoneEt;

    private CustomProgressDialog customProgressDialog;
    private ContactsInputPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_input_page);

        initViews();
        initEditTextView();
    }

    private void initViews(){
        customProgressDialog = new CustomProgressDialog(this);
        presenter = new ContactsInputPresenter(this,new ContactsInputInteractor());
    }

    @OnClick(R.id.backBtn)
    public void backBtnClicked(){onBackPressed();}

    @OnClick(R.id.doneBtn)
    public void addEmergencyContact(){
        List<Contacts> addedContact= new ArrayList<>();
        String contactOneName = contactOneNameEt.getText().toString().trim();
        String contactOneEmail = contactOneEmailEt.getText().toString().trim();
        String contactOnePhone = contactOnePhoneEt.getText().toString().trim();

        String contactTwoName = contactTwoNameEt.getText().toString().trim();
        String contactTwoEmail = contactTwoEmailEt.getText().toString().trim();
        String contactTwoPhone = contactTwoPhoneEt.getText().toString().trim();

        String contactThreeName = contactThreeNameEt.getText().toString().trim();
        String contactThreeEmail = contactThreeEmailEt.getText().toString().trim();
        String contactThreePhone= contactThreePhoneEt.getText().toString().trim();

//        if(!isAuthValid(contactOneName,contactOneEmail,contactOnePhone,contactTwoName,contactTwoEmail,
//                        contactTwoPhone,contactThreeName,contactThreeEmail,contactThreePhone)){return;}

        if(ServerUtils.isNetworkUnavailable(this)){showErrorMessage(ServerUtils.NO_NETWORK_ERROR_MESSAGE);}
        String token = "bearer"+SessionManager.getAccessToken();
        addedContact.add(new Contacts("Sheriff","ossyfizy@gmail.com","08023445678"));
        addedContact.add(new Contacts("Femi","ossyfizy007@gmail.com","08034558967"));
        addedContact.add(new Contacts("Keji","osaigbovoemmanuel1@gmail.com","08024567899"));

        EmergencyContactRequestBody data = new EmergencyContactRequestBody(addedContact);

        presenter.proceed(data,token);
    }

    public boolean isAuthValid(String contactOneName,String contactOneEmail,String contactOnePhone,
                                String contactTwoName,String contactTwoEmail,String contactTwoPhone,
                               String contactThreeName,String contactThreeEmail,String contactThreePhone){

        if(TextUtils.isEmpty(contactOneName)){
            showErrorMessage(getString(R.string.full_name_required));
            contactOneNameEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(contactOneEmail)||!Patterns.EMAIL_ADDRESS.matcher(contactOneEmail).matches()){
            showErrorMessage(getString(R.string.invalid_email));
            contactOneEmailEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(contactOnePhone)){
            showErrorMessage(getString(R.string.phone_required));
            contactOnePhoneEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(contactTwoName)){
            showErrorMessage(getString(R.string.full_name_required));
            contactOneNameEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(contactTwoEmail)||!Patterns.EMAIL_ADDRESS.matcher(contactTwoEmail).matches()){
            showErrorMessage(getString(R.string.invalid_email));
            contactOneEmailEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(contactTwoPhone)){
            showErrorMessage(getString(R.string.phone_required));
            contactOnePhoneEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(contactThreeName)){
            showErrorMessage(getString(R.string.full_name_required));
            contactOneNameEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(contactThreeEmail)||!Patterns.EMAIL_ADDRESS.matcher(contactThreeEmail).matches()){
            showErrorMessage(getString(R.string.invalid_email));
            contactOneEmailEt.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(contactThreePhone)){
            showErrorMessage(getString(R.string.phone_required));
            contactOnePhoneEt.requestFocus();
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
        //The block to display the error message;
        showErrorMessage(error);
    }

    @Override
    public void navigateToHomePage(EmergencyContactsResponseBody data) {
        // to perform after the action has been performed will be here
        showSuccessMessageToast(data.getMessage());
    }

    public void initEmailEditTextView(AppCompatEditText editTextView){
        Drawable drawable = getResources().getDrawable(R.drawable.ic_person_yellow_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this,R.color.icon_color));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        editTextView.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }

    public void initPhoneNumberEditTextView(AppCompatEditText editTextView){
        Drawable drawable = getResources().getDrawable(R.drawable.ic_call_red_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this,R.color.icon_color));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        editTextView.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }

    public void initFullNameEditTextView(AppCompatEditText editTextView){
        Drawable drawable = getResources().getDrawable(R.drawable.ic_person_yellow_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(this,R.color.icon_color));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        editTextView.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }

    public void initEditTextView(){
        initAllEmailEditTextView();
        initAllFullNameEditTextView();
        initAllPhoneNumberEditTextView();
    }

    public void initAllEmailEditTextView(){
        initEmailEditTextView(contactOneNameEt);
        initEmailEditTextView(contactTwoNameEt);
        initEmailEditTextView(contactThreeNameEt);
    }

    public void initAllPhoneNumberEditTextView(){
        initPhoneNumberEditTextView(contactOnePhoneEt);
        initPhoneNumberEditTextView(contactTwoPhoneEt);
        initPhoneNumberEditTextView(contactThreePhoneEt);
    }

    public void initAllFullNameEditTextView(){
        initFullNameEditTextView(contactOneNameEt);
        initFullNameEditTextView(contactTwoNameEt);
        initFullNameEditTextView(contactThreeNameEt);
    }
}
