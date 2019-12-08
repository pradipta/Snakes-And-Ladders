package SnakesAndLadders;

import java.util.*;

public class Board {
	private List<Snake> snakes;
	private List<Ladder> ladders;
	private Map<String, Integer> positionMap;
	private int size;
	
	public Board() {
		this.size = 100;
		this.snakes = new ArrayList<Snake>();
		this.ladders = new ArrayList<Ladder>();
		this.positionMap = new HashMap<String, Integer>();
	}

	public List<Snake> getSnakes() {
		return snakes;
	}

	public void setSnakes(List<Snake> snakes) {
		this.snakes = snakes;
	}

	public List<Ladder> getLadders() {
		return ladders;
	}

	public void setLadders(List<Ladder> ladders) {
		this.ladders = ladders;
	}

	public Map<String, Integer> getPositionMap() {
		return positionMap;
	}

	public void setPositionMap(Map<String, Integer> positionMap) {
		this.positionMap = positionMap;
	}
}
