import java.awt.*;

public class ChessBoard {
	private final Piece[][] board;
	private final int size = 8;
	private int turn = 1;
	private boolean check = false;
	private boolean gameOver = false;

	public boolean getCheck() {
		return this.check;
	}
	public boolean getGameOver() {
		return this.gameOver;
	}
	public int getTurn() {
		return this.turn;
	}
	
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
		board[0][4] = new King("black");
		board[0][3] = new Queen("black");
	}
	
	public Piece getPiece(int x, int y) {
		return this.board[x][y];
	}
	private Boolean isOutBoard(int x, int y) {
		return (x >= this.size && y >= this.size);
	}

	// Check Check.
	public void updateCheck() {
		String myCol = turn == 1?"white":"black";

		if (this.checkCheck()){
			this.check = true;
			System.out.println("Check! " + myCol + " must move to protect King.");
		}
		else { this.check = false; }
	}

	private boolean checkCheck() {
		String oppCol = turn == 1?"black":"white";
		int[] kingPosition = getMyKingPosition();
		int x = kingPosition[0];
		int y = kingPosition[1];

		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				if (getPiece(i, j) != null && getPiece(i, j).color.equals(oppCol) && getPiece(i, j).isValidMove(i, j, x, y, this)) {
					return true;
				}
			}
		}
		return false;
	}
	private boolean checkEscapeCheck(int startX, int startY, int endX, int endY) {
		Piece startPiece = getPiece(startX, startY);
		Piece endPiece = getPiece(endX, endY);

		board[endX][endY] = startPiece;
		board[startX][startY] = null;
		boolean isEscape = !checkCheck();
		board[startX][startY] = startPiece;
		board[endX][endY] = endPiece;
		return isEscape;
	}


	private int[] getMyKingPosition() {
		int[] kingPosition = new int[2];
		String myCol = turn == 1?"white":"black";
			for (int i=0;i<size;i++) {
				for (int j=0;j<size;j++) {
					if (getPiece(i, j) instanceof King && getPiece(i, j).color.equals(myCol)) {
						int[] pos = new int[2];
						pos[0] = i;
						pos[1] = j;
						return pos;
					}
				}
			} return null; // this line never run.
	}
	private boolean checkCheckmate() {
		String oppCol = (turn==1)?"black":"white";
		String myCol = (turn==1)?"white":"black";

		for (int startX=0;startX<size;startX++) {
			for (int startY=0;startY<size;startY++) {
				if (getPiece(startX, startY) != null) {
					if (getPiece(startX, startY).color.equals(myCol)) {
						for (int endX=0;endX<size;endX++) {
							for (int endY=0;endY<size;endY++) {
								if (getPiece(endX, endY) != null) {
									if (getPiece(startX, startY).isValidMove(startX, startY, endX, endY, this) &&
											(this.getPiece(endX, endY).color.equals(oppCol)) &&
											checkEscapeCheck(startX, startY, endX, endY)) {
										return false;
									}
								} else {
									if (getPiece(startX, startY).isValidMove(startX, startY, endX, endY, this) &&
											checkEscapeCheck(startX, startY, endX, endY)) {
										return false;
									}
								}
							}
						}
					}
				}
			}
		} return true;
	}
	public void updateCheckmate() {
		if (checkCheckmate()) { gameOver = true; }
	}


	public Boolean movePiece(int startX, int startY, int endX, int endY) {
		// check is valid movement
		String oppCol = turn == 1?"black":"white";
		String myCol = turn == -1?"black":"white";
		Piece startPiece = this.board[startX][startY];
		Piece endPiece = this.board[endX][endY];

		if (this.isOutBoard(endX, endY)) System.out.println("Invalid Movement[1]: Out of Range.");
		else if ((startPiece == null)) System.out.println("Invalid Movement[2]: There is no Piece at (" + startX + "," + startY + ")");
		else if (!(startPiece.isValidMove(startX, startY, endX, endY, this))) {
			System.out.println("Invalid Movement[5]: The piece must follow it's movement rule.");
		}
		else if (this.getPiece(startX, startY).color.equals(oppCol)) {
			System.out.println("Invalid Movement[6]: It's turn of the " + myCol);
		}
		else if (endPiece != null && endPiece.color.equals(myCol)) {
			System.out.println("Invalid Movement[7]: End point is occupied by your piece.");
		}

		// Move Correctly
		else if (true) {
			this.board[endX][endY] = startPiece;
			this.board[startX][startY] = null;

			if (check && (this.checkCheck())) {
				// check if the movement protect king.
				System.out.println("Invalid Movement[8]: Your King under the threaten. You should protect him.");
				this.board[endX][endY] = endPiece;
				this.board[startX][startY] = startPiece;

				return false;
			}

			this.turn *= -1;
			if (endPiece != null) { System.out.println("Gotcha!"); }
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
