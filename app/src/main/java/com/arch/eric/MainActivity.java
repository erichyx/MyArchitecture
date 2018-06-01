package com.arch.eric;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.arch.eric.mvp.business.MvpFragment;
import com.arch.eric.mvvm.ui.MovieFragment;
import com.arch.eric.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity
{
    private MovieFragment mMovieFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId()) {
                case R.id.navigation_mvp:
                    if (mvpFragment == null) {
                        mvpFragment = MvpFragment.newInstance();
                    }
                    ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mvpFragment, R.id.container);
                    return true;
                case R.id.navigation_mvvm:
                    if (mMovieFragment == null) {
                        mMovieFragment = MovieFragment.newInstance();
                    }
                    ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mMovieFragment, R.id.container);
                    return true;
                case R.id.navigation_other:
                    OtherFragment otherFragment = OtherFragment.newInstance();
                    ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), otherFragment, R.id.container);
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

        mMovieFragment = MovieFragment.newInstance();
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), mMovieFragment, R.id.container);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_mvvm);
    }
}
