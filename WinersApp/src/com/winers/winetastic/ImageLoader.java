package com.winers.winetastic;

import java.io.InputStream;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

public class ImageLoader {
	
	private ImageLoader() { }

	/* Helper classes for loadImageFromWeb
	 * 		 
	 * 		:(
	 * 
	 */
	
	private class URLWrapper {
		String s;
		View v;
		
		public URLWrapper(String s, View v) {
			this.s = s;
			this.v = v;
		}
	}

	private class URLToDrawable extends AsyncTask<Void, Void, Void> {
		URLWrapper wrapper;
		Drawable d;
		
		public URLToDrawable(URLWrapper wrapper) {
			this.wrapper = wrapper;
		}
		
		protected Void doInBackground(Void ... args) {
		    try {
		        InputStream is = (InputStream) new URL(wrapper.s).getContent();
		        d = Drawable.createFromStream(is, "image");
		    } catch (Exception e) {
		    }
		    return null;
		}
		
		@Override
		protected void onPostExecute(Void arg) {
			try {
				((ImageView)(wrapper.v)).setImageDrawable(d);	
			} catch(ClassCastException e) {
				System.err.println(e.getMessage());
				System.err.println("Attempted to cast View to ImageView.");
			}
		}
	}
	
	/**
	 * Creates Drawable from a url
	 * @param url 
	 * @return Drawable
	 */
	public static void loadFromWeb(String url, View v) {
		ImageLoader ih = new ImageLoader();
		ih.new URLToDrawable(ih.new URLWrapper(url, v)).execute();
	}		
	
}
