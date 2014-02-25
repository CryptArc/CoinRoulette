package com.derp.coinroulette;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BetListFragment extends Fragment {
	
	private String[] strings = {"Bet1", "Bet2", "Bet3" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_bet_list, container, false);
		
		ListView list = (ListView) root.findViewById(R.id.bet_list);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, strings);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener((OnItemClickListener) getActivity());
		
		return root;
	}

}
