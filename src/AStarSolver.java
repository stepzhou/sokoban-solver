import java.awt.Point;
import java.util.Collection;
import java.util.PriorityQueue;


public class AStarSolver extends AbstractSolver {
	public AStarSolver(BoardState initialBoard) {
		super(initialBoard);
		queue = new PriorityQueue<BoardState>();
	}

	@Override
	protected void searchFunction(BoardState move) {
		backtrack.put(move, currentState);
		manhattanScore(move);
		queue.add(move);
	}
	
	private void manhattanScore(BoardState state) {
		Collection<Point> goals = state.getGoals();
		Collection<Point> boxes = state.getBoxes();
		int cost = 0;
		for (Point box : boxes) {
			int minMarginalCost = Integer.MAX_VALUE;
			for (Point goal : goals) {
				int dist = getManhattanDistance(box, goal);
				if (dist < minMarginalCost)
					minMarginalCost = dist;
			}
			cost += minMarginalCost;
		}
		state.setCost(cost);
	}
	
	private int getManhattanDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}
