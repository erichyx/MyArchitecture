package com.arch.eric;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.eric.basicore.arch.mvvm.uicontroller.BottomTabFragment;
import me.listenzz.navigation.AwesomeFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends AwesomeFragment {


    public OtherFragment() {
        // Required empty public constructor
    }

    public static OtherFragment newInstance(int sceneId) {

        Bundle args = new Bundle();

        OtherFragment fragment = new OtherFragment();
        args.putString(BottomTabFragment.ARGS_SCENE_ID, sceneId + "");
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_other, container, false);
    }

}
