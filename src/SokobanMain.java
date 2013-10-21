import java.io.IOException;


public class SokobanMain {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BoardState initialBoard = BoardState.parseBoardInput("resource/simple.txt");
			System.out.println(initialBoard);
			BFSSolver bfs = new BFSSolver(initialBoard);
			String solution = bfs.search();
			System.out.println(solution);
		} catch (IOException e) {
			System.out.println("File not found");
		} catch (NoSolutionException e) {
			System.out.println("No solution was found");
		}
	}
	
}
