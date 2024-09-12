
public abstract class Piece {
	protected String color; // White or Black
	
	public Piece(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public abstract String getSymbol();
	public abstract String getImagePath();
	public abstract Boolean isValidMove(int startX, int startY, int endX, int endY, ChessBoard board);

}
