package com.example.user.el;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * Created by user on 2019/5/13.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 4;
    public Fragment playerPage = null;
    public Fragment friendPage = null;
    public Fragment settingPage = null;
    public Fragment shopPage = null;


    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
        playerPage = new playerPage();
        friendPage = new friendPage();
        shopPage = new shopPage();
        settingPage = new settingPage();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case mainpage.PAGE_ONE:
                fragment = friendPage;
                break;
            case mainpage.PAGE_TWO:
                fragment = playerPage;
                break;
            case mainpage.PAGE_THREE:
                fragment = shopPage;
                break;
            case mainpage.PAGE_FOUR:
                fragment = settingPage;
                break;
        }
        return fragment;
    }

}
