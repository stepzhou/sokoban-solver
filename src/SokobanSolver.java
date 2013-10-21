import java.io.IOException;


/**
 * A shoddy command line interface for solving Sokoban with:
 * - BFS
 * - DFS
 * - Uniform cost search
 * - Greedy best first search
 * - A* search
 * 
 * @author Stephen Zhou
 * @uni szz2002
 *
 */
public class SokobanSolver {
	public SokobanSolver() {
	}
	
	public void parseArguments(String[] args) {
		try {
			String flag = args[0];
			String puzzlePath = args[1];
			BoardState initialBoard = BoardState.parseBoardInput(puzzlePath);
			System.out.println(initialBoard);
			if (flag.equals("-b")) {
				BFSSolver bfs = new BFSSolver(initialBoard);
				String solution = bfs.search();
				System.out.println(solution);
			}
			else if (flag.equals("-d")) {
				DFSSolver dfs = new DFSSolver(initialBoard);
				String solution = dfs.search();
				System.out.println(solution);
			}
			else if (flag.equals("-u")) {
				UniformCostSolver ucs = new UniformCostSolver(initialBoard);
				String solution = ucs.search();
				System.out.println(solution);
			}
			else {
				System.out.println("Invalid command");
			}
		} catch (IOException e) {
			System.out.println("Puzzle file not found");
		} catch (NoSolutionException e) {
			System.out.println("Solution does not exist");
		}
	}
}
