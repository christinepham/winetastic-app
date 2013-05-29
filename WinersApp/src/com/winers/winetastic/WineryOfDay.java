package com.winers.winetastic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.winers.winetastic.R;

public class WineryOfDay extends Fragment {
	
	public WineryOfDay() {
		
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment_winery_of_day, container, false);
        return rootView;
    }
}