import java.util.ArrayList;
import java.util.PriorityQueue;


public class UniformCostSolver extends AbstractSolver {
	private PriorityQueue<BoardState> frontier;
	
	public UniformCostSolver(BoardState initialState) {
		super(initialState);
		frontier = new PriorityQueue<BoardState>(11, new UniformCostComparator());
	}

	@Override
	public String search() throws NoSolutionException {
		frontier.add(currentState);
		visited.add(currentState);
		while (!frontier.isEmpty()) {
			currentState = frontier.poll();
			
			if (currentState.isSolved()) {
				System.out.println(currentState);
				return backtrackMoves(currentState);
			}
			
			ArrayList<BoardState> validMoves = getValidMoves();
			for (BoardState move : validMoves) {
				backtrack.put(move, currentState);
				setBoardStateCost(move, currentState.getCost());
				frontier.add(move);
				visited.add(currentState);
			}
		}
		throw new NoSolutionException();
	}
	
	private void setBoardStateCost(BoardState state, int baseCost) {
		if (state.nextMoveHas(BoardState.BOX))
			state.setCost(baseCost + 2);
		else
			state.setCost(baseCost + 1);
	}
}
