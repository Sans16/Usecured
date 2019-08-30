package com.supernova.features.onboarding.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.supernova.R;
import com.supernova.shared.base.base.BaseFragment;

import butterknife.BindView;

public class onBoardingFragment extends BaseFragment {

    @BindView(R.id.titleHeaderTv)
    AppCompatTextView titleHeaderTv;

    @BindView(R.id.contentTv)
    AppCompatTextView contentTv;

    @BindView(R.id.pageImageView)
    AppCompatImageView pageImageView;


    private static final String PAGE = "page";
    private int page;

    public static onBoardingFragment newInstance(int page){
        onBoardingFragment fragment = new onBoardingFragment();

        Bundle b = new Bundle();
        b.putInt(PAGE,page);

        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!=null){
            page = getArguments().getInt(PAGE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setTag(page);
        initComponents();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_onboarding_layout;
    }

    public void initComponents(){
        switch(page){

            case 0:
                fillComponents(getString(R.string.slider_title_one),getString(R.string.slider_content_one),R.drawable.bus_accident_image);
                break;

            case 1:
                fillComponents(getString(R.string.slider_title_two),getString(R.string.slider_content_two),R.drawable.fire_service_image);
                break;

            case 2:
                fillComponents(getString(R.string.slider_title_three),getString(R.string.slider_content_three),R.drawable.abuse_image);
                break;

        }
    }

    public void fillComponents(String title, String content, int sliderImageView){
        titleHeaderTv.setText(title);
        contentTv.setText(content);
        pageImageView.setImageResource(sliderImageView);
    }
}
