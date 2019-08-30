package com.supernova.shared.base.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
    }

    protected abstract int getFragmentLayout();

    private void injectViews(final View view){
        ButterKnife.bind(this,view);
    }

    public void showErrorMessageToast(String msg) {
        if (getActivity() != null) {
            Toasty.error(getActivity(), msg, Toast.LENGTH_SHORT, true).show();
        }
    }

    public void showSuccessMessageToast(String msg) {
        if (getActivity() != null) {
            Toasty.success(getActivity(), msg, Toast.LENGTH_SHORT, true).show();
        }
    }

    public void showInfoMessageToast(String msg) {
        if (getActivity() != null) {
            Toasty.info(getActivity(), msg, Toast.LENGTH_SHORT, true).show();
        }
    }
}
