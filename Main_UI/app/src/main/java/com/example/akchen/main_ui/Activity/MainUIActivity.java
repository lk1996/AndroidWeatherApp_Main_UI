package com.example.akchen.main_ui.Activity;

import android.app.ActionBar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.akchen.main_ui.Fragment.MainUIFragment;
import com.example.akchen.main_ui.R;
import com.example.akchen.main_ui.Adapter.SectionsPagerAdapter;
import com.example.akchen.main_ui.others.utils.Plan;
import com.example.akchen.main_ui.others.utils.User;
import com.example.akchen.main_ui.others.utils.WeatherDB;
import com.example.akchen.main_ui.others.widget.LocationSelectorDialogBuilder;
import com.thinkpage.lib.api.TPCity;
import com.thinkpage.lib.api.TPListeners;
import com.thinkpage.lib.api.TPWeatherDaily;
import com.thinkpage.lib.api.TPWeatherManager;
import com.thinkpage.lib.api.TPWeatherNow;

import java.util.ArrayList;
import java.util.Date;

public class MainUIActivity extends AppCompatActivity implements View.OnClickListener, LocationSelectorDialogBuilder.OnSaveLocationLister {

    private static SectionsPagerAdapter mSectionAdapter = null;
    private static ViewPager mViewPager;
    private WeatherDB weatherDB;
    private Button main_add = null;
    private Button main_share = null;
    private LocationSelectorDialogBuilder locationBuilder;
//    private TPWeatherManager weatherManager;
    private  static ArrayList<MainUIFragment> fragmentsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        getSupportActionBar().hide();

        SectionsPagerAdapter.getLocationList().add("beijing");
//        fragmentsList=SectionsPagerAdapter.getFragmentsList();
//
//        final MainUIFragment mainFragment = MainUIFragment.newInstance("beijing");
//        SectionsPagerAdapter.getFragmentsList().add(fragmentsList.size(), mainFragment);

        mSectionAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionAdapter);


//        //获取北京的天气
//        weatherManager = TPWeatherManager.sharedWeatherManager();
//        //使用心知天气官网获取的key和用户id初始化WeatherManager
//        weatherManager.initWithKeyAndUserId("voxdfkclylzg3hjf", "U3D4EAF09E");
//
//        // 获取北京当前天气，使用简体中文、摄氏度
//        weatherManager.getWeatherNow(new TPCity("beijing")
//                , TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese
//                , TPWeatherManager.TPTemperatureUnit.kCelsius
//                , new TPListeners.TPWeatherNowListener() {
//                    @Override
//                    public void onTPWeatherNowAvailable(TPWeatherNow weatherNow, String errorInfo) {
//                        if (weatherNow != null) {
//                            //weatherNow 就是返回的当前天气信息
//                            mainFragment.SetWeatherNow(weatherNow);
//                            mainFragment.FreshFragment();
//                        } else {
//
//                        }
//                    }
//                });
//        //未来3天
//        weatherManager.getWeatherDailyArray(new TPCity("beijing"),
//                TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese,
//                TPWeatherManager.TPTemperatureUnit.kCelsius,
//                new Date(),
//                3,
//                new TPListeners.TPWeatherDailyListener() {
//                    @Override
//                    public void onTPWeatherDailyAvailable(TPWeatherDaily[] tpWeatherDailies, String s) {
//                        if (tpWeatherDailies != null) {
//                            //weatherNow 就是返回的当前天气信息
//                            mainFragment.SetFutureWeather(tpWeatherDailies);
//                            mainFragment.FreshFragment();
//                        }
//                    }
//                }
//
//        );


        main_share = (Button) findViewById(R.id.main_share);
        main_add = (Button) findViewById(R.id.main_add);

        main_add.setOnClickListener(this);
        main_share.setOnClickListener(this);
    }


    @Override
    public void onSaveLocation(String location, String provinceId, String cityId) {


        SectionsPagerAdapter.getLocationList().add("shanghai");
        mSectionAdapter.notifyDataSetChanged();
//        mSectionAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
////        mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionAdapter);
//        mSectionAdapter.getItem(1);
//
//        final MainUIFragment newFragment = MainUIFragment.newInstance(location);
//        SectionsPagerAdapter.getFragmentsList().add(fragmentsList.size(), newFragment);
//        mViewPager.setAdapter(mSectionAdapter);
//
//        // 获取北京当前天气，使用简体中文、摄氏度
//        weatherManager.getWeatherNow(new TPCity(location)
//                , TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese
//                , TPWeatherManager.TPTemperatureUnit.kCelsius
//                , new TPListeners.TPWeatherNowListener() {
//                    @Override
//                    public void onTPWeatherNowAvailable(TPWeatherNow weatherNow, String errorInfo) {
//                        if (weatherNow != null) {
//                            //weatherNow 就是返回的当前天气信息
//                            newFragment.SetWeatherNow(weatherNow);
//                            newFragment.FreshFragment();
//                        } else {
//
//                        }
//                    }
//                });
//        //未来3天
//        weatherManager.getWeatherDailyArray(new TPCity(location),
//                TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese,
//                TPWeatherManager.TPTemperatureUnit.kCelsius,
//                new Date(),
//                3,
//                new TPListeners.TPWeatherDailyListener() {
//                    @Override
//                    public void onTPWeatherDailyAvailable(TPWeatherDaily[] tpWeatherDailies, String s) {
//                        if (tpWeatherDailies != null) {
//                            //weatherNow 就是返回的当前天气信息
//                            newFragment.SetFutureWeather(tpWeatherDailies);
//                            newFragment.FreshFragment();
//                        }
//                    }
//                }
//
//        );



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_share:
                System.out.println("ffffff");
                break;
            case R.id.main_add:
                if (locationBuilder == null) {
                    locationBuilder = LocationSelectorDialogBuilder.getInstance(this);
                    locationBuilder.setOnSaveLocationLister(this);
                }
                locationBuilder.show();
                break;
        }
    }
}
