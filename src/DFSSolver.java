import java.util.ArrayList;
import java.util.LinkedList;


public class DFSSolver extends AbstractSolver {
	private LinkedList<BoardState> moveStack;
	
	public DFSSolver(BoardState initialState) {
		super(initialState);
		moveStack = new LinkedList<BoardState>();
	}

	@Override
	public String search() throws NoSolutionException {
		startTimer();
		moveStack.push(currentState);
		visited.add(currentState);
		while (!moveStack.isEmpty()) {
			currentState = moveStack.poll();
			
			if (currentState.isSolved()) {
                System.out.println(currentState);
                String solution = backtrackMoves(currentState);
                stopTimer();
                return solution;
			}
			
			ArrayList<BoardState> validMoves = getValidMoves();
			for (BoardState move : validMoves) {
				backtrack.put(move, currentState);
				moveStack.add(move);
				visited.add(currentState);
			}
		}
		throw new NoSolutionException();
	}
}
