import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Board {
	
	private BoardPoint[][] board;
	private BoardPoint player;
	private HashMap<Character, BoardPoint> charPointMap;
	private HashMap<BoardPoint, Character> pointCharMap;
	
	public Board(BoardPoint[][] board, BoardPoint player) {
		this.board = board;
		this.player = player;
		buildBiMap();
	}
	
	public ArrayList<Board> getNextStates() {
		throw new UnsupportedOperationException();
	}
	
	public static Board parseBoardInput(String boardInput) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(boardInput));
		int width = Integer.parseInt(reader.readLine());
		int height = Integer.parseInt(reader.readLine());
		BoardPoint[][] boardPoints = new BoardPoint[width][height];
		BoardPoint player = BoardPoint.Error();

		String line;
		for (int row = 0; row < height && (line = reader.readLine()) != null; row++) {
			for (int col = 0; col < width && col < line.length(); col++) {
//				BoardPoint point = getBoardPoint(line.charAt(col));
//				if (point.has(BoardPoint.State.PLAYER))
//					player = point;
//				boardPoints[row][col] = point;
			}
		}

		reader.close();
		return new Board(boardPoints, player);
	}
	
	/**
	 * Gets the BoardPoint given a character
	 * @param c the input character
	 * @return the character's corresponding BoardPoint
	 */
	private BoardPoint getBoardPoint(char c) {
		return charPointMap.get(c).clone();
	}
	
	private void buildBiMap() {
		charPointMap = new HashMap<Character, BoardPoint>();
		pointCharMap = new HashMap<BoardPoint, Character>();
		charPointMap.put('#', new BoardPoint(BoardPoint.State.WALL));
		charPointMap.put('.', new BoardPoint(BoardPoint.State.GOAL));
		charPointMap.put('@', new BoardPoint(BoardPoint.State.PLAYER));
		charPointMap.put('+', new BoardPoint(BoardPoint.State.PLAYER, BoardPoint.State.GOAL));
		charPointMap.put('$', new BoardPoint(BoardPoint.State.BOX));
		charPointMap.put('*', new BoardPoint(BoardPoint.State.BOX, BoardPoint.State.GOAL));
		charPointMap.put(' ', new BoardPoint());
		
		for (Entry<Character, BoardPoint> entry : charPointMap.entrySet()) {
			pointCharMap.put(entry.getValue(), entry.getKey());
		}
		
	}

}
