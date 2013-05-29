package com.winers.winetastic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import com.winers.winetastic.R;

public class WineOfDay extends Fragment {	
	
	public WineOfDay() {
		
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment_wine_of_day, container, false);
        return rootView;
    }

}
