
public class Bishop extends Piece {
	public Bishop(String color) {
		super(color);
	}
	
	@Override
	public String getSymbol() {
		return color.equals("white") ?"B":"b";
	}
	public String getImagePath() {
		return color.equals("white") ? "resource/whiteBishop.png":"resource/blackBishop.png";
	};
	
	public Boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
		int dX = endX - startX;
		int dY = endY - startY;

		if (Math.abs(dX) == Math.abs(dY)) {
			boolean valid = true;
			if (dX > 0 && dY > 0) {
				for (int i = 1; i < dX; i++){
					valid = valid && board.getPiece(startX + i, startY + i) == null;
				}
			}
			else if (dX > 0 && dY < 0) {
				for (int i = 1; i < dX; i++){
					valid = valid && board.getPiece(startX + i, startY - i) == null;
				}
			}
			else if (dX < 0 && dY > 0) {
				for (int i = 1; i < dY; i++){
					valid = valid && board.getPiece(startX - i, startY + i) == null;

				}
			}
			else {
				for (int i = 1; i < -dY; i++){
					valid = valid && board.getPiece(startX - i, startY - i) == null;
				}
			}
			return valid;
		}
		else return false;
	}
}
