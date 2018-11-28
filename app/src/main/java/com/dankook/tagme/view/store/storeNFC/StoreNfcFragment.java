package com.dankook.tagme.view.store.storeNFC;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.dankook.tagme.R;
import com.dankook.tagme.databinding.FragmentStoreNfcBinding;
import com.dankook.tagme.utils.Constant;
import com.dankook.tagme.view.BaseFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class StoreNfcFragment extends BaseFragment<FragmentStoreNfcBinding>{

    public static final String EXTRA_STORE_KEY = "EXTRA_STORE_KEY";
    public static final String EXTRA_TABLE_NUMBER = "EXTRA_TABLE_NUMBER";
    public static final String EXTRA_IS_DYNAMIC_LINK = "EXTRA_IS_DYNAMIC_LINK";

    private int storeKey;
    private int tableNumber;
    private boolean isDynamicLink;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_store_nfc;
    }

    public static StoreNfcFragment newInstance(int storeKey, int tableNumber, boolean isDynamicLink){

        StoreNfcFragment fragment = new StoreNfcFragment();

        Bundle args = new Bundle();
        args.putInt(EXTRA_STORE_KEY, storeKey);
        args.putInt(EXTRA_TABLE_NUMBER, tableNumber);
        args.putBoolean(EXTRA_IS_DYNAMIC_LINK, isDynamicLink);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args =  getArguments();

        if (args != null) {
            storeKey = args.getInt(EXTRA_STORE_KEY);
            tableNumber = args.getInt(EXTRA_TABLE_NUMBER);
            isDynamicLink = args.getBoolean(EXTRA_IS_DYNAMIC_LINK);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {

        // 태그 접속이랑 일반 접속 구분
        if(isDynamicLink) {
            // 태그 접속
            binding.tvTableNumber.setText(""+tableNumber);
        } else {
            // 일반 접속
            binding.btnDone.setOnClickListener(v -> {
                int tableNumber = Integer.parseInt(binding.etTableNumber.getText().toString());
                makeDynamicLink(getStoreDeepLink(tableNumber));
            });
        }
    }


    /**
     * 현재 가게의 다이나믹 링크 생성 */
    private void makeDynamicLink(Uri uri){

        FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(uri)
                // Firebase 콘솔에 있는 고유값
                .setDynamicLinkDomain("tagme.page.link")
                .setAndroidParameters(
                        new DynamicLink.AndroidParameters.Builder()
                                .build())
                .buildShortDynamicLink()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if(task.isSuccessful()){
                            Uri shortLink = task.getResult().getShortLink();
                            try {
                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT, shortLink.toString());
                                sendIntent.setType("text/plain");
                                startActivity(Intent.createChooser(sendIntent, "Share"));
                            } catch (ActivityNotFoundException exception) {

                            }
                        } else {
                            Log.w("Dynamic Link", task.toString());
                        }
                    }
                });
    }

    private Uri getStoreDeepLink(int tableNumber){

        StringBuilder sb = new StringBuilder();
        sb.append("https://tagme.com/");
        sb.append(Constant.DYNAMIC_SEGMENT_STORE);
        sb.append("?");
        sb.append(Constant.DYNAMIC_STORE_KEY);
        sb.append("=");
        sb.append(storeKey);
        sb.append("&");
        sb.append(Constant.DYNAMIC_TABLE_NUMBER);
        sb.append("=");
        sb.append(tableNumber);

        return Uri.parse(sb.toString());
    }
}
