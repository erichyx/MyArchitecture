package com.arch.eric;

import android.os.Bundle;

import com.arch.eric.mvp.MvpFragment;
import com.arch.eric.mvvm.MovieFragment;

import cn.eric.basicore.arch.mvvm.uicontroller.BottomTabFragment;
import me.listenzz.navigation.AwesomeActivity;

public class MainActivity extends AwesomeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BottomTabFragment rootFragment = BottomTabFragment.newInstance(R.menu.navigation, R.id.navigation_mvvm);
        MvpFragment mvpFragment = MvpFragment.newInstance(R.id.navigation_mvp);
        MovieFragment movieFragment = MovieFragment.newInstance(R.id.navigation_mvvm);
        OtherFragment otherFragment = OtherFragment.newInstance(R.id.navigation_other);
        rootFragment.setChildFragments(mvpFragment, movieFragment, otherFragment);
        setActivityRootFragment(rootFragment);
    }
}
