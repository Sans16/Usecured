package com.supernova.features.onboarding;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.supernova.R;
import com.supernova.features.login.LoginPage;
import com.supernova.features.onboarding.adapter.ScreenSliderPagerAdapter;
import com.supernova.shared.base.base.BaseActivity;
import com.supernova.utils.manager.SessionManager;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OnboardingPage extends BaseActivity implements
        ViewPager.OnPageChangeListener,
        MultiplePermissionsListener {

    @BindView(R.id.pagerView)
    ViewPager pagerView;

    @BindView(R.id.dotIndicator)
    DotsIndicator layoutDot;

    @BindView(R.id.buttonPanelHolder)
    ViewSwitcher buttonPanelHolder;

    private int currentDirection = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_page);

        pagerView.setAdapter(new ScreenSliderPagerAdapter(getSupportFragmentManager()));
        pagerView.addOnPageChangeListener(this);
        layoutDot.setViewPager(pagerView);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {/* Empty method */}

    @Override
    public void onPageSelected(int position) {
        if(position == 2 && currentDirection == 0){
            buttonPanelHolder.showNext();
            currentDirection = 1;
        }

        if(position == 1 && currentDirection == 1){
            buttonPanelHolder.showPrevious();
            currentDirection = 0;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {/* Empty method */}

    @OnClick({R.id.skipButton, R.id.getStartedButton})
    void onSkipButtonClicked() {
      doShowLocationRequestDialog();
    }

    @OnClick(R.id.nextButton)
    void onNextButtonClicked() {
        int currentPosition = pagerView.getCurrentItem();

        if (currentPosition < 2) {
            pagerView.setCurrentItem(currentPosition + 1);
        }
    }

    private void doProceedAction() {
        SessionManager.isSeenOnboardingScreen();
        startActivity(new Intent(OnboardingPage.this, LoginPage.class));
        finish();
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {
        if(report.areAllPermissionsGranted()){
            doProceedAction();
        }
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

    }

    private void doCheckForLocationPermission() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(this)
                .onSameThread()
                .check();
    }

    private void doShowLocationRequestDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Location Permission")
                .setMessage("The app needs to access your location to effieciently work well")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        doCheckForLocationPermission();
                    }
                })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                doProceedAction();
            }
        })
        .create()
        .show();
        ;
    }
}
