package com.practice.somap;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.practice.somap.domain.CarApi;
import com.practice.somap.domain.Data;
import com.practice.somap.domain.ZoneApi;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, ZoneApi.Callback{

    private GoogleMap mMap;
    private ProgressBar progressBar;
    private ProgressBar progressBar2;
    private ClusterManager<MarkerItem> clusterManager;

    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    private Map<String, LatLng> citys = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // 맵 세팅
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        citys.put("서울",new LatLng(37.516038, 127.019783));
        citys.put("부산",new LatLng(35.168359, 129.176855));
        citys.put("광주",new LatLng(35.154507, 126.845171));

        // 리스트 세팅
        progressBar2 = findViewById(R.id.progressBar2);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try{
            ZoneApi.getZones(this);
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void init(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // 1. 클러스터 매니저 초기화
        clusterManager = new ClusterManager<MarkerItem>(this, mMap);

        // * 클러스터 그룹 클릭시 처리 : 그룹 내에 있는 목록을 갱신처리...
        clusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MarkerItem>() {
            @Override
            public boolean onClusterClick(Cluster<MarkerItem> cluster) {
                Toast.makeText(MapsActivity.this
                        , "마커좌표="+cluster.getPosition()
                        ,Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        // * 단일 마커 클릭 시 처리 : 아래 목록에 자동차 리스팅
        clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<MarkerItem>() {
            @Override
            public boolean onClusterItemClick(MarkerItem markerItem) {
                Log.e("onClusterItemClick", markerItem.zone_id);
                setCarList(markerItem.zone_id);
                return false;
            }
        });

        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);

        setClusterOnMap();
    }

    /**
     * 마커 클릭시 자동차 목록 갱신하기
     * @param zone_id
     */
    private void setCarList(String zone_id){
        try {
            progressBar2.setVisibility(View.VISIBLE);
            CarApi.getCars(zone_id, () -> {
                adapter.setDataAndRefresh(CarApi.data);
                progressBar2.setVisibility(View.GONE);
            });
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    /**
     * 지도에 마커 클러스터 처리하기
     */
    private void setClusterOnMap(){
        clusterManager.clearItems();
        Observable<Data> observable = Observable.create(emitter -> {
            for(Data data :ZoneApi.data){
                emitter.onNext(data);
            }
            emitter.onComplete();
        });
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data ->{
                    // 2. 클러스터 매니저에 마커를 등록
                    MarkerItem item = new MarkerItem(data.getLat(), data.getLng());
                    item.zone_id = data.getZone_id();
                    clusterManager.addItem(item);
                    // LatLng marker = new LatLng(data.getLat(), data.getLng());
                    // mMap.addMarker(new MarkerOptions().position(marker).title(data.getZone_name()));
                }, e->{}, ()->{
                    LatLng coord;
                    if(currentArea != null && !"".equals(currentArea)) {
                        coord = citys.get(currentArea);
                    }else{
                        coord = citys.get("서울");
                    }
                    mMap.addMarker(new MarkerOptions().position(coord).title("City : " + currentArea));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coord, 11));
                });
    }

    public void setAll(View view){

    }
    private String currentArea = "";
    public void setSeoul(View view){
        currentArea = "서울";
        try {
            ZoneApi.getZones(currentArea, () -> {
                setClusterOnMap();
            });
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    public void setBusan(View view){
        currentArea = "부산";
        try {
            ZoneApi.getZones(currentArea, () -> {
                setClusterOnMap();
            });
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    public void setGwangju(View view){
        currentArea = "광주";
        try {
            ZoneApi.getZones(currentArea, () -> {
                setClusterOnMap();
            });
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    class MarkerItem implements ClusterItem {
        private LatLng position;
        public String zone_id;

        public MarkerItem(double lat, double lng){
            position = new LatLng(lat, lng);
        }
        @Override
        public LatLng getPosition() {
            return position;
        }
    }
}