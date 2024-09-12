
public class Knight extends Piece {
	public Knight(String color) {
		super(color);
	}
	
	@Override
	public String getSymbol() {
		return color.equals("white") ?"N":"n";
	}
	public String getImagePath() {
		return color.equals("white") ? "resource/whiteKnight.png":"resource/blackKnight.png";
	};
	
	public Boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
		if (endX == startX - 2) {
			return endY == startY + 1 || endY == startY - 1;
		}
		if (endX == startX - 1) {
			return endY == startY + 2 || endY == startY - 2;
		}
		if (endX == startX + 1) {
			return endY == startY + 2 || endY == startY - 2;
		}
		if (endX == startX + 2) {
			return endY == startY + 1 || endY == startY - 1;
		}
		return false;
	}
}
