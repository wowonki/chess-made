
public class ChessBoard {
	private final Piece[][] board;
	private final int size = 8;
	private int turn = 1;
	
	public ChessBoard() {
		// binding object to variable.
		board = new Piece[size][size];
		initiateBoard();
	}
	
	private void initiateBoard() {
		for (int i=0;i < size;i++) {
			for (int j=0; j < size; j++) {
				board[i][j] = null;
			}
		}
		
		// Place Pawn on the Board
		for (int i=0;i<8;i++) {
			board[6][i] = new Pawn("white");
			board[1][i] = new Pawn("black");
		}
		
		// Place Other Materials
		board[7][0] = new Rook("white");
		board[7][7] = new Rook("white");
		board[7][1] = new Knight("white");
		board[7][6] = new Knight("white");
		board[7][2] = new Bishop("white");
		board[7][5] = new Bishop("white");
		board[7][3] = new Queen("white");
		board[7][4] = new King("white");
		
		board[0][0] = new Rook("black");
		board[0][7] = new Rook("black");
		board[0][1] = new Knight("black");
		board[0][6] = new Knight("black");
		board[0][2] = new Bishop("black");
		board[0][5] = new Bishop("black");
		board[0][3] = new King("black");
		board[0][4] = new Queen("black");
	}
	
	public Piece getPiece(int x, int y) {
		return this.board[x][y];
	}
	private Boolean isOutBoard(int x, int y) {
		return (x >= this.size && y >= this.size);
	}
	public Boolean movePiece(int startX, int startY, int endX, int endY) {
		
		// check is valid movement
		if (this.isOutBoard(endX, endY)) System.out.println("Invalid Movement[1]: Out of Range.");
		else if ((this.board[startX][startY] == null)) System.out.println("Invalid Movement[2]: There is no Piece at (" + startX + "," + startY + ")");
		else if (startX == endX && startY == endY) System.out.println("Invalid Movement[4]: The piece must move from starting point.");
		else if (!(this.board[startX][startY].isValidMove(startX, startY, endX, endY, this))) {
			System.out.println("Invalid Movement[5]: The piece must follow it's movement rule.");
		}
		else if (this.getPiece(startX, startY).color.equals("white") && this.turn == -1) {
			System.out.println("Invalid Movement[6]: It's turn of the black");
		}
		else if (this.getPiece(startX, startY).color.equals("black") && this.turn == 1) {
			System.out.println("Invalid Movement[6]: It's turn of the white");
		}

		else if (this.board[endX][endY] != null) {
			if (this.board[endX][endY].color.equals("white") && this.turn == -1) {
				this.board[endX][endY] = this.getPiece(startX, startY);
				this.board[startX][startY] = null;
				this.turn *= -1;
				System.out.println("Gotcha!");
				return true;
			}
			else if (this.board[endX][endY].color.equals("black") && this.turn == 1) {
				this.board[endX][endY] = this.getPiece(startX, startY);
				this.board[startX][startY] = null;
				this.turn *= -1;
				System.out.println("Gotcha!");
				return true;
			}
			else System.out.println("Invalid Movement[3]: The piece is already placed at (" + endX + "," + endY + ")");
		}

		else {
				this.board[endX][endY] = this.getPiece(startX, startY);
				this.board[startX][startY] = null;
				this.turn *= -1;
				return true;
			}
		return false;
	}
	
	public void printBoard() {
		for (int i=0;i < size;i++) {
			for (int j=0;j < size; j++) {
				if (board[i][j] == null) {
					System.out.print("- ");
				}
				else {
					System.out.print(board[i][j].getSymbol() + " ");
				}
			}
			System.out.print("\n");
		}
	System.out.print("\n");
	}
	
}
