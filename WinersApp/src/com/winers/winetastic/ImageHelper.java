/**
 * Wrapper for image manipulation methods. 
 */

package com.winers.winetastic;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;

public class ImageHelper {
	public final Point displaySize;
	
	private ImageHelper() {	 // inaccessible constructor	
		displaySize = new Point();
	}
	
	/** 
	 * Resizes a view based on the android screen size.
	 * It keeps the aspect ratio of the image's default dimensions.
	 * @author Victoria Do
	 * @param rootv  The element to be resized
	 * 
	 */
	public static void scaleToScreenWidth(View view) {
		// Get display information
		
		int width = view.getMeasuredWidth();
		int height = view.getMeasuredHeight();		
		double ratio = width/height;
		
		
	}
	
	protected static void setDisplaySize(Context c) {
		WindowManager windowManager = (WindowManager)c.getSystemService(Context.WINDOW_SERVICE);
		int width = windowManager.getDefaultDisplay().getWidth();
		int height = windowManager.getDefaultDisplay().getHeight();
	}
	
//	protected static Point getDisplaySize() {
//		if(displaySize == null)
//			return null;
//	}
}
