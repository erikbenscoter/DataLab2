package com.project2015.datalab2.wellnessnet;



import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends android.support.v4.app.Fragment {

    Button btn_toSearch;
    Button btn_toLocal;
    Button btn_toHistory;


    public WelcomeFragment() {

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            final int ResourceIdToInflate = R.layout.fragment_welcome;

            //link to the fragment already in resources
            View view = inflater.inflate(R.layout.fragment_welcome, container, false);

            //let's attach to some buttons...we will need view to do this because they are in this
                //particular fragment vs. pulling them from a full activity

                btn_toSearch = (Button) view.findViewById(R.id.buttonToSearch);
                btn_toLocal = (Button) view.findViewById(R.id.buttonToSearch);
                btn_toHistory = (Button) view.findViewById(R.id.buttonToSearch);





        // Inflate the layout for this fragment
        return view;
    }




}
