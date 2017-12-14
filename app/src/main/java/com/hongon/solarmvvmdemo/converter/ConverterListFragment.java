package com.hongon.solarmvvmdemo.converter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hongon.solarmvvmdemo.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by CoCO on 2017/12/14.
 */

public class ConverterListFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.frag_converte_list,container,false);
        Log.d("Test","ConverterListFragment onCreateView");
        return view;
    }

   @Override
   public void onStart() {
       super.onStart();
       // 初始化List
       initList();

       // 设置Adapter
       RecyclerView recyclerView = getView().findViewById(R.id.id_converterRecyclerView);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       ConverterListItemAdapter adapter =new ConverterListItemAdapter(mConverterList);
       recyclerView.setAdapter(adapter);
   }



    //-------------
    ArrayList<ConverterListItem> mConverterList;
    private void initList()
    {
        mConverterList = new ArrayList<ConverterListItem>();
        for(int i=0;i<6;++i)
        {
            ConverterListItem T =new ConverterListItem();
            T.setID(1000+i);
            String name = "Converter "+(char)(i+32);
            T.setName(name);
            T.setLastReportTime(new Date(System.currentTimeMillis()));
            mConverterList.add(T);
        }
    }

}
