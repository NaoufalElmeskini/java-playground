package com.example.dojo.tennis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.example.dojo.tennis.Player.PLAYER1;
import static com.example.dojo.tennis.Player.PLAYER2;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * probleme : https://codingdojo.org/kata/Tennis/
 *
 * This Kata is about implementing a simple tennis game.
 * I came up with it while thinking about Wii tennis, where they have simplified tennis, so each set is one game.
 *
 * The scoring system is rather simple:
 *
 * domain : Game, Win, Advantage, Deuce, Score
 *
 * Each player can have either of these points in one game “love” “15” “30” “40”
 * If you have 40 and you win the point you win the game, however there are special rules.
 * If both have 40 the players are “deuce”.
 * If the game is in deuce, the winner of a point will have advantage
 * If the player with advantage wins the ball he wins the game
 * If the player without advantage wins they are back at deuce.
 * Alternate description of the rules per Wikipedia (http://en.wikipedia.org/wiki/Tennis#Scoring ):
 *
 * A game is won by the first player to have won at least four points in total and at least two points more than the opponent.
 * The running score of each game is described in a manner peculiar to tennis: scores from zero to three points are described as
 * 		“love”, “15”, “30”, and “40” respectively.
 * If at least three points have been scored by each player, and the scores are equal, the score is “deuce”.
 * If at least three points have been scored by each side and a player has one more point than his opponent, the score of the game is “advantage” for the player in the lead.
 */
public class TennisGameTest {
	// X(input): output
	// updateScore(scorer): "player1: (SCORE1), player2: (SCORE2)"
	/**
	 * ex : score = {player1: love, player2: love},
	 * 	getScore() => score = {player1: love, player2: love}
	 * 	updateScore(player1) => score = {player1: love, player2: love}
	 * 	updateScore(player1) => score = {player1: 15, player2: love}
	 * 	updateScore(player1) => score = {player1: 30, player2: love}
	 * 	updateScore(player1) => score = {player1: 40, player2: love}
	 * 	updateScore(player1) => (game.winner = player1)
	 */

	@Test
	public void shouldInitiateScoresAtLove() {
		//    when
		TennisGame game = new TennisGame();
		//    then
		assertThat(game.scoreOfPlayer1()).isEqualTo("love");
		assertThat(game.scoreOfPlayer2()).isEqualTo("love");
	}
	@ParameterizedTest
	@CsvSource({
			"love love, PLAYER1, 15 love",
			"15 love, PLAYER1, 30 love",
			"30 love, PLAYER1, 40 love",
	}
	)
	public void shouldUpdateScore(String initialScore, String playerWhoScored, String expectedScore) {
		TennisGame game = new TennisGame();
		game.setScore(initialScore);

		String expectedPlayer1Score = expectedScore.split(" ")[0];
		String expectedPlayer2Score = expectedScore.split(" ")[1];
		//    when
		game.updateScore(Player.valueOf(playerWhoScored));
		//    then
		assertThat(game.scoreOfPlayer1()).isEqualTo(expectedPlayer1Score);
		assertThat(game.scoreOfPlayer2()).isEqualTo(expectedPlayer2Score);
	}

	@Test
	public void shouldUpdateScoreTo15FromLove() {
		TennisGame game = new TennisGame();
		//    when
		game.updateScore(PLAYER1);
		//    then
		assertThat(game.scoreOfPlayer1()).isEqualTo("15");
		assertThat(game.scoreOfPlayer2()).isEqualTo("love");
	}
	@Test
	public void shouldUpdateScoreTo30From15() {
		TennisGame game = new TennisGame();
		//    when
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER1);
		//    then
		assertThat(game.scoreOfPlayer1()).isEqualTo("30");
		assertThat(game.scoreOfPlayer2()).isEqualTo("love");
	}

	@Test
	public void shouldUpdateScoreTo40From30() {
		TennisGame game = new TennisGame();
		//    when
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER1);
		//    then
		assertThat(game.scoreOfPlayer1()).isEqualTo("40");
		assertThat(game.scoreOfPlayer2()).isEqualTo("love");
	}

	@Test
	public void shouldWinGameFrom40() {
		TennisGame game = new TennisGame();
		//    when
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER1);
		//    then
		assertThat(game.getWinner()).isEqualTo(PLAYER1);
	}

	@Test
	public void shouldUpdatePlayer2To15GivenPlayer1At40() {
		TennisGame game = new TennisGame();
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER1);
		//    when
		game.updateScore(PLAYER2);
		//    then
		assertThat(game.scoreOfPlayer1()).isEqualTo("40");
		assertThat(game.scoreOfPlayer2()).isEqualTo("15");
	}

	@Test
	public void shouldUpdatePlayersToDEUCEGivenPlayer1At40() {
		TennisGame game = new TennisGame();
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER1);
		//    when
		game.updateScore(PLAYER2);
		game.updateScore(PLAYER2);
		game.updateScore(PLAYER2);
		//    then
		assertThat(game.scoreOfPlayer1()).isEqualTo("DEUCE");
		assertThat(game.scoreOfPlayer2()).isEqualTo("DEUCE");
	}

	@Test
	public void shouldUpdatePlayersToDEUCEGivenPlayer2At40() {
		TennisGame game = new TennisGame();
		updateToDEUCE(game);
		//    then
		assertThat(game.scoreOfPlayer1()).isEqualTo("DEUCE");
		assertThat(game.scoreOfPlayer2()).isEqualTo("DEUCE");
	}

	@Test
	public void shouldUpdatePlayerToAdvantageGivenPlayersAtDeuce() {
		TennisGame game = new TennisGame();
		updateToDEUCE(game);
		//    when
		game.updateScore(PLAYER1);
		//    then
		assertThat(game.scoreOfPlayer1()).isEqualTo("ADVANTAGE");
		assertThat(game.scoreOfPlayer2()).isEqualTo("DEUCE");
	}

	@Test
	public void shouldDeuceFromAdvantage() {
		TennisGame game = new TennisGame();
		updateToDEUCE(game);
		// 	when
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER2);
		//    then
		assertThat(game.getWinner()).isNull();
		assertThat(game.scoreOfPlayer1()).isEqualTo("DEUCE");
		assertThat(game.scoreOfPlayer2()).isEqualTo("DEUCE");
	}

	private static void updateToDEUCE(TennisGame game) {
		game.updateScore(PLAYER2);
		game.updateScore(PLAYER2);
		game.updateScore(PLAYER2);
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER1);
		game.updateScore(PLAYER1);
	}
}
