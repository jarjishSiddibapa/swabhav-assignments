package object_oriented_tic_tac_toe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import object_oriented_tic_tac_toe.com.aurionpro.model.Cell;
import object_oriented_tic_tac_toe.com.aurionpro.model.Mark;

public class CellTest {

//	Test 1 -> Created cell must be empty.
	@Test
	void newCellShouldBeEmpty() {
		Cell cell = new Cell();
		assertEquals(Mark.EMPTY, cell.getMark(), "A newly created cell must be EMPTY.");
	}

// Test 2 -> Should be able to mark X or O to a cell.
	@Test
	void shouldAllowMarkX() {
		Cell cell = new Cell();
		cell.setMark(Mark.X);
		assertEquals(Mark.X, cell.getMark(), "Marking an EMPTY cell with X should set its mark to X.");
	}

	@Test
	void shouldAllowMarkO() {
		Cell cell = new Cell();
		cell.setMark(Mark.O);
		assertEquals(Mark.O, cell.getMark(), "Marking an EMPTY cell with O should set its mark to O.");
	}

//	Test 3 -> If the cell is marked with either X or O, it should not be allowed to mark again.
	@Test
	void markingNonEmptyCellThrowsException() {
		Cell cell = new Cell();
		cell.setMark(Mark.X);
		assertThrows(IllegalStateException.class, () -> cell.setMark(Mark.O),
				"Once marked, a cell must not be markable again.");
	}
}