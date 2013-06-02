package com.winers.winetastic;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

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
	private ArrayList<String> 			groupItem, tempChild;
	private ArrayList<String> 			groupText;					
	private ArrayList<Object> 			childrenItem;
	private ArrayList<View> 			groupViews;
	private ArrayList<ArrayList<View>> 	childViews;
	private LayoutInflater 				minInflater;
	private Activity 					activity;
	private ArrayList<boolean[]> 		isSelected;
	private boolean[] 					groupIsSelected;	
	private WineSearchObject 			searchParameters;

	// -- Constructors -- //

	public AdvancedSearchAdapter (ArrayList<String> group, ArrayList<Object> children ) {
		groupItem = group;
		childrenItem = children;
		groupViews = new ArrayList<View>();
		childViews = new ArrayList<ArrayList<View>>();
		isSelected = new ArrayList<boolean[]>();
		groupText = new ArrayList<String>();
		groupIsSelected = new boolean[groupItem.size()];
		for (int i=0; i<groupItem.size();i++) {
			childViews.add(new ArrayList<View>());
			isSelected.add(new boolean[((ArrayList<String>) children.get(i)).size()]);
			//groupIsSelected[i] = false;
			groupText.add(groupItem.get(i));
			//groupViews.add(new View(activity));
		}
		//setSelectionsFalse();

		searchParameters = new WineSearchObject();
	}


	// -- Methods -- //


	public WineSearchObject getSearchParameters() {
		return searchParameters;
	}

	public void initializeSelections () {
		setSelectionsFalse();
	}

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
		CheckedTextView rowItem = (CheckedTextView) convertView.findViewById(R.id.row_name);
		rowItem.setText(tempChild.get(childPosition));
		((ArrayList<View>)childViews.get(groupPosition)).add(rowItem);



		if ((isSelected.get(groupPosition))[childPosition]) {
			rowItem.setBackgroundColor(Color.GREEN);
		}
		else {
			rowItem.setBackgroundColor(convertView.getResources().getColor(R.color.cream));
		}


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
//				Toast.makeText(activity, "click  "+ groupPosition+"  "+ childPosition,
//				Toast.LENGTH_SHORT).show();

						String group = groupItem.get(groupPosition);

						// Color group
						if (group.equals("Color")) {
							//Toast.makeText(activity, tempChild.get(childPosition), Toast.LENGTH_SHORT).show();

							selectColor(theItem, groupPosition, childPosition);
						}
						// Price group
						else if (group.equals("Price")) {
							//Toast.makeText(activity, tempChild.get(childPosition), Toast.LENGTH_SHORT).show();
							selectPrice(theItem, groupPosition, childPosition);
						}
						// Type group
						else if(group.equals("Type")) {
							//Toast.makeText(activity, tempChild.get(childPosition), Toast.LENGTH_SHORT).show();
							selectType(theItem, groupPosition, childPosition);
						}
						// Accent group
						else if (group.equals("Accent")) {
							//Toast.makeText(activity, tempChild.get(childPosition), Toast.LENGTH_SHORT).show();
							//selectMultiple(text);
							selectAccent(theItem, groupPosition, childPosition);
						}

						/*
						 * UNCOMMENT THE FOLLOWING FOR DEBUGGING PURPOSES
						 *
						String colorss = "";
						for(boolean colorSelect: isSelected.get(0)) {
							colorss += colorSelect + "   ";
						}
						Toast.makeText(activity, colorss, Toast.LENGTH_SHORT).show();
						*/
				// UNCOMMENT THE FOLLOWING FOR DEBUGGING PURPOSES
				//Toast.makeText(activity, "the selected: " + getSelectedItems(), Toast.LENGTH_SHORT).show();
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
		return groupItem.get(groupPosition);
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
		if(groupViews.size()<groupItem.size() && !groupViews.contains(convertView))
			groupViews.add(groupPosition, convertView);
		
		if(groupIsSelected[groupPosition]) {
			((CheckedTextView) convertView).setText(groupText.get(groupPosition));
		}
		else {
			((CheckedTextView) convertView).setText(groupItem.get(groupPosition));
		}
		
		
		
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
	// Which warning are we suppressing here?
	public String selectOne (CheckedTextView item, int groupPosition, int childPosition) {
		// deselect
		
		CheckedTextView ct = ((CheckedTextView)groupViews.get(groupPosition));
		String text = (String) ct.getText();
		String ind = (String) ct.getText();
		ind += "  "+ groupPosition;
		Toast.makeText(activity, ind, Toast.LENGTH_SHORT).show();
		
		
		if ((isSelected.get(groupPosition))[childPosition]) {
			item.setSelected(false);
			(isSelected.get(groupPosition))[childPosition] = false;
			item.setBackgroundColor(item.getResources().getColor(R.color.cream));
			
			if(text.indexOf("(") > 0) {
				ct.setText(text.substring(0, text.indexOf("(")));
				groupIsSelected[groupPosition] = false;
				text = text.substring(0, text.indexOf("("));
				text = text.trim();
				groupText.set(groupPosition, text);
			}
			return "";
		}
		// clear the rest of selection
		ArrayList<View> views = ((ArrayList<View>)childViews.get(groupPosition));
		for(View cv : views) {
			cv.setSelected(false);
			cv.setBackgroundColor(cv.getResources().getColor(R.color.cream));
		}

		boolean [] tempSel =isSelected.get(groupPosition); 
		for (int i=0; i<tempSel.length; i++) {
			tempSel[i] = false;
		}

		
		item.setSelected(true);
		(isSelected.get(groupPosition))[childPosition] = true;
		item.setBackgroundColor(Color.GREEN);
		if(text.indexOf("(") > 0) { 
			ct.setText(text.substring(0, text.indexOf("(")));
			text = text.substring(0, text.indexOf("("));
			text = text.trim();
			groupIsSelected[groupPosition] = false;
		}
		text += " (" + item.getText() + ")";
		ct.setText(text);
		groupText.set(groupPosition, text);
		groupIsSelected[groupPosition] = true;
		return (String) item.getText();

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


	public void selectColor(CheckedTextView text, int groupPosition, int childPosition) {
		searchParameters.setColor(selectOne(text, groupPosition, childPosition));
	}

	public void selectPrice(CheckedTextView text, int groupPosition, int childPosition) {
		searchParameters.setPrice(selectOne(text, groupPosition, childPosition));
	}

	public void selectType(CheckedTextView text, int groupPosition, int childPosition) {
		searchParameters.setType(selectOne(text, groupPosition, childPosition));
	}

	public void selectAccent(CheckedTextView text, int groupPosition, int childPosition) {
		
		/* When we had multiple accents
		String acc = selectMultiple(text);
		if(acc.equals(""))  {
			searchParameters.removeAccent((String)text.getText());
			(isSelected.get(groupPosition))[childPosition] = false;
		}
		else {
			searchParameters.addAccent(acc);
			(isSelected.get(groupPosition))[childPosition] = true;
		}
		*/
		searchParameters.setAccent(selectOne(text, groupPosition, childPosition));

	}


	public String getSelectedItems () {
		String s = "";
		s += " Color " + searchParameters.getColor() + "\n";
		s += " Price " + searchParameters.getPrice() + "\n";
		s += " Type " + searchParameters.getType() + "\n";
		s += " Accents " + searchParameters.getAccent();

		return s;
	}

	public void clear () {
		// UNCOMMENT THE FOLLOWING FOR DEBUGGING PURPOSES
		// Toast.makeText(activity, "CLEAR", Toast.LENGTH_SHORT).show();
		// change all the backgrounds back to deselect color
		for (ArrayList<View> children : childViews) {
			for(View view : children) {
				((CheckedTextView) view).setSelected(false);
				((CheckedTextView) view).setBackgroundColor(view.getResources().getColor(R.color.cream));
			}
		}
		String ind = "";
		for (int i=0; i<groupItem.size();i++) {
			ind += i + "  ";
			groupIsSelected[i] = false;
			groupText.set(i, groupItem.get(i));
			((CheckedTextView)groupViews.get(i)).setText(groupItem.get(i));

		}
		Toast.makeText(activity, ind, Toast.LENGTH_SHORT).show();
		((CheckedTextView)groupViews.get(0)).setText(groupItem.get(0));
		setSelectionsFalse();
		// clear all the selections
		searchParameters.clear();
	}

	public void tempClear () {
		// UNCOMMENT THE FOLLOWING FOR DEBUGGING PURPOSES
		//Toast.makeText(activity, "TEMP CLEAR", Toast.LENGTH_SHORT).show();
		for (ArrayList<View> children : childViews) {
			for(View view : children) {
				((CheckedTextView) view).setBackgroundColor(view.getResources().getColor(R.color.cream));
			}
		}		
	}

	private void setSelectionsFalse () {



		for (boolean[] grps : isSelected) {
			for (int i=0; i< grps.length; i++) {
				grps[i] = false;
			}
		}
//		for(int i=0;i<groupIsSelected.length;i++) {
//			groupIsSelected[i] = false;
//			groupText.set(i, groupItem.get(i));
//		}

		/* 
		 * UNCOMMENT THE FOLLOWING FOR DEBUGGING PURPOSES
		 * 
		String colorss = "";
		for(boolean colorSelect: isSelected.get(0)) {
			colorss += colorSelect + "   ";
		}
		
		Toast.makeText(activity, "CLear " + colorss, Toast.LENGTH_SHORT).show();
		*/
	}



}