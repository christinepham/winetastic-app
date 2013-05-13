package com.winers.winetastic;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.example.winersapp.R;

public class AdvancedSearchAdapter extends BaseExpandableListAdapter {


	// -- Fields -- //
	private ArrayList<String> 	groupItem, tempChild;
	private ArrayList<Object> 	childrenItem;
	private ArrayList<Object> 	groupViews;
	private LayoutInflater 		minInflater;
	private Activity 			activity;
	

	// -- Constructors -- //

	public AdvancedSearchAdapter (ArrayList<String> group, ArrayList<Object> children ) {
		groupItem = group;
		childrenItem = children;
		groupViews = new ArrayList<Object>();
		for (int i=0; i<groupItem.size();i++) 
			groupViews.add(new ArrayList<View>());
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

	@SuppressWarnings("unchecked")
	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		tempChild = (ArrayList<String>) childrenItem.get(groupPosition);
		
		if (convertView == null) {
			convertView = minInflater.inflate(R.layout.child_row, null);
		}
		final CheckedTextView text = (CheckedTextView) convertView.findViewById(R.id.row_name);
		text.setText(tempChild.get(childPosition));
		((ArrayList<View>)groupViews.get(groupPosition)).add(text);
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// toast caused an index out of bounds error
				// there is an error on which position certain items are getting added
				// children are going to wrong group position in children item
				tempChild = (ArrayList<String>) childrenItem.get(groupPosition);
				Toast.makeText(activity, ""+ groupPosition+"  "+tempChild.get(0)+"  " + tempChild.get(childPosition),
						Toast.LENGTH_SHORT).show();
						String group = groupItem.get(groupPosition);
						
						// Color group
						if (group.equals("Color")) {
							selectOne(text, groupPosition);
						}
						// Season group
						else if (group.equals("Season")) {
							selectOne(text, groupPosition);
						}
						// Type group
						else if(group.equals("Type")) {
							selectOne(text, groupPosition);
						}
						// Accent group
						else if (group.equals("Accent")) {
							selectMultiple(text);
						}
						
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
		
	@SuppressWarnings("unchecked")
	public void selectOne (CheckedTextView text, int groupPosition) {
		// deselect
		if (text.isSelected()) {
			text.setSelected(false);
			text.setBackgroundColor(text.getResources().getColor(R.color.cream));
			return;
		}
		// clear the rest of selection
		ArrayList<View> views = ((ArrayList<View>)groupViews.get(groupPosition));
		for(View cv : views) {
			cv.setSelected(false);
			cv.setBackgroundColor(cv.getResources().getColor(R.color.cream));
			
		}
		text.setSelected(true);
		text.setBackgroundColor(Color.GREEN);

	}
	
	public void selectMultiple(CheckedTextView text) {
		if (text.isSelected()) {
			text.setSelected(false);
			text.setBackgroundColor(text.getResources().getColor(R.color.cream));
			return;
		}
		
		text.setSelected(true);
		text.setBackgroundColor(Color.GREEN);
		
	}
	
	public void search() {

	}

}
