import java.io.IOException;


public class SokobanMain {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BoardState initialBoard = BoardState.parseBoardInput("resource/simple.txt");
			System.out.println(initialBoard);
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
}
