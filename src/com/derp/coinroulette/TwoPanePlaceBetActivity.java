package com.derp.coinroulette;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class TwoPanePlaceBetActivity extends FragmentActivity implements OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_bet);
    }


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		/* Create different fragments depending on bet type selected */
		ConfirmBetFragment frag = new ConfirmBetFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.details_frame_layout, frag).commit();
	}

}
