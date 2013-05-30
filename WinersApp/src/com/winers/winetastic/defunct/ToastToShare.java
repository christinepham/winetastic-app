package com.winers.winetastic.defunct;

import com.winers.winetastic.AbstractActivity;
import com.winers.winetastic.R;
import com.winers.winetastic.R.layout;
import com.winers.winetastic.R.menu;
import com.winers.winetastic.R.string;

import android.os.Bundle;
import android.view.Menu;

public class ToastToShare extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_to_share);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_toast_to_share, menu);
        return true;
    }

	@Override
	protected int getTitleText() {
		// TODO Auto-generated method stub
		return R.string.toast_to_share_title;
	}
}
