
/**
 * Command interface for varying heuristics in greedy BFS and A*
 * 
 * All implementing classes:
 * 		BoxGoalHeuristic
 * 		ManhattanHeuristic
 * @author Stephen Zhou
 * @uni szz2002
 *
 */
public interface Heuristic {
	
	public void score(BoardState state);

}
