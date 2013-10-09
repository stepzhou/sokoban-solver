import java.io.IOException;


public class SokobanSolver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Board intialBoard = Board.parseBoardInput("resource/simple.txt");
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

}
