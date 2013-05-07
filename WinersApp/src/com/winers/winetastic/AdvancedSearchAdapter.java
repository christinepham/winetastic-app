package com.winers.winetastic;

import java.util.ArrayList;

import com.example.winersapp.R;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class AdvancedSearchAdapter extends BaseExpandableListAdapter {


	// -- Fields -- //
	private ArrayList<String> groupItem, tempChild;
	private ArrayList<Object> childrenItem;
	private LayoutInflater minInflater;
	private Activity activity;

	// -- Constructors -- //

	public AdvancedSearchAdapter (ArrayList<String> group, ArrayList<Object> children ) {
		groupItem = group;
		childrenItem = children;
	}


	// -- Methods -- //

	public void setInflater (LayoutInflater inflater, Activity act) {
		minInflater = inflater;
		activity = act;
	}

	@Override
	public Object getChild(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		tempChild = (ArrayList<String>) childrenItem.get(groupPosition);
		
		if (convertView == null) {
			convertView = minInflater.inflate(R.layout.child_row, null);
		}
		final CheckedTextView text = (CheckedTextView) convertView.findViewById(R.id.row_name);
		text.setText(tempChild.get(childPosition));
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(activity, tempChild.get(childPosition),
					//	Toast.LENGTH_SHORT).show();
						//text.setBackgroundColor(Color.BLACK);
			}
		});
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return ((ArrayList<String>) childrenItem.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return groupItem.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = minInflater.inflate(R.layout.group_row, null);
		}
		((CheckedTextView) convertView).setText(groupItem.get(groupPosition));
		((CheckedTextView) convertView).setChecked(isExpanded);
		 
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
