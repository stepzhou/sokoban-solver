import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class BoardState {
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
	private Point direction;
	
	public BoardState(byte[][] board, Point player, ArrayList<Point> goals) {
		this.board = board;
		this.player = player;
		this.goals = goals;
		direction = null;
	}

	public BoardState(byte[][] board, Point player, ArrayList<Point> goals, 
			Point direction) {
		this.board = board;
		this.player = player;
		this.goals = goals;
		this.direction = direction;
	}
	
	public boolean isSolved() {
		for (Point p : goals) {
			if (!(pointHas(p.x, p.y, GOAL) && pointHas(p.x, p.y, BOX))) {	
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns whether or not the player can move in a certain directoin
	 * @param direction the row/col direction
	 * @return True if player can move, false otherwise
	 */
	public boolean canMove(Point direction) {
		Point newPos = new Point(player.x + direction.x, player.y + direction.y);
		Point oneOutPos = new Point(newPos.x + direction.x, newPos.y + direction.y);
		if (pointHas(newPos, BOX)) {
			// Box can't be pushed if there's a wall or box
			if (pointHas(oneOutPos, WALL) || pointHas(oneOutPos, BOX))
				return false;
			// Shouldn't ever happen
			else if (pointHas(oneOutPos, PLAYER))
				throw new IllegalStateException(
						String.format("Player shouldn't be there row: %d col: %d",
								oneOutPos.x, oneOutPos.y));
			// Goal or empty
			else
				return true;
		}
		else if (pointHas(newPos, WALL))
			return false;
		else if (pointHas(newPos, PLAYER))
			throw new IllegalStateException(
					String.format("Player shouldn't be there row: %d col: %d",
							newPos.x, newPos.y));
		// Goal or empty
		else
			return true;
	}
	
	/**
	 * Returns the new BoardState after moving a certain direction
	 * @param direction the direction to move
	 * @return the new BoardState
	 * @pre must be called only if canMove is true
	 */
	public BoardState getMove(Point direction) {
		Point newPos = new Point(player.x + direction.x, player.y + direction.y);
		Point oneOutPos = new Point(newPos.x + direction.x, newPos.y + direction.y);
		
		// Copy board
		byte[][] newBoard = new byte[board.length][];
		for (int i = 0; i < newBoard.length; i++)
			newBoard[i] = board[i].clone();
		
		// Take player off current position
		byte playerBitField = newBoard[player.x][player.y];
		newBoard[player.x][player.y] = toggleField(playerBitField, PLAYER); 
		
		// Turn player on new position
		byte newPlayerBitField = newBoard[newPos.x][newPos.y];
		newBoard[newPos.x][newPos.y] = toggleField(newPlayerBitField, PLAYER); 
		
		// If pushing a box, move box
		if (pointHas(newPos, BOX)) {
			byte oldBoxPos = newBoard[newPos.x][newPos.y];
			byte newBoxPos = newBoard[oneOutPos.x][oneOutPos.y];
			newBoard[newPos.x][newPos.y] = toggleField(oldBoxPos, BOX);
			newBoard[oneOutPos.x][oneOutPos.y] = toggleField(newBoxPos, BOX);
		}

		// Not copying goals because they SHOULD be the same anyways...
		return new BoardState(newBoard, newPos, goals, direction);
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		result = prime * result + ((goals == null) ? 0 : goals.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardState other = (BoardState) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		if (goals == null) {
			if (other.goals != null)
				return false;
		} else if (!goals.equals(other.goals))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}

	/**
	 * Gets the byte board representation used for search hashing
	 * @return the byte board representation
	 */
	public byte[][] getBoard() {
		return board;
	}
	
	/**
	 * Gets the direction that the player made to get to the boardstate
	 * @return the directoin that the player made to get to the boardstate
	 */
	public Point getDirection() {
		return direction;
	}

	private boolean pointHas(int row, int col, byte field) {
		return (board[row][col] & field) == field;
	}
	
	private boolean pointHas(Point pos, byte field) {
		return pointHas(pos.x, pos.y, field);
	}
	
	private byte toggleField(byte bitfield, byte field) {
		return (byte) (bitfield ^ field);
	}

	/**
	 * Parses a Sokoban text file into a Board object
	 * @param boardInput the Sokoban text file
	 * @return the Board state object
	 * @throws IOException if the Sokoban file does not exist
	 */
	public static BoardState parseBoardInput(String boardInput) throws IOException {
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
		return new BoardState(boardPoints, player, goals);
	}
}
