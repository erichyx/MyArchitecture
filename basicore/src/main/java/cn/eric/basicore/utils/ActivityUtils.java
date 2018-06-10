package cn.eric.basicore.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class ActivityUtils
{
	public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
											 @NonNull Fragment fragment, int frameId) {
		checkNotNull(fragmentManager);
		checkNotNull(fragment);
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.add(frameId, fragment);
		transaction.commit();
	}

	public static void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager,
											 @NonNull Fragment fragment, int frameId) {
		checkNotNull(fragmentManager);
		checkNotNull(fragment);
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(frameId, fragment);
		transaction.commit();
	}

    private static <T> T checkNotNull(T reference) {
		if(reference == null) {
			throw new NullPointerException();
		} else {
			return reference;
		}
	}
}
