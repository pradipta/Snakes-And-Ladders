package SnakesAndLadders;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of players: ");
		int numberOfPlayers = scanner.nextInt();
		
		if (numberOfPlayers < 2 || numberOfPlayers > 4) {
			System.out.println("Should be between 2 and 4");
			scanner.close();
			return;
		}
	
		scanner.close();

		List<Player> playersList = new ArrayList<>();

		for (int i = 0; i < numberOfPlayers; i++) {
			String name = "Player" + String.valueOf(i + 1);
			String id = String.valueOf(i + 1);
			playersList.add(new Player(id, name));
		}

		// System.out.println("Enter number of ladders: ");
		int numberOfLadders = 3;
		int numberOfSnakes = 3;

		Map<Integer, Boolean> snakePresent = new HashMap<>();
		Map<Integer, Boolean> ladderPresent = new HashMap<>();
		
		snakePresent.put(0, false);
		ladderPresent.put(0, false);
		int currentLadderCount = 0;
		int currentSnakeCount = 0;

		List<Ladder> ladders = new ArrayList<>();
		List<Snake> snakes = new ArrayList<>();

		while (currentLadderCount < numberOfLadders) {
			//System.out.println("Enter start for ladder " + (currentLadderCount + 1));
			int start = new Random().nextInt(100)+1;
			//System.out.println("Enter end for ladder " + (currentLadderCount + 1) +"(greater than start)");
			int end = -1;
			while (end<=start) {
				end = new Random().nextInt(100)+1;
			}

			
			if (snakePresent.containsKey(start) || snakePresent.containsKey(end) || ladderPresent.containsKey(start) || ladderPresent.containsKey(end)) {
				System.out.println("A ladder or a snake is already present, try again");
				continue;
			}
			
			ladderPresent.put(start, true);
			ladderPresent.put(end, true);
			
			ladders.add(new Ladder(start, end));
			//System.out.println("List: "+ladders.get(0).getStart());
			currentLadderCount++;
		}
		//System.out.println("Here");
		while (currentSnakeCount < numberOfSnakes) {
			//System.out.println("Enter start for snake " + (currentSnakeCount + 1));
			//System.out.println("Here");
			int start = new Random().nextInt(100)+1;
			//System.out.println("Enter end for snake (less than start) " + (currentSnakeCount + 1)+"(greater than start)");
			int end = -1;
			while (end<=start) {
				end = new Random().nextInt(100)+1;
			}
			// put check for existing
			
			if (snakePresent.containsKey(start) || snakePresent.containsKey(end) || ladderPresent.containsKey(start) || ladderPresent.containsKey(end)) {
				System.out.println("A ladder or a snake is already present, try again");
				continue;
			}
			snakePresent.put(start, true);
			snakePresent.put(end, true);
			
			snakes.add(new Snake(start, end));
			currentSnakeCount++;
		}
		System.out.println("Ladders: "+currentLadderCount);
		System.out.println("Snakes: "+currentSnakeCount);
		for (Ladder ladder : ladders) {
			System.out.println ("Ladder start: "+ladder.getStart()+" end: "+ladder.getEnd());
		}
		
		for (Snake snake : snakes) {
			System.out.println ("Snake start: "+snake.getStart()+" end: "+snake.getEnd());
		}

		Game newGame = new Game();
		newGame.setPlayersQueue(playersList);
		newGame.setSnakesAndLadders(snakes, ladders);

		newGame.playGame();
	}

}
