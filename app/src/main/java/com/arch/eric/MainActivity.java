package com.arch.eric;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.arch.eric.mvp.business.MvpFragment;
import com.arch.eric.mvvm.ui.MvvmFragment;
import com.arch.eric.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity
{
    private MvvmFragment mMvvmFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId()) {
                case R.id.navigation_mvvm:
                    if (mMvvmFragment == null) {
                        mMvvmFragment = MvvmFragment.newInstance();
                    }
                    ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mMvvmFragment, R.id.container);
                    return true;
                case R.id.navigation_mvp:
                    if (mvpFragment == null) {
                        mvpFragment = MvpFragment.newInstance();
                    }
                    ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mvpFragment, R.id.container);
                    return true;
                case R.id.navigation_other:

                    return true;
            }
            return false;
        }
    };
    private MvpFragment mvpFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMvvmFragment = MvvmFragment.newInstance();
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mMvvmFragment, R.id.container);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
