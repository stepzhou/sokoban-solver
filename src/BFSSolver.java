import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;


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
	
	public BFSSolver(BoardState initialState) {
		currentState = initialState;
		visited = new HashSet<BoardState>();
		moveQueue = new LinkedList<BoardState>();
	}
	
	// TODO: Returning a sequence of directions as a result
	public void search() {
		moveQueue.add(currentState);
		while (!moveQueue.isEmpty()) {
			currentState = moveQueue.poll();
			if (currentState.isSolved()) {
				System.out.println(currentState);
				return;
			}
			visited.add(currentState);
			enqueueValidTransitions();
		}
	}
	
	/**
	 * Gets the player's next set of possible moves
	 * @return the possible moves
	 */
	private void enqueueValidTransitions() {
		enqueueMove(BoardState.UP);
		enqueueMove(BoardState.RIGHT);
		enqueueMove(BoardState.DOWN);
		enqueueMove(BoardState.LEFT);
	}
	
	private void enqueueMove(Point direction) {
		if (currentState.canMove(direction)) {
			BoardState newState = currentState.getMove(direction);
			if (!visited.contains(newState))
				moveQueue.add(newState);
		}
	}
}
