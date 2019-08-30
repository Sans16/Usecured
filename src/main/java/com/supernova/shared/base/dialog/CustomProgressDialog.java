package com.supernova.shared.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.airbnb.lottie.LottieAnimationView;
import com.supernova.R;

public class CustomProgressDialog {
    private Activity activity;
    private Dialog dialog;

    public CustomProgressDialog(Activity activity) {
        this.activity = activity;
        initDialog();
        bindViews();
    }

    private void initDialog() {
        dialog  = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.setContentView(R.layout.custom_progress_dialog);
    }

    private void bindViews() {
        LottieAnimationView lottieAnimationView = dialog.findViewById(R.id.loader_view);
        lottieAnimationView.setAnimation("animations/loader.json");
    }

    public void showDialog() {
        dialog.show();
    }

    //..also create a method which will hide the dialog when some work is done
    public void hideDialog(){
        dialog.dismiss();
    }
}
