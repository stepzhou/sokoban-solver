import java.util.LinkedList;


/**
 * Attempts to solve a Sokoban puzzle with BFS
 * @author stephen
 * @uni szz2002
 *
 */
public class BFSSolver extends AbstractSolver {
	
	public BFSSolver(BoardState initialState) {
		super(initialState);
		queue = new LinkedList<BoardState>();
	}
	
	@Override
	protected void searchFunction(BoardState move) {
		backtrack.put(move, currentState);
		queue.add(move);
		visited.add(currentState);
	}
}
