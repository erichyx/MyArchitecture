package cn.eric.basicore.arch.mvvm.uicontroller;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.eric.basicore.R;
import me.listenzz.navigation.AwesomeFragment;
import me.listenzz.navigation.FragmentHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomTabFragment extends AwesomeFragment {

    private static final String SAVED_FRAGMENT_TAGS = "nav_fragment_tags";
    private static final String SAVED_SELECTED_INDEX = "nav_selected_index";

    private static final String ARGS_NAV_RES_ID = "nav_res_id";
    private static final String ARGS_NAV_SELECTED_ITEM_ID = "nav_selected_item_id";
    public static final String ARGS_SCENE_ID = "nav_scene_id";

    private List<AwesomeFragment> fragments;
    private ArrayList<String> fragmentTags = new ArrayList<>();
    private int navResId;
    private int selectedIndex;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            for (int i = 0; i < fragments.size(); i++) {
                AwesomeFragment fragment = fragments.get(i);
                if (fragment.getSceneId().equals(item.getItemId() + "")) {
                    setSelectedIndex(i);
                    break;
                }
            }
            return true;
        }
    };

    public static BottomTabFragment newInstance(int navMenuId, int selectedItemId) {

        Bundle args = new Bundle();

        BottomTabFragment fragment = new BottomTabFragment();
        args.putInt(ARGS_NAV_RES_ID, navMenuId);
        args.putInt(ARGS_NAV_SELECTED_ITEM_ID, selectedItemId);
        fragment.setArguments(args);
        return fragment;
    }

    public BottomTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_tab, container, false);
    }

    @Override
    public boolean isParentFragment() {
        return true;
    }

    @Override
    public void onViewCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        Bundle arguments = FragmentHelper.getArguments(this);
        navResId = arguments.getInt(ARGS_NAV_RES_ID);

        navigation = root.findViewById(R.id.navigation);
        navigation.inflateMenu(navResId);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState != null) {
            fragmentTags = savedInstanceState.getStringArrayList(SAVED_FRAGMENT_TAGS);
            fragments = new ArrayList<>();
            FragmentManager fragmentManager = getChildFragmentManager();
            for (int i = 0, size = fragmentTags.size(); i < size; i++) {
                fragments.add((AwesomeFragment) fragmentManager.findFragmentByTag(fragmentTags.get(i)));
            }
            selectedIndex = savedInstanceState.getInt(SAVED_SELECTED_INDEX);
            setSelectedIndex(selectedIndex);
        } else {
            if (fragments == null || fragments.size() == 0) {
                throw new IllegalArgumentException("必须使用 `setChildFragments` 设置 childFragments ");
            }
            setChildFragmentsInternal(fragments);

            int selectedItemId = arguments.getInt(ARGS_NAV_SELECTED_ITEM_ID);
            navigation.setSelectedItemId(selectedItemId);
        }
    }

    public void setChildFragments(AwesomeFragment... fragments) {
        setChildFragments(Arrays.asList(fragments));
    }

    public void setChildFragments(final List<AwesomeFragment> fragments) {
        if (isAdded()) {
            throw new IllegalStateException(getClass().getSimpleName() + " 已经处于 added 状态，不能再设置 childFragments");
        }
        this.fragments = fragments;
    }

    private void setChildFragmentsInternal(List<AwesomeFragment> fragments) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0, size = fragments.size(); i < size; i++) {
            AwesomeFragment fragment = fragments.get(i);
            fragmentTags.add(fragment.getSceneId());
            transaction.add(R.id.tabs_content, fragment, fragment.getSceneId());
            if (i == selectedIndex) {
                transaction.setPrimaryNavigationFragment(fragment);
            } else {
                transaction.hide(fragment);
            }
        }
        transaction.commit();
    }

    public void setSelectedIndex(int index) {
        scheduleTaskAtStarted(() -> {
            selectedIndex = index;
            FragmentManager fragmentManager = getChildFragmentManager();
            fragmentManager.executePendingTransactions();
            Fragment previous = fragmentManager.getPrimaryNavigationFragment();
            AwesomeFragment current = fragments.get(index);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setPrimaryNavigationFragment(current);
            transaction.hide(previous);
            transaction.show(current);
            transaction.commit();
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(SAVED_FRAGMENT_TAGS, fragmentTags);
        outState.putInt(SAVED_SELECTED_INDEX, selectedIndex);
    }
}
