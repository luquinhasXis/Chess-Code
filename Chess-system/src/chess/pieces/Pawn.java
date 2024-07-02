package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean [][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		if (getColor() == Color.WHITE) {
			p.setValues(position.getRow() -1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() -2, position.getColumn());
			Position p2 = new Position(position.getRow() -1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() -1, position.getColumn() -1);
			if(getBoard().positionExists(p) && isThereOponnentPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() -1, position.getColumn() +1);
			if(getBoard().positionExists(p) && isThereOponnentPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}		
			// en passant peças brancas lado esquerdo
			if(position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if(getBoard().positionExists(left) && isThereOponnentPiece(left) && getBoard().piece(left) == chessMatch.GetEnPassantVulnerable()) {
					matriz[left.getRow() - 1][left.getColumn()] = true;
			}		
			// en passant peças brancas lado direito
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if(getBoard().positionExists(right) && isThereOponnentPiece(right) && getBoard().piece(right) == chessMatch.GetEnPassantVulnerable()) {
					matriz[right.getRow() - 1][right.getColumn()] = true;
				}
			}	
		}
		else {
			p.setValues(position.getRow() +1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() +2, position.getColumn());
			Position p2 = new Position(position.getRow() +1, position.getColumn());
			if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() +1, position.getColumn() -1);
			if(getBoard().positionExists(p) && isThereOponnentPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() +1, position.getColumn() +1);
			if(getBoard().positionExists(p) && isThereOponnentPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			// en passant peças pretas lado esquerdo
			if(position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if(getBoard().positionExists(left) && isThereOponnentPiece(left) && getBoard().piece(left) == chessMatch.GetEnPassantVulnerable()) {
					matriz[left.getRow() + 1][left.getColumn()] = true;
			}		
			// en passant peças pretas lado direito
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if(getBoard().positionExists(right) && isThereOponnentPiece(right) && getBoard().piece(right) == chessMatch.GetEnPassantVulnerable()) {
					matriz[right.getRow() + 1][right.getColumn()] = true;
				}
			}	
		}
		return matriz;
	}
}
