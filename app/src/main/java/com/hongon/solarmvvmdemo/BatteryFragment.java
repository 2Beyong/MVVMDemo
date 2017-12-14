package com.hongon.solarmvvmdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CoCO on 2017/12/12.
 */

public class BatteryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.page_battery,container,false);
        Log.d("Test","BatterFragment : onCreateView.");
        return view;
    }

    @Override
    public void onDestroy() {
        Log.d("Test","BatterFragment : onDestroy.");
        super.onDestroy();
    }
}
