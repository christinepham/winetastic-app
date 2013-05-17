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

public class AdvancedSearchAdapter extends BaseExpandableListAdapter {


	// -- Fields -- //
	private ArrayList<String> 	groupItem, tempChild;
	private ArrayList<Object> 	childrenItem;
	private ArrayList<ArrayList<View>> 	groupViews;
	private LayoutInflater 		minInflater;
	private Activity 			activity;
	
	// selected items
	private String 				selectedColor;
	private String 				selectedSeason;
	private String 				selectedType;
	private ArrayList<String> 	selectedAccents;

	// -- Constructors -- //

	public AdvancedSearchAdapter (ArrayList<String> group, ArrayList<Object> children ) {
		groupItem = group;
		childrenItem = children;
		groupViews = new ArrayList<ArrayList<View>>();
		for (int i=0; i<groupItem.size();i++) 
			groupViews.add(new ArrayList<View>());
		
		selectedColor = "";
		selectedSeason = "";
		selectedType = "";		
		selectedAccents = new ArrayList<String>();
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
				// need to figure out why other optins are being selected when they arent
				tempChild = (ArrayList<String>) childrenItem.get(groupPosition);
				CheckedTextView theItem = (CheckedTextView) v.findViewById(R.id.row_name);
//				Toast.makeText(activity, ""+ groupPosition+"  "+tempChild.get(0)+"  " + tempChild.get(childPosition),
//						Toast.LENGTH_SHORT).show();
				
						String group = groupItem.get(groupPosition);
						
						// Color group
						if (group.equals("Color")) {
							//Toast.makeText(activity, tempChild.get(childPosition), Toast.LENGTH_SHORT).show();
							
							selectColor(theItem, groupPosition);
						}
						// Season group
						else if (group.equals("Season")) {
							//Toast.makeText(activity, tempChild.get(childPosition), Toast.LENGTH_SHORT).show();
							selectSeason(theItem, groupPosition);
						}
						// Type group
						else if(group.equals("Type")) {
							//Toast.makeText(activity, tempChild.get(childPosition), Toast.LENGTH_SHORT).show();
							selectType(theItem, groupPosition);
						}
						// Accent group
						else if (group.equals("Accent")) {
							//Toast.makeText(activity, tempChild.get(childPosition), Toast.LENGTH_SHORT).show();
							//selectMultiple(text);
							selectAccent(theItem);
						}
						
				Toast.makeText(activity, "the selected: " + getSelectedItems(), Toast.LENGTH_SHORT).show();
			}
		});
		return convertView;
	}

	@SuppressWarnings("unchecked")
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
	
	public void makeSelection() {
		
	}
		
	@SuppressWarnings("unchecked")
	public String selectOne (CheckedTextView text, int groupPosition) {
		// deselect
		if (text.isSelected()) {
			text.setSelected(false);
			text.setBackgroundColor(text.getResources().getColor(R.color.cream));
			return "";
		}
		// clear the rest of selection
		ArrayList<View> views = ((ArrayList<View>)groupViews.get(groupPosition));
		for(View cv : views) {
			cv.setSelected(false);
			cv.setBackgroundColor(cv.getResources().getColor(R.color.cream));
		}
		
		text.setSelected(true);
		text.setBackgroundColor(Color.GREEN);
		return (String) text.getText();

	}
	
	public String selectMultiple(CheckedTextView text) {
		if (text.isSelected()) {
			text.setSelected(false);
			text.setBackgroundColor(text.getResources().getColor(R.color.cream));
			return "";
		}
		
		text.setSelected(true);
		text.setBackgroundColor(Color.GREEN);
		return (String) text.getText();
	}
	
	
	public void selectColor(CheckedTextView text, int groupPosition) {
		selectedColor = selectOne(text, groupPosition);
	}
	
	public void selectSeason(CheckedTextView text, int groupPosition) {
		selectedSeason = selectOne(text, groupPosition);
	}
	
	public void selectType(CheckedTextView text, int groupPosition) {
		selectedType = selectOne(text, groupPosition);
	}
	
	public void selectAccent(CheckedTextView text) {
		String acc = selectMultiple(text);
		if(acc.equals(""))  selectedAccents.remove((String)text.getText());
		else selectedAccents.add(acc);
		
	}
	
		
	public String getSelectedItems () {
		String s = "";
		s += " Color " + selectedColor + "\n";
		s += " Season " + selectedSeason + "\n";
		s += " Type " + selectedType + "\n";
		s += " Accents ";
		for (String acc: selectedAccents) {
			s += acc + "\n";
		}
		
		return s;
	}
	
	public void search() {

	}
	
	public void clear () {
		Toast.makeText(activity, "CLEAR", Toast.LENGTH_SHORT).show();
		// change all the backgrounds back to deselect color
		for (ArrayList<View> children : groupViews) {
			for(View view : children) {
				((CheckedTextView) view).setSelected(false);
				((CheckedTextView) view).setBackgroundColor(view.getResources().getColor(R.color.cream));
			}
		}
		
		// clear all the selections
		selectedColor = "";
		selectedSeason = "";
		selectedType = "";
		selectedAccents.clear();
	}

	
	
}
