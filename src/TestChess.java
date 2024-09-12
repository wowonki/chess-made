
public class TestChess {
	public static void main(String[] args) {
		// Create Chess Board
		ChessBoard board = new ChessBoard();
		
		board.printBoard();
		
		// valid pawn move
		System.out.println("Valied White Pawn Move from (6, 7) to (4, 7)");
		board.movePiece(6, 7, 4, 7);
		board.printBoard();
		
		// valid pawn move
		System.out.println("Valied Black Pawn Move from (1, 0) to (2, 0)");
		board.movePiece(1, 0, 2, 0);
		board.printBoard();
		
		// valid white rook move
		System.out.println("Valied Rook Move from (7, 7) to (5, 7)");
		board.movePiece(7, 7, 5, 7);
		board.printBoard();
		
		// valid black pawn move
		System.out.println("Valied Black Pawn Move from (1, 3) to (3, 3)");
		board.movePiece(1, 3, 3, 3);
		board.printBoard();
		
		// valid white rook move
		System.out.println("Valied Rook Move from (5, 7) to (5, 0)");
		board.movePiece(5, 7, 5, 0);
		board.printBoard();
		
		// valid black knight move
		System.out.println("Valied Knight Move from (0, 1) to (2, 0)");
		board.movePiece(0, 1, 2, 0);
		board.printBoard();
		
		// Invalid move 1. no piece to move
		System.out.println("Invalid move 1. no piece to move");
		board.movePiece(7, 7, 6, 7);
		
		// Invalid move 2. no movement from starting point
		System.out.println("Invalid move 2. no movement from start");
		board.movePiece(7, 5, 7, 5);
		
		// Invalid move 3. invalid movement of the piece
		System.out.println("Invalid move 3. Invalid movement of Piece");
		board.movePiece(6, 6, 3, 6);
		
		// Invalid move 4. out of board.
		System.out.println("Invalid move 3. Invalid movement of Piece");
		board.movePiece(6, 6, 3, 6);
	}

}
