
public class King extends Piece{
	public King(String color) {
		super(color);
	}
	
	@Override
	public String getSymbol() {
		return color.equals("white") ?"K":"k";
	}
	public String getImagePath() {
		return color.equals("white") ? "resource/whiteKing.png":"resource/blackKing.png";
	};
	
	public Boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board) {
		int maxX = Math.max(startX, endX);
		int minX = Math.min(startX, endX);
		int maxY = Math.max(startY, endY);
		int minY = Math.min(startY, endY);

        return maxX - minX <= 1 && maxY - minY <= 1;
	}
}
