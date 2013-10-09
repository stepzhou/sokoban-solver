import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class Board {
	// Bitfields guaranteed to never exceed 15
	private static final byte PLAYER = 1 << 0;
	private static final byte WALL = 1 << 1;
	private static final byte BOX = 1 << 2;
	private static final byte GOAL = 1 << 3;
	private static HashMap<Character, Byte> charToField;
	private static HashMap<Byte, Character> fieldToChar;
	static {
		charToField = new HashMap<Character, Byte>();
		charToField.put('#', WALL);
		charToField.put('.', GOAL);
		charToField.put('@', PLAYER);
		charToField.put('+', (byte) (PLAYER | GOAL));
		charToField.put('$', BOX);
		charToField.put('*', (byte) (BOX | GOAL));
		charToField.put(' ', (byte) 0);
		
		fieldToChar = new HashMap<Byte, Character>();
		for (Entry<Character, Byte> entry : charToField.entrySet()) {
			fieldToChar.put(entry.getValue(), entry.getKey());
		}
	}
	
	private byte[][] board;
	private Point player;
	private ArrayList<Point> goals;
	
	public Board(byte[][] board, Point player, ArrayList<Point> goals) {
		this.board = board;
		this.player = player;
		this.goals = goals;
	}
	
	public boolean isSolved() {
		for (Point p : goals) {
			if (!(boardHas(p.x, p.y, GOAL) && boardHas(p.x, p.y, BOX))) {	
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				builder.append(fieldToChar.get(board[row][col]));
			}
			builder.append('\n');
		}
		return builder.toString();
	}
	
	/**
	 * Gets the byte board representation used for search hashing
	 * @return the byte board representation
	 */
	public byte[][] getBoard() {
		return board;
	}

	private boolean boardHas(int row, int col, byte field) {
		return (board[row][col] & field) == field;
	}

	/**
	 * Parses a Sokoban text file into a Board object
	 * @param boardInput the Sokoban text file
	 * @return the Board state object
	 * @throws IOException if the Sokoban file does not exist
	 */
	public static Board parseBoardInput(String boardInput) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(boardInput));
		int width = Integer.parseInt(reader.readLine());
		int height = Integer.parseInt(reader.readLine());
		byte[][] boardPoints = new byte[height][width];
		Point player = new Point();
		ArrayList<Point> goals = new ArrayList<Point>();

		String line;
		for (int row = 0; row < height && (line = reader.readLine()) != null; row++) {
			for (int col = 0; col < width && col < line.length(); col++) {
				byte field = charToField.get(line.charAt(col));
				boardPoints[row][col] = field;
				if ((field & PLAYER) == PLAYER)
					player = new Point(row, col);
				if ((field & GOAL) == GOAL)
					goals.add(new Point(row, col));
			}
		}

		reader.close();
		return new Board(boardPoints, player, goals);
	}
}
