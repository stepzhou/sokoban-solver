import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map.Entry;

public class Board {
	private static byte PLAYER = 1 << 0;
	private static byte WALL = 1 << 1;
	private static byte BOX = 1 << 2;
	private static byte GOAL = 1 << 3;
	
	private EnumSet<State>[][] board;
	private Point player;
	private HashMap<Character, EnumSet<State>> charPointMap;
	private HashMap<EnumSet<State>, Character> pointCharMap;
	
	public enum State {
		PLAYER, WALL, BOX, GOAL, ERROR
	}
	
	public Board(EnumSet<State>[][] board, Point player) {
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
		EnumSet<State>[][] boardPoints = new EnumSet<State>[width][height];

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
	 * Builds the character/State EnumSet translation tables
	 */
	private void buildBiMap() {
		charPointMap = new HashMap<Character, EnumSet<State>>();
		pointCharMap = new HashMap<EnumSet<State>, Character>();
		charPointMap.put('#', EnumSet.of(State.WALL));
		charPointMap.put('.', EnumSet.of(State.GOAL));
		charPointMap.put('@', EnumSet.of(State.PLAYER));
		charPointMap.put('+', EnumSet.of(State.PLAYER, State.GOAL));
		charPointMap.put('$', EnumSet.of(State.BOX));
		charPointMap.put('*', EnumSet.of(State.BOX, State.GOAL));
		charPointMap.put(' ', EnumSet.noneOf(State.class));
		
		for (Entry<Character, EnumSet<State>> entry : charPointMap.entrySet()) {
			pointCharMap.put(entry.getValue(), entry.getKey());
		}
		
	}

}
