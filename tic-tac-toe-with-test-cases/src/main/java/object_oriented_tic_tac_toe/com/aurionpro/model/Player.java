package object_oriented_tic_tac_toe.com.aurionpro.model;

public class Player {
	private final String name;
    private final Mark mark;
    private int score = 0;

    public Player(String name, Mark mark) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public Mark getMark() {
        return mark;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
}
