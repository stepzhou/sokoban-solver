import java.util.LinkedList;


public class DFSSolver extends AbstractSolver {
	
	public DFSSolver(BoardState initialState) {
		super(initialState);
		queue = new LinkedList<BoardState>();
	}

	@Override
	protected void searchFunction(BoardState move) {
		backtrack.put(move, currentState);
		((LinkedList<BoardState>) queue).push(move);
		visited.add(currentState);
	}
}
