package com.supernova.features.setting;

import android.content.Intent;
import android.os.Bundle;
import com.supernova.R;
import com.supernova.features.changepassword.ChangePasswordPage;
import com.supernova.shared.base.base.BaseActivity;

import butterknife.OnClick;

public class SettingPage extends BaseActivity {
    private boolean smsChargesChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        // check if the smsCharges setting has been checked or not.
    }

    @OnClick({R.id.backTv,R.id.backBtn})
    public void onBackClicked(){
        onBackPressed();
    }

    @OnClick(R.id.cardContainerOne)
    public void navigateToProfilePage(){
        showInfoMessageToast("Working on the activity Page");
    }

    @OnClick(R.id.cardContainerTwo)
    public void navigateToChangePasswordPage(){
        startActivity(new Intent(SettingPage.this, ChangePasswordPage.class));
    }


    @OnClick(R.id.cardContainerFour)
    public void navigateToAboutPage(){
        showInfoMessageToast("Working on the activity Page");
    }
}
