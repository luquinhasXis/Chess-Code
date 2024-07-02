package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JFrame;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program { 

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
		
		while(!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Coluna: ");
				ChessPosition source = UI.readChessPosition(sc);
			
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				System.out.println();
				System.out.print("Linha: ");
				ChessPosition target = UI.readChessPosition(sc);
			
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				if(chessMatch.getPromoted() != null) {
					System.out.print("Digite peça para promover (B/C/T/M): ");
					String type = sc.nextLine();
					type = type.toUpperCase();
					while(!type.equals("B") && !type.equals("T") && !type.equals("C") && !type.equals("M")) {
						System.out.print("Valor inválido. Digite novamente: (B/C/T/M): ");
						type = sc.nextLine();
					}
					chessMatch.replacePromotedPiece(type);
				}
			}
			catch(ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}
}
