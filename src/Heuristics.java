import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class Heuristics {
	public static void manhattan(BoardState state) {
		Set<Point> goals = state.getGoals();
		Set<Point> boxes = state.getBoxes();
		
		// Boxes on a goal are cost 0 so don't check them
		Set<Point> intersection = new HashSet<Point>(goals);
		intersection.retainAll(boxes);
		goals.removeAll(intersection);
		boxes.removeAll(intersection);
		
		// TODO optimize to recalculate only one box's delta score if it has
		// been pushed. Although for complicated puzzles (like Kristi), this
		// is not a bottleneck
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
	
	private static int getManhattanDistance(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
	
	public static void boxesOnGoals(BoardState state) {
		
	}
}
