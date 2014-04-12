package com.derp.coinroulette.gamelogic;

import java.util.ArrayList;
import java.util.List;

public class RouletteTable {
	private ArrayList<Bet> bets = new ArrayList<Bet>();
	
	/**
	 * add bet to the current game table
	 * must be called before spin
	 * @param b
	 */
	public void addBet(Bet b) {
		bets.add(b);
	}
	
	/**
	 * Spin the roulette wheel. Result from bets 
	 * can be obtained by calling getBetResult
	 */
	public void spin() {
		
	}
	
	/**
	 * Get result from previous spin. Must be called
	 * before the table is reset
	 * @return
	 */
	public List<BetResult> getBetResult() {
		return new ArrayList<BetResult>();
	}
	
	/**
	 * Reset the table and forget the bet results. Called 
	 * when the player wants to do more bets after a spin
	 */
	public void reset() {
		
	}
	
}
