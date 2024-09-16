public class Pawn extends Piece {
	public Pawn(String color) {
		super(color);
	}

	@Override
	public String getSymbol() {
		return color.equals("white") ? "P" : "p";
	}

	public String getImagePath() {
		return color.equals("white") ? "resource/whitePawn.png" : "resource/blackPawn.png";
	}

	@Override
	public Boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
		if (board.getPiece(endX, endY) == null) {
			if (this.color.equals("white")) {
				if (startX == 6) {
					return (startY == endY && endX == startX - 2 && board.getPiece(endX, endY) == null) ||
							(startY == endY && endX == startX - 1 && board.getPiece(endX, endY) == null);
				} else return (startY == endY && endX == startX - 1 && board.getPiece(endX, endY) == null);
			} else {
				if (startX == 1) {
					return (startY == endY && endX == startX + 2 && board.getPiece(endX, endY) == null) ||
							(startY == endY && endX == startX + 1 && board.getPiece(endX, endY) == null);
				} else return (startY == endY && endX == startX + 1 && board.getPiece(endX, endY) == null);
			}
		} else {
			// Get other piece
			if (this.color.equals("white")) {
				return endX - startX == -1 && Math.abs(endY - startY) == 1;
			} else return endX - startX == 1 && Math.abs(endY - startY) == 1;
		}
	}
}