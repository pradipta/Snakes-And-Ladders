package SnakesAndLadders;

import java.util.*;

public class Game {
	private Board snakeAndLadderBoard;
	private Boolean isComplete = false;
	private int numberOfPlayers;
	private Queue<Player> playersQueue;

	private static final int size = 100;

	public Game() {
		this.snakeAndLadderBoard = new Board();
		this.playersQueue = new LinkedList<>();
	}

	public void setPlayersQueue(List<Player> playersList) {
		this.numberOfPlayers = playersList.size();

		Map<String, Integer> positionMap = new HashMap<String, Integer>();

		for (Player eachPlayer : playersList) {
			this.playersQueue.add(eachPlayer);
			positionMap.put(eachPlayer.getId(), 0);
		}

		snakeAndLadderBoard.setPositionMap(positionMap);
	}

	public void setSnakesAndLadders(List<Snake> snakesList, List<Ladder> ladderList) {
		this.snakeAndLadderBoard.setSnakes(snakesList);
		this.snakeAndLadderBoard.setLadders(ladderList);
	}

	private int updatePositionIfSnakeOrLadder(int currentPosition) {
		int prePos;

		do {
			prePos = currentPosition;

			for (Snake snake : this.snakeAndLadderBoard.getSnakes()) {
				if (snake.getStart() == currentPosition) {
					currentPosition = snake.getEnd();
				}
			}

			for (Ladder ladder : this.snakeAndLadderBoard.getLadders()) {
				if (ladder.getStart() == currentPosition) {
					currentPosition = ladder.getEnd();
				}
			}
		} while (currentPosition != prePos);
		// return 0;
		return currentPosition;
	}

	private void makeAMove(Player nowPlaying, int numberOfSteps) {
		int currentPosition = this.snakeAndLadderBoard.getPositionMap().get(nowPlaying.getId());
		int newPosition = currentPosition + numberOfSteps;

		//TODO change Game.size to non static when using dynamic size
		if (newPosition > Game.size) {
			newPosition = currentPosition;
		} else {
			newPosition = updatePositionIfSnakeOrLadder(newPosition);
		}

		this.snakeAndLadderBoard.getPositionMap().put(nowPlaying.getId(), newPosition);
	}

	private int getDiceValue() {
		int diceTotalValue = 0;
		int diceNowValue = 0;
		do {
			diceNowValue = Dice.roll();
			diceTotalValue += diceNowValue;
		} while (diceNowValue == 6);
		return diceTotalValue;
	}

	public void playGame() {
		while (!isComplete) {
			int diceTotalValue = this.getDiceValue();
			Player nowPlaying = playersQueue.poll();
			System.out.println("Player: " + nowPlaying.getName() + " rolled a " + diceTotalValue);
			makeAMove(nowPlaying, diceTotalValue);
			System.out.println("Player: " + nowPlaying.getName() + " is now at position "
					+ this.snakeAndLadderBoard.getPositionMap().get(nowPlaying.getId()));
			//TODO change Game.size to non static when using dynamic size
			if (this.snakeAndLadderBoard.getPositionMap().get(nowPlaying.getId()) == Game.size) {
				System.out.println("Player: " + nowPlaying.getName() + " has won the game");
				this.isComplete = true;
				break;
			} else {
				playersQueue.add(nowPlaying);
			}
		}
	}
}
