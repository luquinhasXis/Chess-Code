package boardgame;

public abstract class Piece {

	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
	}
	
	protected Board getBoard() {
		return board;
	}	
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		// r = row    // c = column
		boolean[][] mat = possibleMoves();
		for (int r=0; r<mat.length; r++) {
			for (int c=0; c<mat.length; c++) {
				if (mat[r][c]) {
					return true;
				}
			}
		}
		return false;
	}
}