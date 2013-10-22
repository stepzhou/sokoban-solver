import java.util.ArrayList;
import java.util.PriorityQueue;


public class AStarSolver extends AbstractSolver {
	public AStarSolver(BoardState initialBoard) {
		super(initialBoard);
		queue = new PriorityQueue<BoardState>();
	}

	@Override
	protected void searchFunction(ArrayList<BoardState> validMoves) {
		for (BoardState move : validMoves) {
			backtrack.put(move, currentState);
			Heuristics.manhattan(move);
			queue.add(move);
		}
	}
}
