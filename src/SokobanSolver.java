import java.io.IOException;


public class SokobanSolver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Board initialBoard = Board.parseBoardInput("resource/simple.txt");
			System.out.println(initialBoard);
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

}
