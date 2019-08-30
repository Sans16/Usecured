package com.supernova.shared.base.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.supernova.R;
import com.supernova.shared.base.types.DialogType;

public class AppSuccessErrorDialog {

    private Activity activity;
    private Dialog dialog;

    @DialogType
    private String dialogType;
    private String message;
    private CallbackResult callbackResult;

    public void setOnCallbackResult(final CallbackResult callbackResult) {
        this.callbackResult = callbackResult;
    }

    public AppSuccessErrorDialog(Activity activity, @DialogType String dialogType, String message) {
        this.activity = activity;
        this.dialogType = dialogType;
        this.message = message;
        initDialog();
        bindViews();
    }

    private void initDialog() {
        dialog  = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.success_error_dialog);
        dialog.setOnDismissListener(dialogInterface -> doCloseButtonCallback());
    }

    private void bindViews() {
        AppCompatTextView confirmationButton = dialog.findViewById(R.id.confirmationButtonView);
        AppCompatImageView stateImageView = dialog.findViewById(R.id.stateImageView);
        AppCompatTextView titleTextView = dialog.findViewById(R.id.titleTextView);
        AppCompatTextView messageTextView = dialog.findViewById(R.id.messageTextView);
        View topView = dialog.findViewById(R.id.topView);

        if (dialogType.equals(DialogType.TYPE_SUCCESS)) {
            titleTextView.setText(R.string.yea);
            confirmationButton.setText(R.string.ok);
            stateImageView.setImageResource(R.drawable.ic_done_black_24dp);
            stateImageView.setBackgroundResource(R.drawable.border_rounded_green_bg);
            topView.setBackgroundColor(ContextCompat.getColor(activity, R.color.app_green));
        }

        messageTextView.setText(message);
        confirmationButton.setOnClickListener(v -> {
            dialog.dismiss();
            doCloseButtonCallback();
        });
    }

    private void doCloseButtonCallback() {
        if (callbackResult == null) {
            return;
        }

        callbackResult.onCloseButtonClicked();
    }

    public void showDialog() {
        dialog.show();
        try {
            dialog.getWindow().setAttributes(getLayoutParams());
        } catch (Exception ignored) {}
    }

    @NonNull
    private WindowManager.LayoutParams getLayoutParams() {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        return lp;
    }

    public interface CallbackResult {
        void onCloseButtonClicked();
    }
}
