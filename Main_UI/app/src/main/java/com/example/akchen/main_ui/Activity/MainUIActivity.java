package com.example.akchen.main_ui.Activity;

import android.app.ActionBar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.akchen.main_ui.Fragment.MainUIFragment;
import com.example.akchen.main_ui.R;
import com.example.akchen.main_ui.Adapter.SectionsPagerAdapter;
import com.thinkpage.lib.api.TPCity;
import com.thinkpage.lib.api.TPListeners;
import com.thinkpage.lib.api.TPWeatherDaily;
import com.thinkpage.lib.api.TPWeatherManager;
import com.thinkpage.lib.api.TPWeatherNow;

import java.util.Date;

public class MainUIActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionAdapter =null;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_ui);
        final MainUIFragment mainFragment = MainUIFragment.newInstance(0);
        mSectionAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        SectionsPagerAdapter.getFragmentsList().add(0,mainFragment);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionAdapter);
        //获取北京的天气
        TPWeatherManager weatherManager = TPWeatherManager.sharedWeatherManager();
        //使用心知天气官网获取的key和用户id初始化WeatherManager
        weatherManager.initWithKeyAndUserId("voxdfkclylzg3hjf","U3D4EAF09E");

        getSupportActionBar().hide();
       // 获取北京当前天气，使用简体中文、摄氏度
        //ActionBar actionBar=getActionBar();
        //actionBar.hide();
       weatherManager.getWeatherNow(new TPCity("beijing")
                , TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese
                , TPWeatherManager.TPTemperatureUnit.kCelsius
                , new TPListeners.TPWeatherNowListener() {
                    @Override
                    public void onTPWeatherNowAvailable(TPWeatherNow weatherNow, String errorInfo) {
                        if (weatherNow != null) {
                            //weatherNow 就是返回的当前天气信息
                            mainFragment. SetWeatherNow(weatherNow);
                           mainFragment.FreshFragment();
                        } else {

                        }}});
        //未来3天
        weatherManager.getWeatherDailyArray(new TPCity("beijing"),
                TPWeatherManager.TPWeatherReportLanguage.kSimplifiedChinese,
                TPWeatherManager.TPTemperatureUnit.kCelsius,
                new Date(),
                3,
                new TPListeners.TPWeatherDailyListener(){
                    @Override
                    public void onTPWeatherDailyAvailable(TPWeatherDaily[] tpWeatherDailies, String s) {
                        if ( tpWeatherDailies != null) {
                            //weatherNow 就是返回的当前天气信息
                            mainFragment.SetFutureWeather(tpWeatherDailies);
                            mainFragment.FreshFragment();
                        }
                    }
                }

                );

    }
}
