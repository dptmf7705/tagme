package com.dankook.tagme.view.main.map;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dankook.tagme.data.remote.LoadDataListResponse;
import com.dankook.tagme.data.remote.RetrofitApi;
import com.dankook.tagme.data.remote.RetrofitClient;
import com.dankook.tagme.data.remote.StoreAddrRequest;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.view.BaseFragment;
import com.dankook.tagme.R;
import com.dankook.tagme.databinding.FragmentMapBinding;
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NMapFragment extends BaseFragment<FragmentMapBinding> {

    private final long LOCATION_UPDATE_MIN_TIME = Long.MAX_VALUE;
    private final float LOCATION_UPDATE_MIN_DISTANCE = 5;
    private static final String CLIENT_ID = "ASIW6dcG016kEqZHnNIJ";// 애플리케이션 클라이언트 아이디 값
    private static final int MY_PERMISSION_REQUEST_LOCATION = 1000;
    private NMapContext mMapContext;
    private NMapController mMapController;
    private NMapViewerResourceProvider mMapViewerResourceProvider;
    private NMapOverlayManager mOverlayManager;
    private NMapMyLocationOverlay mMapMyLocationOverlay;
    private NMapLocationManager mMapLocationManager;
    private NMapCompassManager mMapCompassManager;
    private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener =
            new NMapLocationManager.OnLocationChangeListener() { //위치 변경 콜백 인터페이스 정의
                //위치가 변경되면 호출
                @Override
                public boolean onLocationChanged(NMapLocationManager locationManager, NGeoPoint myLocation) {
                    if (mMapController != null) {

                        //mMapController.animateTo(myLocation);//지도 중심을 현재 위치로 이동
                        mMapController.setMapCenter(myLocation);
                        mMapController.setZoomLevel(6);
                        findStoreAddr(myLocation);
                    }
                    return true;
                }

                //정해진 시간 내에 위치 탐색 실패 시 호출
                @Override
                public void onLocationUpdateTimeout(NMapLocationManager locationManager) {
                }
                //현재 위치가 지도 상에 표시할 수 있는 범위를 벗어나는 경우 호출
                @Override
                public void onLocationUnavailableArea(NMapLocationManager locationManager, NGeoPoint myLocation) {
                    stopMyLocation(); //내 위치 찾기 중지 함수 호출
                }
            };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapContext =  new NMapContext(super.getActivity());
        mMapLocationManager = new NMapLocationManager(super.getActivity());
        mMapLocationManager.setUpdateFrequency(LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE);
        mMapContext.onCreate();
        mMapViewerResourceProvider = new NMapViewerResourceProvider(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.nMapView.setClientId(CLIENT_ID);// 클라이언트 아이디 설정
        binding.nMapView.setClickable(true);
        mMapContext.setupMapView(binding.nMapView);
        mMapController = binding.nMapView.getMapController();
        mMapCompassManager = new NMapCompassManager(getActivity());
        binding.nmpaframgmentFabGps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkPermission();
//                if(curLocation != null)
//                    drawMarker(curLocation);
            }
        });
        mMapLocationManager.setOnLocationChangeListener(onMyLocationChangeListener);
    }

    @Override
    public void onStart(){
        super.onStart();
        mMapContext.onStart();
        mOverlayManager = new NMapOverlayManager(getActivity(), binding.nMapView, mMapViewerResourceProvider);
        mMapMyLocationOverlay = mOverlayManager.createMyLocationOverlay(mMapLocationManager, mMapCompassManager);
        binding.nMapView.setBuiltInZoomControls(true, null); // 줌 인/아웃 버튼 생성
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSION_REQUEST_LOCATION) {
            //권한 승인
            startMyLocation();
        }
    }

    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionResult = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
            /* 위치 정보 권한이 없을 때 */
            // 패키지는 안드로이드 어플리케이션의 아이디다.( 어플리케이션 구분자 )
            if (permissionResult == PackageManager.PERMISSION_DENIED) {
                /* 사용자가 위치 정보 권한을 한번이라도 거부한 적이 있는 지 조사한다.
                 * 거부한 이력이 한번이라도 있다면, true를 리턴한다.
                 */
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("권한이 필요합니다.")
                            .setMessage("이 기능을 사용하기 위해서는 위치 정보 권한이 필요합니다. 계속하시겠습니까?")
                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
                                    }
                                }
                            })
                            .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getActivity(), "기능을 취소했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .create()
                            .show();
                } else { //최초로 위치 정보 권한을 요청
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
                }
            } else { //이미 위치 정보 권한이 있는 경우
                //해당 작업 수행
                //경로 찾기 수행
                startMyLocation();
            }
        } else {
            //마시멜로 이하의 버전이라 권한이 별도로 필요 없음
            //경로 찾기 수행
            startMyLocation();
        }
    }


    private void findStoreAddr(NGeoPoint myLocation) {

        Geocoder geocoder = new Geocoder(getContext(), Locale.KOREA);
        List<Address> address;
        String throughFare;
        StoreAddrRequest myThroughFare = new StoreAddrRequest();
        Log.d("addr",""+ myLocation.latitude + " "+ myLocation.longitude);
        try {
            address = geocoder.getFromLocation(myLocation.latitude, myLocation.longitude,1);
            throughFare = address.get(0).getThoroughfare();
            throughFare = throughFare.substring(0, throughFare.length()-1);
            myThroughFare.setStoreAddr(throughFare);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RetrofitApi request = RetrofitClient.getClient().create(RetrofitApi.class);
        Call<LoadDataListResponse<Store>> call = request.getStoreAddr(myThroughFare);

        call.enqueue(new Callback<LoadDataListResponse<Store>>() {
            @Override
            public void onResponse(Call<LoadDataListResponse<Store>> call, Response<LoadDataListResponse<Store>> response) {
                LoadDataListResponse<Store> dataList = response.body();
                drawMarker(dataList);
            }

            @Override
            public void onFailure(Call<LoadDataListResponse<Store>> call, Throwable t) {

            }
        });
    }

    private void drawMarker(LoadDataListResponse<Store> storeList){
        Geocoder coder = new Geocoder(getContext());
        NMapPOIdata poiData = new NMapPOIdata(storeList.getContent().size(), mMapViewerResourceProvider);
        poiData.beginPOIdata(storeList.getContent().size());
        for(int i = 0; i < storeList.getContent().size(); i++){
            try {
                int markerId = NMapPOIflagType.PIN;
                List<Address> address = coder.getFromLocationName(storeList.getContent().get(i).getStoreAddress(), 1);
                poiData.addPOIitem(address.get(0).getLongitude(), address.get(0).getLatitude(), storeList.getContent().get(i).getStoreName(), markerId, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        poiData.endPOIdata();
        NMapPOIdataOverlay poiDataOverlay = mOverlayManager.createPOIdataOverlay(poiData, null);
        poiDataOverlay.showAllPOIdata(0);
    }

    private void startMyLocation() {
        if (mMapLocationManager.isMyLocationEnabled()) { //현재 위치를 탐색 중인지 확인
            if (!binding.nMapView.isAutoRotateEnabled()) { //지도 회전기능 활성화 상태 여부 확인
                //mMapMyLocationOverlay.setCompassHeadingVisible(true); //나침반 각도 표시
                mMapCompassManager.enableCompass(); //나침반 모니터링 시작
                //binding.nMapView.setAutoRotateEnabled(true, false); //지도 회전기능 활성화
            }
            binding.nMapView.invalidate();
        } else { //현재 위치를 탐색 중이 아니면
            Boolean isMyLocationEnabled = mMapLocationManager.enableMyLocation(false); //현재 위치 탐색 시작
            if (!isMyLocationEnabled) { //위치 탐색이 불가능하면
                Toast.makeText(getActivity(), "Please enable a My Location source in system settings", Toast.LENGTH_LONG).show();
                Intent goToSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(goToSettings);
            }
        }
    }
    private void stopMyLocation() {
        mMapLocationManager.disableMyLocation(); //현재 위치 탐색 종료
        if (binding.nMapView.isAutoRotateEnabled()) { //지도 회전기능이 활성화 상태라면
            mMapMyLocationOverlay.setCompassHeadingVisible(false); //나침반 각도표시 제거
            mMapCompassManager.disableCompass(); //나침반 모니터링 종료
            binding.nMapView.setAutoRotateEnabled(false, false); //지도 회전기능 중지
        }
    }
}
