/**
 * Wrapper for image manipulation methods. 
 * @author Victoria Do
 */

package com.winers.winetastic;

import android.content.Context;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import android.graphics.Point;
import android.view.View;

import android.view.WindowManager;

public class ImageHelper {
	/* Set by call to setDisplaySize */
	public static DisplayMetrics DISPLAY_METRICS = null;  
	
	/* No instantiation */
	private ImageHelper() { }  
	
	/** 
	 * Resizes a view based on the android screen size.
	 * It keeps the aspect ratio of the image's default dimensions.
	 * 
	 * @param rootv  The element to be resized
	 */
	public static void scaleToScreenWidth(View view) {
		try {
			int width = view.getWidth();
			int height = view.getHeight();		
			double ratio = DISPLAY_METRICS.widthPixels/width;
			
			LayoutParams vparams = view.getLayoutParams();
			vparams.width = (int) DISPLAY_METRICS.widthPixels;
			vparams.height = (int) (height * ratio);
			view.setLayoutParams(vparams);
		} catch(NullPointerException e) {
			System.err.println("NullPointerException: DISPLAY_METRICS has not been set.");
		}
	}

	/** 
	 * Gets the display metrics of the device and sets it to the globally 
	 * accessible variable DISPLAY_METRICS.
	 * 
	 * @param c  the calling context
	 */	
	protected static void setDisplaySize(Context c) {
		if(DISPLAY_METRICS == null) {
			DISPLAY_METRICS = new DisplayMetrics();
			
			WindowManager w = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
			Display display = w.getDefaultDisplay();
			display.getMetrics(DISPLAY_METRICS);
		}
	}
	
	/** 
	 * Returns DISPLAY_METRICS, an object containing screen size information.
	 * 
	 * @return the DisplayMetrics object
	 */	
	
	public static DisplayMetrics getDisplayMetrics() {
		return ImageHelper.DISPLAY_METRICS;
	}
}
