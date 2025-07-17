package object_oriented_tic_tac_toe.com.aurionpro.model;

import java.util.Arrays;
import java.util.List;

public class Board {
	private final Cell[] cells = new Cell[9];

	private static final List<int[]> WIN_PATTERNS = Arrays.asList(new int[] { 0, 1, 2 }, new int[] { 3, 4, 5 },
			new int[] { 6, 7, 8 }, // rows
			new int[] { 0, 3, 6 }, new int[] { 1, 4, 7 }, new int[] { 2, 5, 8 }, // columns
			new int[] { 0, 4, 8 }, new int[] { 2, 4, 6 } // diagonals
	);

	public Board() {
		for (int i = 0; i < 9; i++) {
			cells[i] = new Cell();
		}
	}

	public String display() {
		StringBuilder boardDisplay = new StringBuilder();

		for (int cellIndex = 0; cellIndex < 9; cellIndex++) {
			Mark currentMark = cells[cellIndex].getMark();

			// If cell is empty, show the position number (1 to 9)
			if (currentMark == Mark.EMPTY) {
				boardDisplay.append(cellIndex + 1);
			} else {
				boardDisplay.append(currentMark); // Either X or O
			}

			// Add formatting based on the position
			boolean isEndOfRow = (cellIndex + 1) % 3 == 0;
			boolean isLastCell = cellIndex == 8;

			if (isEndOfRow && !isLastCell) {
				boardDisplay.append("\n---------\n");
			}
			
			if (!isEndOfRow) {
				boardDisplay.append(" | ");
			}
		}

		return boardDisplay.toString();
	}

//	marking a position by index.
	public void markPosition(int position, Mark mark) {
		if (position < 1 || position > 9) {
			throw new IllegalArgumentException("Position must be between 1 & 9.");
		}
		cells[position - 1].setMark(mark);
	}
	
//	return a mark at a given position.
	public Mark getMark(int position) {
		if (position < 1 || position > 9) {
			throw new IllegalArgumentException("Position must be between 1 & 9.");
		}
		return cells[position - 1].getMark();
	}
	
	
//	Returns true if there is a winning pattern (three Xs or three Os in a row).
	public boolean hasWinner() {
	    for (int[] winningPattern : WIN_PATTERNS) {
	        int firstIndex = winningPattern[0];
	        int secondIndex = winningPattern[1];
	        int thirdIndex = winningPattern[2];

	        Mark firstMark = cells[firstIndex].getMark();
	        Mark secondMark = cells[secondIndex].getMark();
	        Mark thirdMark = cells[thirdIndex].getMark();

	        // Check if all three marks are the same and not EMPTY
	        if (firstMark != Mark.EMPTY &&
	            firstMark == secondMark &&
	            firstMark == thirdMark) {
	            return true;  // A winning pattern is found
	        }
	    }
	    return false;  // No winning pattern found
	}

// Returns the winning mark (X or O). Call after hasWinner to get the Winner's mark!
	public Mark getWinner() {
	    for (int[] winningPattern : WIN_PATTERNS) {
	        int firstIndex = winningPattern[0];
	        int secondIndex = winningPattern[1];
	        int thirdIndex = winningPattern[2];

	        Mark firstMark = cells[firstIndex].getMark();
	        Mark secondMark = cells[secondIndex].getMark();
	        Mark thirdMark = cells[thirdIndex].getMark();

	        // If all three marks are same and not empty, it's a win
	        if (firstMark != Mark.EMPTY &&
	            firstMark == secondMark &&
	            firstMark == thirdMark) {
	            return firstMark; // Either X or O
	        }
	    }

	    // Should never reach here if hasWinner() was true
	    throw new IllegalStateException("No winner on board");
	}
	
//	
	public boolean isFull() {
		for (Cell cell: cells) {
			if (cell.getMark() == Mark.EMPTY) return false; // this means that at least one cell is available.
		}
		
		return true; // we reach to this part only if all the cells are occupied.
	}
	
	public void reset() {
		for (Cell cell: cells) {
			cell.reset();
		}
	}
	
}

 