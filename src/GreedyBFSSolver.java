import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class GreedyBFSSolver extends AbstractSolver {

	public GreedyBFSSolver(BoardState initialState) {
		super(initialState);
		queue = new PriorityQueue<BoardState>();
	}
	
	@Override
	protected void searchStart() {
		super.searchStart();
		Heuristics.manhattan(currentState);
	}

	@Override
	protected void searchFunction(ArrayList<BoardState> validMoves) {
		// TODO got to calculate the score of the initial state. maybe method in AS
		for (BoardState move : validMoves) {
			backtrack.put(move, currentState);
			Heuristics.manhattan(move);
			if (move.getCost() < currentState.getCost()) {
				queue.add(currentState);
				queue.add(move);
				break;
			} 
			queue.add(move);
		}
	}


}
