package com.derp.coinroulette;

import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SpinWheelActivity extends Activity implements AnimationListener, DialogInterface.OnClickListener {

	private Handler handler = new Handler();
	private Runnable displayResult;
	private Random random = new Random();
	
	private Bitmap rotateBitmap(Bitmap old, float angle) {
		Bitmap nbitmap = Bitmap.createBitmap(old.getWidth(), old.getHeight(), old.getConfig());
		
		Matrix mat = new Matrix();
		mat.setRotate(angle, old.getWidth() / 2, old.getHeight() / 2);
		Canvas canvas = new Canvas(nbitmap);
		canvas.drawBitmap(old, mat, new Paint());
		
		return nbitmap;
	}

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_spinwheel);

		int pocket = 32;
		
		/* Rotate with a random amount to make it look nicer */
		float randomAngle = random.nextFloat() * 360;
		
		/* TODO: This requires APIv11. Find out some way of doing this in earlier versions. */
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inMutable = true;
		
		/* Load ball image and rotate so the ball lands on correct wheel pocket. 
		 * Blend into wheel image and display in ImageView */
		
		Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.wheel_ball);
		Bitmap ballRotated = rotateBitmap(ball, pocket * 360f / 36.0f + randomAngle);
		ball = null; /* Clean up to avoid out out memory exceptions */
		
		/* Copy the rotated ball onto the wheel image */
		
		Bitmap wheelBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wheel, options);
		Bitmap wheelRotated = rotateBitmap(wheelBitmap, randomAngle);
		wheelBitmap = null;
		
		overlay(wheelRotated, ballRotated);
		
		/* Set the new image to the image view */
		ImageView wheel_image = (ImageView) findViewById(R.id.wheel_image);
		wheel_image.setImageBitmap(wheelRotated);
		
		/* Set up animation */
		Animation spinAnimation = AnimationUtils.loadAnimation(this, R.anim.wheel_spin_anim);
		spinAnimation.setAnimationListener(this);
		wheel_image.setAnimation(spinAnimation);
	}

	public void overlay(Bitmap bitmap, Bitmap overlay) {
	    Canvas canvas = new Canvas(bitmap);
	    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
	    canvas.drawBitmap(overlay, 0, 0, paint);
	} 

	@Override
	public void onAnimationEnd(Animation arg0) {
		final MediaPlayer mp = MediaPlayer.create(this, R.raw.win);
		final Activity activity = this;
		
		displayResult = new Runnable() {
			
			@Override
			public void run() {
				mp.start();
				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setNeutralButton("OK", (OnClickListener) activity)
				       .setCancelable(false)
				       .setMessage("You won 100 BTC!").create().show();
			}
		};
		
		handler.postDelayed(displayResult, 1000);
	}
	
	@Override 
	public void onPause() {
		super.onPause();
		handler.removeCallbacks(displayResult);
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		
	}

	@Override
	public void onAnimationStart(Animation arg0) {
		
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		finish();
	}
}
