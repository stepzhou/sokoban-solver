import java.util.EnumSet;
import java.util.HashMap;

public class BoardPoint {
	private static HashMap<Character, EnumSet<State>> charToState;
	private static HashMap<EnumSet<State>, Character> stateToChar;

	public enum State {
		PLAYER, WALL, BOX, GOAL, ERROR
	}
	
	private EnumSet<State> stateFields;
	
	public BoardPoint() {
		stateFields = EnumSet.noneOf(State.class);
	}
	
	public BoardPoint(State s1) {
		stateFields = EnumSet.of(s1);
	}
	
	public BoardPoint(State s1, State s2) {
		stateFields = EnumSet.of(s1, s2);
	}

	public void removeFlag(State flag) {
		stateFields.remove(flag);
	}
	
	public boolean has(State flag) {
		return stateFields.contains(flag);
	}
	
	public boolean equals(Object o) {
		BoardPoint other = (BoardPoint) o;
		return this.stateFields.equals(other.stateFields);
	}
	
	public static BoardPoint Error() {
		return new BoardPoint(State.ERROR);
	}
	
}
