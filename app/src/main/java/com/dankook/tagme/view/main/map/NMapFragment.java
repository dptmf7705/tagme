package com.dankook.tagme.view.main.map;

import android.os.Bundle;

import com.dankook.tagme.view.BaseFragment;
import com.dankook.tagme.R;
import com.dankook.tagme.databinding.FragmentMapBinding;
import com.nhn.android.maps.NMapContext;

public class NMapFragment extends BaseFragment<FragmentMapBinding>{
    private NMapContext mMapContext;
    private static final String CLIENT_ID = "ASIW6dcG016kEqZHnNIJ";// 애플리케이션 클라이언트 아이디 값

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapContext =  new NMapContext(super.getActivity());
        mMapContext.onCreate();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.nMapView.setClientId(CLIENT_ID);// 클라이언트 아이디 설정
        mMapContext.setupMapView(binding.nMapView);
    }

    @Override
    public void onStart(){
        super.onStart();
        mMapContext.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapContext.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapContext.onPause();
    }

    @Override
    public void onStop() {
        mMapContext.onStop();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mMapContext.onDestroy();
        super.onDestroy();
    }
}
