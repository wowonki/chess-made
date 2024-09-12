
public class Rook extends Piece {
	public Rook(String color) {
		super(color);
	}
	
	@Override
	public String getSymbol() {
		return color.equals("white") ?"R":"r";
	}
	public String getImagePath() {
		return color.equals("white") ? "resource/whiteRook.png":"resource/blackRook.png";
	}
	
	@Override
	public Boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
		if (startX == endX) {
			int L = Math.max(startY, endY);
			int S = Math.min(startY, endY);
			boolean valid = true;
			for (int i = S+1;i < L;i++) {
				valid = valid && board.getPiece(startX, i) == null;
			}
			return valid;
		}
		else if (startY == endY) {
			int L = Math.max(startX, endX);
			int S = Math.min(startX, endX);
			boolean valid = true;
			for (int i = S+1;i < L;i++) {
				valid = valid && board.getPiece(i, startY) == null;
			}
			return valid;
		} else return false;
	}
}
