import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;


public abstract class AbstractSolver {
	protected BoardState currentState;
	protected HashSet<BoardState> visited;
	protected HashMap<BoardState, BoardState> backtrack;
	
	public AbstractSolver(BoardState initialState) {
		currentState = initialState;
		visited = new HashSet<BoardState>();
		backtrack = new HashMap<BoardState, BoardState>();
	}
	
	public abstract String search() throws NoSolutionException;
	
	protected ArrayList<BoardState> getValidMoves() {
        ArrayList<BoardState> validMoves = new ArrayList<BoardState>(4);
        addIfValid(validMoves, Direction.UP);
        addIfValid(validMoves, Direction.RIGHT);
        addIfValid(validMoves, Direction.DOWN);
        addIfValid(validMoves, Direction.LEFT);
        return validMoves;
    }

    protected void addIfValid(ArrayList<BoardState> moves, Point direction) {
        if (currentState.canMove(direction)) {
            BoardState newState = currentState.getMove(direction);
            if (!visited.contains(newState))
                moves.add(newState);
        }
    }
	
	protected String backtrackMoves(BoardState finalState) {
		LinkedList<Character> moveStack = new LinkedList<Character>();
		BoardState current = finalState;
		while (current.getDirection() != null) {
			char move = Direction.directionToChar(current.getDirection());
			moveStack.push(move);
            current = backtrack.get(current);
		}
		
		StringBuilder solution = new StringBuilder();
		String delim = "";
		for (Character move : moveStack) {
			solution.append(delim);
			solution.append(move);
			delim = ", ";
		}
		return solution.toString();
	}

}
