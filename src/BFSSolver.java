import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;


/**
 * Attempts to solve a Sokoban puzzle with BFS
 * @author stephen
 * @uni szz2002
 *
 */
public class BFSSolver {
	private BoardState currentState;
	private HashSet<BoardState> visited;
	private LinkedList<BoardState> moveQueue;
	private HashMap<BoardState, BoardState> backtrack;
	
	public BFSSolver(BoardState initialState) {
		currentState = initialState;
		visited = new HashSet<BoardState>();
		moveQueue = new LinkedList<BoardState>();
		backtrack = new HashMap<BoardState, BoardState>();
	}
	
	// TODO: Returning a sequence of directions as a result
	public String search() throws NoSolutionException {
		moveQueue.add(currentState);
		while (!moveQueue.isEmpty()) {
			currentState = moveQueue.poll();
			if (currentState.isSolved()) {
                System.out.println(currentState);
				return backtrackMoves(currentState);
			}

			visited.add(currentState);

			ArrayList<BoardState> validMoves = getValidMoves();
			for (BoardState move : validMoves) {
				backtrack.put(move, currentState);
				moveQueue.add(move);
			}
		}
		throw new NoSolutionException();
	}
	
    private ArrayList<BoardState> getValidMoves() {
        ArrayList<BoardState> validMoves = new ArrayList<BoardState>(4);
        addIfValid(validMoves, BoardState.UP);
        addIfValid(validMoves, BoardState.RIGHT);
        addIfValid(validMoves, BoardState.DOWN);
        addIfValid(validMoves, BoardState.LEFT);
        return validMoves;
    }

    private void addIfValid(ArrayList<BoardState> moves, Point direction) {
        if (currentState.canMove(direction)) {
            BoardState newState = currentState.getMove(direction);
            if (!visited.contains(newState))
                moves.add(newState);
        }
    }
	
	private String backtrackMoves(BoardState finalState) {
		LinkedList<Character> moveStack = new LinkedList<Character>();
		BoardState current = finalState;
		while (current.getDirection() != null) {
			char move = getDirectionChar(current.getDirection());
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
	
	private char getDirectionChar(Point direction) {
		if (direction.equals(BoardState.UP))
			return 'u';
		else if (direction.equals(BoardState.RIGHT))
			return 'r';
		else if (direction.equals(BoardState.DOWN))
			return 'd';
		else if (direction.equals(BoardState.LEFT))
			return 'l';
		else
			throw new IllegalStateException("Non-existent direction: " 
					+ direction);
	}
	
}
