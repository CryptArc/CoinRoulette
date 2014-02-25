package com.derp.coinroulette;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Used in the single pane interface to confirm a bet
 * 
 * @author x1x
 *
 */
public class ConfirmBetActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_bet);
    }
    
	public void onConfirmBet(View v) {
		setResult(1, getIntent().putExtra("bet_type", "asdf"));
		finish();
	}

}
