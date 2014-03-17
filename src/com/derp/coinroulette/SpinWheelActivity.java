package com.derp.coinroulette;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SpinWheelActivity extends Activity implements AnimationListener, DialogInterface.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_spinwheel);
		
		ImageView wheel_image = (ImageView) findViewById(R.id.wheel_image);
		Animation spinAnimation = AnimationUtils.loadAnimation(this, R.anim.wheel_spin_anim);
		spinAnimation.setAnimationListener(this);
		wheel_image.setAnimation(spinAnimation);
		
		
	}

	@Override
	public void onAnimationEnd(Animation arg0) {
		/* We have finished playing the roulette animation.
		 * Play winning sound if applicable and display result after a delay
		 */
		Handler handler = new Handler();
		final MediaPlayer mp = MediaPlayer.create(this, R.raw.win);
		final Activity activity = this;
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				mp.start();
				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setNeutralButton("OK", (OnClickListener) activity)
				       .setCancelable(false)
				       .setMessage("You won 100 BTC!").create().show();
			}
		}, 1000);
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
