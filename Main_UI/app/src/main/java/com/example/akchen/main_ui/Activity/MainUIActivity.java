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

        String pinyinLocation= "";
        if(location.indexOf("北京")>=0)
            pinyinLocation = "beijing";
        if(location.indexOf("上海")>=0)
            pinyinLocation = "shanghai";
        if(location.indexOf("天津")>=0)
            pinyinLocation = "tianjin";
        if(location.indexOf("重庆")>=0)
            pinyinLocation = "chongqing";
        if(location.indexOf("香港")>=0)
            pinyinLocation = "xianggang";
        if(location.indexOf("澳门")>=0)
            pinyinLocation = "aomen";
        if(location.indexOf("台湾")>=0)
            pinyinLocation = "taizhong";
        if(location.indexOf("黑龙江")>=0)
            pinyinLocation = "jixi";
        if(location.indexOf("辽宁")>=0)
            pinyinLocation = "dalian";
        if(location.indexOf("内蒙古")>=0)
            pinyinLocation = "fengzhen";
        if(location.indexOf("河北")>=0)
            pinyinLocation = "zhangjiakou";
        if(location.indexOf("河南")>=0)
            pinyinLocation = "luoyang";
        if(location.indexOf("山西")>=0)
            pinyinLocation = "linfen";
        if(location.indexOf("山东")>=0)
            pinyinLocation = "heze";
        if(location.indexOf("江苏")>=0)
            pinyinLocation = "lianyungang";
        if(location.indexOf("浙江")>=0)
            pinyinLocation = "quzhou";
        if(location.indexOf("福建")>=0)
            pinyinLocation = "xiamen";
        if(location.indexOf("江西")>=0)
            pinyinLocation = "ganzhou";
        if(location.indexOf("安徽")>=0)
            pinyinLocation = "hefei";
        if(location.indexOf("湖北")>=0)
            pinyinLocation = "wuhan";
        if(location.indexOf("湖南")>=0)
            pinyinLocation = "changsha";
        if(location.indexOf("广东")>=0)
            pinyinLocation = "zhanjiang";
        if(location.indexOf("广西")>=0)
            pinyinLocation = "nanning";
        if(location.indexOf("海南")>=0)
            pinyinLocation = "hainan";
        if(location.indexOf("贵州")>=0)
            pinyinLocation = "guiyang";
        if(location.indexOf("云南")>=0)
            pinyinLocation = "puer";
        if(location.indexOf("四川")>=0)
            pinyinLocation = "chengdu";
        if(location.indexOf("西藏")>=0)
            pinyinLocation = "lasa";
        if(location.indexOf("陕西")>=0)
            pinyinLocation = "hanzhong";
        if(location.indexOf("宁夏")>=0)
            pinyinLocation = "yinchuan";
        if(location.indexOf("甘肃")>=0)
            pinyinLocation = "qingyang";
        if(location.indexOf("青海")>=0)
            pinyinLocation = "yushu";
        if(location.indexOf("新疆")>=0)
            pinyinLocation = "hami";
        ArrayList<String> locationlist =  SectionsPagerAdapter.getLocationList();
        boolean flag = true;
        for(int i=0;i<locationlist.size();i++)
        {
            if(locationlist.get(i).equals(pinyinLocation))
                flag = false;
        }

        if(flag) {
            SectionsPagerAdapter.getLocationList().add(pinyinLocation);
            mSectionAdapter.notifyDataSetChanged();
        }
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
