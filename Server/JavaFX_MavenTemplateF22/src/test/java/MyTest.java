import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	@Test
	void CFourInfoTest() {
		CFourInfo gameInfo = new CFourInfo();

		assertEquals(gameInfo.message, "", "incorrect default value for message");
	}

	@Test
	void CFourInfoTest2() {
		CFourInfo gameInfo = new CFourInfo();

		assertEquals(gameInfo.p1Turn, true, "incorrect default value for p1turn");
	}

	@Test
	void CFourInfoTest3() {
		CFourInfo gameInfo = new CFourInfo();

		assertEquals(gameInfo.p2Turn, false, "incorrect default value for p2turn");
	}

	@Test
	void CFourInfoTest4() {
		CFourInfo gameInfo = new CFourInfo();

		assertEquals(gameInfo.has2Players, false, "incorrect default value for has2Players");
	}

	@Test
	void CFourInfoTest5() {
		CFourInfo gameInfo = new CFourInfo();

		assertEquals(gameInfo.hasWinner, false, "incorrect default value for hasWinner");
	}

	@Test
	void CFourInfoTest6() {
		CFourInfo gameInfo = new CFourInfo();

		assertEquals(gameInfo.winner, 0, "incorrect default value for winner");
	}

	@Test
	void CFourInfoTest7() {
		CFourInfo gameInfo = new CFourInfo();

		assertEquals(gameInfo.row, -1, "incorrect default value for row");
	}

	@Test
	void CFourInfoTest8() {
		CFourInfo gameInfo = new CFourInfo();

		assertEquals(gameInfo.col, -1, "incorrect default value for col");
	}

	@Test
	void CFourInfoTest9() {
		CFourInfo gameInfo = new CFourInfo();

		assertEquals(gameInfo.playerCount, 0, "incorrect default value for playerCount");
	}

	@Test
	void CFourInfoTest10() {
		CFourInfo gameInfo = new CFourInfo();
		gameInfo.playerCount = 5;

		assertEquals(gameInfo.playerCount, 5, "value for playerCount didn't change correctly");
	}

	@Test
	void CFourInfoTest11() {
		CFourInfo gameInfo = new CFourInfo();
		gameInfo.has2Players = true;

		assertEquals(gameInfo.has2Players, true, "value for has2Players didn't change correctly");
	}

	@Test
	void CFourInfoTest12() {
		CFourInfo gameInfo = new CFourInfo();
		gameInfo.p2Turn = true;

		assertEquals(gameInfo.p2Turn, true, "value for p2Turn didn't change correctly");
	}

	@Test
	void CFourInfoTest13() {
		CFourInfo gameInfo = new CFourInfo();
		gameInfo.id = 5;

		assertEquals(gameInfo.id, 5, "value for id wasn't set correctly");
	}

	@Test
	void CFourInfoTest14() {
		CFourInfo gameInfo = new CFourInfo();
		gameInfo.row = 3;

		assertEquals(gameInfo.row, 3, "value for row didn't change correctly");
	}

	@Test
	void CFourInfoTest15() {
		CFourInfo gameInfo = new CFourInfo();
		gameInfo.col = 3;

		assertEquals(gameInfo.col, 3, "value for col didn't change correctly");
	}

	@Test
	void CFourInfoTest16() {
		CFourInfo gameInfo = new CFourInfo();
		CFourInfo gameInfo2 = gameInfo;
		gameInfo2.id = 2;
		gameInfo.id = 1;

		assertEquals(gameInfo.id, 1, "value for gameInfo's id isn't seperate from gameInfo2's information");
	}

}
