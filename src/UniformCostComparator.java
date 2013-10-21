import java.util.Comparator;


public class UniformCostComparator implements Comparator<BoardState> {

	@Override
	public int compare(BoardState state1, BoardState state2) {
		if (state1.getCost() < state2.getCost())
			return -1;
		else if (state1.getCost() > state2.getCost())
			return 1;
		else
			return 0;
	}
	
}
