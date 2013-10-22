import java.util.LinkedList;

/**
 * Attempts to solve a Sokoban puzzle with DFS
 * @author Stephen Zhou
 * @uni szz2002
 *
 */
public class DFSSolver extends AbstractSolver {
	
	public DFSSolver(BoardState initialState) {
		super(initialState);
		queue = new LinkedList<BoardState>();
	}

	@Override
	protected void searchFunction(BoardState move) {
		backtrack.put(move, currentState);
		((LinkedList<BoardState>) queue).push(move);
	}
}
