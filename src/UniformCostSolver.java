import java.util.PriorityQueue;

public class UniformCostSolver extends AbstractSolver {

	public UniformCostSolver(BoardState initialState) {
		super(initialState);
		queue = new PriorityQueue<BoardState>(11,
				new UniformCostComparator());
	}

	@Override
	protected void searchFunction(BoardState move) {
		backtrack.put(move, currentState);
		updateBoardStateCost(move, currentState.getCost());
		queue.add(move);
		visited.add(currentState);
	}

	private void updateBoardStateCost(BoardState state, int baseCost) {
		if (state.nextMoveHas(BoardState.BOX))
			state.setCost(baseCost + 2);
		else
			state.setCost(baseCost + 1);
	}
}
