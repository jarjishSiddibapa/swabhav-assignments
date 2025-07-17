package object_oriented_tic_tac_toe.com.aurionpro.model;

public class Cell {
	private Mark mark = Mark.EMPTY;
	
	public Mark getMark() {
		return mark;
	}
	
	public void setMark(Mark newMark) {
		if (newMark == null) {
			throw new IllegalArgumentException("Mark must not be null.");
		}
		
		if (mark == Mark.EMPTY) {
			mark = newMark;
			return;
		}
		
		throw new IllegalStateException("Cell is already marked!");
	}
	
	public void reset() {
		this.mark = Mark.EMPTY;
	}
}
