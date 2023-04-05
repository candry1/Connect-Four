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

	//------------------------------------------------------------------------------------------------------------------

//	@Test
//	void GameButtonTest() {
//		GameButton b = new GameButton("red", 2, 4);
//
//		assertEquals(b.enabled, false,  "incorrect default value for enabled");
//	}

//	@Test
//	void GameButtonTest2() {
//		GameButton b = new GameButton("red", 2, 4);
//
//		assertEquals(b.col, 4,  "incorrect default value for col");
//	}
//
//	@Test
//	void GameButtonTest3() {
//		GameButton b = new GameButton("red", 2, 4);
//
//		assertEquals(b.row, 2,  "incorrect default value for row");
//	}
//
//	@Test
//	void GameButtonTest4() {
//		GameButton b = new GameButton("red", 2, 4);
//
//		assertEquals(b.color, "red",  "incorrect default value for color");
//	}
//
//	@Test
//	void GameButtonTest5() {
//		GameButton b = new GameButton("red", 2, 4);
//		b.player = 1;
//
//		assertEquals(b.player, 1,  "incorrect value for player");
//	}
//
//	@Test
//	void GameButtonTest6() {
//		GameButton b = new GameButton("red", 2, 4);
//		b.color = "brown";
//
//		assertEquals(b.color, "brown",  "value for color didn't change correctly");
//	}
//
//	@Test
//	void GameButtonTest7() {
//		GameButton b = new GameButton("red", 2, 4);
//		b.row = 33;
//
//		assertEquals(b.row, 33,  "value for row didn't change correctly");
//	}
//
//	@Test
//	void GameButtonTest8() {
//		GameButton b = new GameButton("red", 2, 4);
//		b.col = 22;
//
//		assertEquals(b.col, 22,  "value for col didn't change correctly");
//	}
//
//	@Test
//	void GameButtonTest9() {
//		GameButton b = new GameButton("red", 2, 4);
//		b.enabled = true;
//
//		assertEquals(b.enabled, true,  "value for enabled didn't change correctly");
//	}
//
//	@Test
//	void GameButtonTest10() {
//		GameButton b = new GameButton("red", 2, 4);
//		b.col = 4;
//		b.row = 4;
//
//		assertEquals(b.col, b.row,  "value for col and row didn't change correctly");
//	}
//
//	@Test
//	void GameButtonTest11() {
//		GameButton b = new GameButton("red", 2, 4);
//		GameButton c = b;
//		c.player = 4;
//		b.player = 2;
//
//		assertEquals(b.player, 2,  "values for c and b players didn't change correctly, they're still connected");
//	}
}
