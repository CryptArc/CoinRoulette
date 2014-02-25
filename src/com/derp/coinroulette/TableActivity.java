package com.derp.coinroulette;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class TableActivity extends Activity implements View.OnTouchListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_table);
		
		View tableImage = findViewById(R.id.image);
		tableImage.setOnTouchListener(this);
	}
	
	/* 
	 * Handle touch events on table image
	 * 
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
	 */
	@Override
	public boolean onTouch (View v, MotionEvent ev) {
		 final int action = ev.getAction();
		 final int evX = (int) ev.getX();
		 final int evY = (int) ev.getY();
		 		 
		 switch (action) {
		 case MotionEvent.ACTION_UP :
		   int touchColor = getHotspotColor (R.id.image_areas, evX, evY);
		   int tolerance = 25;
		   if (closeMatch(Color.YELLOW, touchColor, tolerance)) {
			   Toast.makeText(this, "Touched RED", Toast.LENGTH_LONG).show();
		   } else {
			   Toast.makeText(this, "Touched random", Toast.LENGTH_LONG).show();
		   }
		  } 
		  return true;
		}
	
	private int getHotspotColor (int hotspotId, int x, int y) {
		  ImageView img = (ImageView) findViewById (hotspotId);
		  img.setDrawingCacheEnabled(true); 
		  Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache()); 
		  img.setDrawingCacheEnabled(false);
		  try {
			  return hotspots.getPixel(x, y);
		  } catch (IllegalArgumentException e) {
			  return -1;
		  }
	}
	
	private boolean closeMatch (int color1, int color2, int tolerance) {
		 if ((int) Math.abs (Color.red (color1) - Color.red (color2)) > tolerance ) 
		    return false;
		 if ((int) Math.abs (Color.green (color1) - Color.green (color2)) > tolerance ) 
		    return false;
		 if ((int) Math.abs (Color.blue (color1) - Color.blue (color2)) > tolerance ) 
		    return false;
		 return true;
	} 
	
	
	public void callBetClicked(View view) {
		Intent intent;
		if (getResources().getBoolean(R.bool.dual_pane)) {
			intent = new Intent(this, TwoPanePlaceBetActivity.class);
		} else {
			intent = new Intent(this, SinglePanePlaceBetActivity.class);
		}
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			Toast.makeText(this, data.getExtras().getString("bet_type"), Toast.LENGTH_LONG).show();
		}
	}
	
}
