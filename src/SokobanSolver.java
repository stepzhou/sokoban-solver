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
	
	public static void parseArguments(String[] args) {
		try {
			String flag = args[0];
			String puzzlePath = args[1];
			BoardState initialBoard = BoardState.parseBoardInput(puzzlePath);
			AbstractSolver solver = null;
			System.out.println(initialBoard);
			if (flag.equals("-b")) {
				solver = new BFSSolver(initialBoard);
			}
			else if (flag.equals("-d")) {
				solver = new DFSSolver(initialBoard);
			}
			else if (flag.equals("-u")) {
				solver = new UniformCostSolver(initialBoard);
			}
			else {
				System.out.println("Invalid command");
			}
			
			if (solver != null) {
				String solution = solver.search();
				long timeElapsed = solver.getElapsedTimeMillis();
				System.out.println(solution);
				System.out.println(timeElapsed);
			}
		} catch (IOException e) {
			System.out.println("Puzzle file not found");
		} catch (NoSolutionException e) {
			System.out.println("Solution does not exist");
		}
	}
	
}
