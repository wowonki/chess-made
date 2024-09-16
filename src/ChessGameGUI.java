import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChessGameGUI extends JFrame {
    private JPanel boardPanel;
    private final int BOARD_SIZE = 8;
    private JButton[][] squares = new JButton[BOARD_SIZE][BOARD_SIZE];
    private ChessBoard chessBoard;  // 체스판 객체
    private Point selectedPiece = null;  // 선택된 말의 위치

    public ChessGameGUI() {
        chessBoard = new ChessBoard();  // 체스판 초기화
        setTitle("Chess Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeBoard();
        updateBoard();  // 체스판에 기물 업데이트
        setVisible(true);
    }

    // 체스판 초기화
    private void initializeBoard() {
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                JButton square = new JButton();
                if ((row + col) % 2 == 0) {
                    square.setBackground(Color.WHITE);
                } else {
                    square.setBackground(Color.GRAY);
                }
                square.setPreferredSize(new Dimension(60, 60));

                int finalRow = row;
                int finalCol = col;
                square.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        handleMouseClick(finalRow, finalCol);
                    }
                });

                squares[row][col] = square;
                boardPanel.add(square);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
    }

    private ImageIcon resizeImage(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    // 체스판 업데이트 (각 기물의 심볼을 표시)
    private void updateBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = chessBoard.getPiece(row, col);
                if (piece != null) {
                    squares[row][col].setIcon(resizeImage(piece.getImagePath(), 60, 60));
                } else {
                    squares[row][col].setIcon(null);
                }
            }
        }
        chessBoard.updateCheck();
        if (chessBoard.getCheck()) { chessBoard.updateCheckmate(); }
        if (chessBoard.getGameOver()) { this.endGame(); }
    }
    private void endGame() {
        // 배경을 불투명한 회색으로 바꾸고
        // 더이상 버튼이 작동하지 않도록 해서 게임을 이어나가지 못하게.
        String winner = chessBoard.getTurn() == 1?"black":"white";
        System.out.println("Checkmate! " + winner + " Win.");
        // System.exit(0);
    }

    // 마우스 클릭 처리
    private void handleMouseClick(int row, int col) {
        if (selectedPiece == null && chessBoard.getPiece(row, col) != null) {
            // 말을 선택
            selectedPiece = new Point(row, col);
            System.out.println("Selected piece at: " + selectedPiece);
        } else if (selectedPiece != null) {
            // 선택된 말이 있으면 이동 시도
            int startX = selectedPiece.x;
            int startY = selectedPiece.y;
            if (chessBoard.movePiece(startX, startY, row, col)) {
                updateBoard();  // 체스판 업데이트
            }
            selectedPiece = null;
        }
    }

    public static void main(String[] args) {
        new ChessGameGUI();
    }
}
