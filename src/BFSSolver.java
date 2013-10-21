import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Attempts to solve a Sokoban puzzle with BFS
 * @author stephen
 * @uni szz2002
 *
 */
public class BFSSolver extends AbstractSolver {
	private LinkedList<BoardState> moveQueue;
	
	public BFSSolver(BoardState initialState) {
		super(initialState);
		moveQueue = new LinkedList<BoardState>();
	}
	
	@Override
	public String search() throws NoSolutionException {
		moveQueue.add(currentState);
		visited.add(currentState);
		while (!moveQueue.isEmpty()) {
			currentState = moveQueue.poll();

			if (currentState.isSolved()) {
                System.out.println(currentState);
				return backtrackMoves(currentState);
			}

			ArrayList<BoardState> validMoves = getValidMoves();
			for (BoardState move : validMoves) {
				backtrack.put(move, currentState);
				moveQueue.add(move);
				visited.add(currentState);
			}
		}
		throw new NoSolutionException();
	}
}
