package com.example.akchen.main_ui.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.akchen.main_ui.Fragment.MainUIFragment;
import com.example.akchen.main_ui.R;
import com.example.akchen.main_ui.Adapter.SectionsPagerAdapter;
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
    private static SwipeRefreshLayout fresher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        getSupportActionBar().hide();

        SectionsPagerAdapter.getLocationList().add("beijing");
        //..........

        mSectionAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionAdapter);
        fresher= (SwipeRefreshLayout)findViewById(R.id.fresher);
        fresher.setEnabled(false);
        fresher.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //获取当前的Fragment
                        final MainUIFragment currentFragment = mSectionAdapter.getCurrentFragment();
                        //位置
                        String location = currentFragment.getLocation();
                        //get manager
                        TPWeatherManager weatherManager  = TPWeatherManager.sharedWeatherManager();
                        // 获取北京当前天气，使用简体中文、摄氏度
                        weatherManager.getWeatherNow(new TPCity(location)
                                , TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese
                                , TPWeatherManager.TPTemperatureUnit.kCelsius
                                , new TPListeners.TPWeatherNowListener() {
                                    @Override
                                    public void onTPWeatherNowAvailable(TPWeatherNow weatherNow, String errorInfo) {
                                        if (weatherNow != null) {
                                            //weatherNow 就是返回的当前天气信息
                                            currentFragment.SetWeatherNow(weatherNow);
                                            currentFragment.FreshFragment();
                                        } else {

                                        }
                                    }
                                });
                        //未来3天
                        weatherManager.getWeatherDailyArray(new TPCity(location),
                                TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese,
                                TPWeatherManager.TPTemperatureUnit.kCelsius,
                                new Date(),
                                3,
                                new TPListeners.TPWeatherDailyListener() {
                                    @Override
                                    public void onTPWeatherDailyAvailable(TPWeatherDaily[] tpWeatherDailies, String s) {
                                        if (tpWeatherDailies != null) {
                                            //weatherNow 就是返回的当前天气信息
                                            currentFragment.SetFutureWeather(tpWeatherDailies);
                                            currentFragment.FreshFragment();
                                        }
                                    }
                                }

                        );

                        fresher.setRefreshing(false);


                    }
                });
        main_share = (Button) findViewById(R.id.main_share);
        main_add = (Button) findViewById(R.id.main_add);

        main_add.setOnClickListener(this);
        main_share.setOnClickListener(this);
    }


    @Override
    public void onSaveLocation(String location, String provinceId, String cityId) {

        SectionsPagerAdapter.getLocationList().add("shanghai");
        mSectionAdapter.notifyDataSetChanged();

    }
    public static SwipeRefreshLayout getFresher()
    {
        return fresher;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_share:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                //获取当前fragment的天气信息

                intent.putExtra(Intent.EXTRA_TEXT, "I have successfully share my message through my app");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getTitle()));
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
