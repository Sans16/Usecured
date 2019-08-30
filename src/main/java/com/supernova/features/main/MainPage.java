package com.supernova.features.main;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.supernova.R;
import com.supernova.features.contact.ContactsInputPage;
import com.supernova.features.setting.SettingPage;
import com.supernova.model.response.EmergencyResponseBody;
import com.supernova.shared.base.base.BaseActivity;
import com.supernova.shared.base.dialog.CustomProgressDialog;
import com.supernova.utils.manager.SessionManager;

import butterknife.OnClick;

public class MainPage extends BaseActivity implements MainPageView{

    private double latitude,longitude;
    private MainPagePresenter presenter;
    private CustomProgressDialog customProgressDialog;

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 123;
    private AppCompatImageView panicBtn;
    int numberIncrement = 0;
    private Activity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //    checkIfUserSeenOnboarding();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mContext = this;
        processGetUserLastLocation();
        initViews();

//        panicBtn = findViewById(R.id.imageView2);
//
//        if(checkPermission(Manifest.permission.SEND_SMS)){
//            //enable button to send
//        }else{
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
//                    SEND_SMS_PERMISSION_REQUEST_CODE);
//            // show error message for denied permission
//        }



  //      panicBtn.setOnClickListener(new View.OnClickListener() {
  //          @Override
  //          public void onClick(View view) {
 //               Log.i("Tag","panic button clicked");
//                for(int position = 0; position<3;position++){
//                    String[] numbers = {"08023119233","08023119345","08188453531"};
//                    sendPanic(numbers[numberIncrement],"I need money");
//                    numberIncrement+=1;
//                }

 //           }
  //      });
    }

//    private void checkIfUserSeenOnboarding() {
//        if (!SessionManager.isSeenOnboardingScreen()) {
//            startActivity(new Intent(MainPage.this, OnboardingPage.class));
//            finish();
//        }
//    }

    //    public void sendPanic(String number,String panicMessage){
//        // check for empty phone and number and message
//        if(checkPermission(Manifest.permission.SEND_SMS)){
//            SmsManager smsManager = SmsManager.getDefault();
//            smsManager.sendTextMessage(number,null,panicMessage,null,null);
//        }else{
//           // Toast Error message
//        }
//    }

    @OnClick(R.id.cardViewOne)
    public void firstCardClicked(){ startActivity(new Intent(this, ContactsInputPage.class));}

    @OnClick(R.id.cardViewTwo)
    public void secondCardClicked(){startActivity(new Intent(this, ContactsInputPage.class));}

    @OnClick(R.id.cardViewThree)
    public void thirdCardClicked(){startActivity(new Intent(this, ContactsInputPage.class));}

    @OnClick(R.id.settingsFloatingBtn)
    public void backClicked(){
        onBackPressed();
    }

    private boolean checkPermission(String permission){
        int checkPermission = ContextCompat.checkSelfPermission(this,permission);
        return (checkPermission == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == SEND_SMS_PERMISSION_REQUEST_CODE){
                if(grantResults.length>0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    // allow the button to send
                    // like toast a success message
                }
        }
    }

    private void processGetUserLastLocation() {
        FusedLocationProviderClient fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(mContext);

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            showErrorMessage("Permission not granted");
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(mContext, location -> {
            // Got last known location. In some rare situations this can be null.

            if (location != null) {
                // Logic to handle location object
                latitude =location.getLatitude();
                longitude = location.getLongitude();

                //showSuccessMessageToast("processgetUserLastLocation: "+location.getLatitude());
                Log.i("TAG", "processgetUserLastLocation: "+location.getLatitude());
                Log.i("TAG", "processgetUserLastLocation: "+location.getLatitude());
                Log.i("TAG", "processgetUserLastLocation: "+location.getLongitude());
            }
        });
    }

    public void initViews(){
        customProgressDialog = new CustomProgressDialog(this);
        presenter = new MainPagePresenter(this,new MainPageInteractor());
    }

    @OnClick(R.id.imageView2)
    public void requestForEmergency(){
        double q = latitude+longitude;
        String accessToken = "bearer "+ SessionManager.getAccessToken();
        presenter.emergencyRequested(accessToken,q);
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
    public void showErrorDialog(String message) {
        showErrorMessage(message);
    }

    @Override
    public void showSuccessInfo(EmergencyResponseBody data) {
        String message = data.getMessage();
        showSuccessMessageToast(message);
    }

    @OnClick(R.id.settingsFloatingBtn)
    public void navigateToSettingsPage(){
        startActivity(new Intent(mContext, SettingPage.class));
    }

    @OnClick(R.id.newsFloatingBtn)
    public void navigateToNewsPage(){
        Log.i("TAG","news btn clicked");
        showInfoMessageToast("Feature coming soon");
        startActivity(new Intent(this, SettingPage.class));
    }

    @OnClick(R.id.tipsFloatingBtn)
    public void navigateToTipsPage(){showInfoMessageToast("Feature coming soon");}

    @OnClick(R.id.hotlinesFloatingBtn)
    public void navigatePageToHotLines(){showInfoMessageToast("Feature coming soon");}
}
