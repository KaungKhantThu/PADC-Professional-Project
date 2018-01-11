package xyz.kkt.padcprofessionalproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lenovo on 1/2/2018.
 */

public class ConfigUtils {

    private static final String KEY_PAGE_INDEX = "KEY_PAGE_INDEX";

    private SharedPreferences mSharedPreferences;

    public ConfigUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences("ConfigUtils", Context.MODE_PRIVATE);
    }


    public void savePageIndex(int pageIndex) {
        mSharedPreferences.edit().putInt(KEY_PAGE_INDEX, pageIndex).apply();
    }

    public int loadPageIndex() {
        return mSharedPreferences.getInt(KEY_PAGE_INDEX, 1);
    }
}