package com.example.dojo.tennis;

import lombok.Getter;

import java.util.*;

import static com.example.dojo.tennis.Player.PLAYER1;
import static com.example.dojo.tennis.Player.PLAYER2;

public class TennisGame {
	@Getter
	private Map<Player, String> score = new HashMap();
	public final LinkedList<String> pointList = new LinkedList<>(
			List.of("love", "15", "30", "40", "DEUCE", "ADVANTAGE"));
	private Player winner;

	public TennisGame() {
		score.put(PLAYER1, pointList.getFirst());
		score.put(PLAYER2, pointList.getFirst());
	}

	public void updateScore(Player player) {
		Player other = Arrays.stream(Player.values()).filter(element -> element != player).findFirst().orElseThrow();
		if (!scoreOfPlayer(player).equals(scoreOfPlayer(other)) && (scoreOfPlayer(player).equals("40"))) {
			this.winner = player;
			return;
		}

		if (("DEUCE".equals(scoreOfPlayer(player)) && "ADVANTAGE".equals(scoreOfPlayer(other)))
			|| ("30".equals(scoreOfPlayer(player)) && "40".equals(scoreOfPlayer(other)))){
			score.replace(other, scoreOfPlayer(other), "DEUCE");
			score.replace(player, scoreOfPlayer(player), "DEUCE");
			return;
		}
		if ("DEUCE".equals(scoreOfPlayer(player)) && "DEUCE".equals(scoreOfPlayer(other))) {
			score.replace(player, scoreOfPlayer(player), "ADVANTAGE");
			return;
		}

		pointList.stream().sorted()
				.filter(point -> scoreOfPlayer(player).equalsIgnoreCase(point))
				.map(pointList::indexOf).findFirst()
				.map(index -> score.replace(player, pointList.get(index), pointList.get(index + 1)));
	}

	public String scoreOfPlayer1() {
		return scoreOfPlayer(PLAYER1);
	}

	public String scoreOfPlayer2() {
		return scoreOfPlayer(PLAYER2);
	}

	public String scoreOfPlayer(Player player) {
		return score.get(player);
	}

	public Player getWinner() {
		return this.winner;
	}

	/**
	 *
	 * @param initialScore
	 * ex : "love love"
	 */
	public void setScore(String initialScore) {
		String score1 = initialScore.split(" ")[0];
		String score2 = initialScore.split(" ")[1];
		score.replace(PLAYER1, score1);
		score.replace(PLAYER2, score2);
	}
}