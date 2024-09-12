
public class Queen extends Piece{
	public Queen(String color) {
		super(color);
	}
	
	@Override
	public String getSymbol() {
		return color.equals("white") ?"Q":"q";
	}
	public String getImagePath() {
		return color.equals("white") ? "resource/whiteQueen.png":"resource/blackQueen.png";
	};
	
	public Boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
		// Is Bishop Movement
		int dX = endX - startX;
		int dY = endY - startY;

		if (Math.abs(dX) == Math.abs(dY)) {
			boolean valid = true;
			if (dX > 0 && dY > 0) {
				for (int i = 1; i < dX; i++){
					valid = valid && board.getPiece(startX + i, startY + i) == null;
				}
				return valid;
			}
			else if (dX > 0 && dY < 0) {
				for (int i = 1; i < dX; i++){
					valid = valid && board.getPiece(startX + i, startY - i) == null;
				}
				return valid;
			}
			else if (dX < 0 && dY > 0) {
				for (int i = 1; i < dY; i++){
					valid = valid && board.getPiece(startX - i, startY + i) == null;

				}
				return valid;
			}
			else {
				for (int i = 1; i < -dY; i++){
					valid = valid && board.getPiece(startX - i, startY - i) == null;
				}
				return valid;
			}
		}

		// Is Rook Movement
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
