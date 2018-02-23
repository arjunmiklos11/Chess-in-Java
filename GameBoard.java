
public class GameBoard {
	
	public final int ROWS = 8;
	public final int COLS = 8;
	public Cell[][] cell = new Cell[ROWS][COLS];
	
	public GameBoard(){
		for(int row = 1; row < 2; row++){
			for(int col = 0; col < COLS; col++){
				cell[row][col] = new Cell(row, col, new Pawn("wP", row, col, "white"));
			}
		}
		for(int row = 6; row < 7; row++){
			for(int col = 0; col < COLS; col++){
				cell[row][col] = new Cell(row, col, new Pawn("bP", row, col, "black"));
			}
		}
		for(int row = 2; row < 6; row++){
			for(int col = 0; col < COLS; col++){
				cell[row][col] = new Cell(row, col);
			}
		}
		cell[0][0] = new Cell(0, 0, new Rook("wR", 0, 0, "white"));
		cell[0][7] = new Cell(0, 7, new Rook("wR", 0, 7, "white"));
		cell[0][1] = new Cell(0, 1, new Knight("wKn", 0, 1, "white"));
		cell[0][6] = new Cell(0, 6, new Knight("wKn", 0, 6, "white"));
		cell[0][2] = new Cell(0, 2, new Bishop("wB", 0, 2, "white"));
		cell[0][5] = new Cell(0, 5, new Bishop("wB", 0, 5, "white"));
		cell[0][4] = new Cell(0, 4, new Queen("wQ", 0, 4, "white"));
		cell[0][3] = new Cell(0, 3, new King("wK", 0, 3, "white"));;
		cell[7][0] = new Cell(7, 0, new Rook("bR", 7, 0, "black"));
		cell[7][7] = new Cell(7, 7, new Rook("bR", 7, 7, "black"));
		cell[7][1] = new Cell(7, 1, new Knight("bKn", 7, 1, "black"));
		cell[7][6] = new Cell(7, 6, new Knight("bKn", 7, 6, "black"));
		cell[7][2] = new Cell(7, 2, new Bishop("bB", 7, 2, "black"));
		cell[7][5] = new Cell(7, 5, new Bishop("bB", 7, 5, "black"));
		cell[7][4] = new Cell(7, 4, new Queen("bQ", 7, 4, "black"));
		cell[7][3] = new Cell(7, 3, new King("bK", 7, 3, "black"));
	}
	
	
}


