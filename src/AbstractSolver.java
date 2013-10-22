import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Abstract solver class with base search functionality
 * @author Stephen Zhou
 * @uni szz2002
 *
 */
public abstract class AbstractSolver {
	protected BoardState currentState;
	protected HashSet<BoardState> visited;
	protected HashMap<BoardState, BoardState> backtrack;
	protected Queue<BoardState> queue;
	
	private long startTime;
	private long endTime;
	private int previouslySeen;
	private int nodesExplored;
	
	public AbstractSolver(BoardState initialState) {
		currentState = initialState;
		visited = new HashSet<BoardState>();
		backtrack = new HashMap<BoardState, BoardState>();
		startTime = endTime = -1;
		previouslySeen = nodesExplored = 0;
	}
	
	/**
	 * Searches the Sokoban puzzle for solution and returns the move sequence
	 * @return the Sokoban solution move sequence
	 * @throws NoSolutionException if no solution is found
	 */
	public String search() throws NoSolutionException {
		startTimer();
		searchStart();
		while (!queue.isEmpty()) {
			currentState = queue.poll();
			visited.add(currentState);
			nodesExplored++;

			if (currentState.isSolved()) {
				System.out.println(currentState);
				String solution = backtrackMoves(currentState);
				stopTimer();
				return solution;
			}

			ArrayList<BoardState> validMoves = getValidMoves();
			searchFunction(validMoves);
		}
		throw new NoSolutionException();
	}
	
	protected void searchStart() {
		queue.add(currentState);
	}
	
	protected abstract void searchFunction(ArrayList<BoardState> validMoves);
	
	/**
	 * Returns the valid moves from the current state.
	 * @return the valid moves from the current state.
	 */
	protected ArrayList<BoardState> getValidMoves() {
        ArrayList<BoardState> validMoves = new ArrayList<BoardState>(4);
        addIfValid(validMoves, Direction.UP);
        addIfValid(validMoves, Direction.RIGHT);
        addIfValid(validMoves, Direction.DOWN);
        addIfValid(validMoves, Direction.LEFT);
        return validMoves;
    }
	
	/**
	 * Backtracks through the search to find the move sequence
	 * @param finalState the final, goal state
	 * @return the Sokoban solution move sequence
	 */
    protected String backtrackMoves(BoardState finalState) {
    	// Backtracking solutions and adding moves to stack
		LinkedList<Character> moveStack = new LinkedList<Character>();
		BoardState current = finalState;
		while (current.getDirectionTaken() != null) {
			char move = Direction.directionToChar(current.getDirectionTaken());
			moveStack.push(move);
            current = backtrack.get(current);
		}
		
		// Comma delimiting solution
		StringBuilder solution = new StringBuilder();
		String delim = "";
		for (Character move : moveStack) {
			solution.append(delim);
			solution.append(move);
			delim = ", ";
		}
		return solution.toString();
	}
    
    protected void startTimer() {
    	startTime = System.currentTimeMillis();
    	endTime = System.currentTimeMillis();
    }
    
    protected void stopTimer() {
    	endTime = System.currentTimeMillis();
    }
    
    public long getElapsedTimeMillis() {
    	return endTime - startTime;
    }
    
    public int getVisitedLength() {
    	return visited.size();
    }
    
    public int getQueueLength() {
    	return queue.size();
    }
    
    public int getNodesExplored() {
    	return nodesExplored;
    }
    
    public int getPreviouslySeen() {
    	return previouslySeen;
    }

	private void addIfValid(ArrayList<BoardState> moves, Point direction) {
	    if (currentState.canMove(direction)) {
	        BoardState newState = currentState.getMove(direction);
	        if (!visited.contains(newState))
	            moves.add(newState);
	        else
	        	previouslySeen++;
	    }
	}

}
