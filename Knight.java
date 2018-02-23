import java.util.ArrayList;

public class Knight extends Piece{

	public Knight(String name, int row, int col, String team){
		super(name, row, col, team);
	}
	
	public ArrayList<Cell> nextMove(GameBoard game){
		ArrayList<Cell> pos = new ArrayList<Cell>();
		if(new Cell(getRow() - 2, getCol() - 1).isValid() && validMove(game.cell[getRow() - 2][getCol() - 1])){
			pos.add(new Cell(getRow() - 2, getCol() - 1));
		}
		if(new Cell(getRow() - 2, getCol() + 1).isValid() && validMove(game.cell[getRow() - 2][getCol() + 1])){
			pos.add(new Cell(getRow() - 2, getCol() + 1));
		}
		if(new Cell(getRow() - 1, getCol() - 2).isValid() && validMove(game.cell[getRow() - 1][getCol() - 2])){
			pos.add(new Cell(getRow() - 1, getCol() - 2));
		}
		if(new Cell(getRow() - 1, getCol() + 2).isValid() && validMove(game.cell[getRow() - 1][getCol() + 2])){
			pos.add(new Cell(getRow() - 1, getCol() + 2));
		}
		if(new Cell(getRow() + 1, getCol() - 2).isValid() && validMove(game.cell[getRow() + 1][getCol() - 2])){
			pos.add(new Cell(getRow() + 1, getCol() - 2));
		}
		if(new Cell(getRow() + 1, getCol() + 2).isValid() && validMove(game.cell[getRow() + 1][getCol() + 2])){
			pos.add(new Cell(getRow() + 1, getCol() + 2));
		}
		if(new Cell(getRow() + 2, getCol() - 1).isValid() && validMove(game.cell[getRow() + 2][getCol() - 1])){
			pos.add(new Cell(getRow() + 2, getCol() - 1));
		}
		if(new Cell(getRow() + 2, getCol() + 1).isValid() && validMove(game.cell[getRow() + 2][getCol() + 1])){
			pos.add(new Cell(getRow() + 2, getCol() + 1));
		}
		if (pos.size() >= 1){
   		 empty = false;
    	}
		for(int i = 0; i < game.COLS; i++){
			for(int j = 0; j < game.ROWS; j++){
				if(game.cell[j][i].isEmpty() == false && game.cell[j][i].getPiece().isKing() == true && game.cell[j][i].getPiece().getTeam().equals(getTeam())){
					int kingX = i;
					int kingY = j;
				for(int a = 0; a < pos.size(); a++){
					int newX = pos.get(a).getX();
					int newY = pos.get(a).getY();
					int oldX = getCol();
					int oldY = getRow();
					if(isCapture(game.cell[newY][newX]) == true){
						Piece captured = game.cell[newY][newX].getPiece();
						Piece temp = game.cell[getRow()][getCol()].getPiece();
						game.cell[newY][newX].setPiece(temp);
						game.cell[getRow()][getCol()].removePiece();
						if(game.cell[kingY][kingX].getPiece().isInCheck(game) == true){
							pos.remove(a);
							a--;
						}
						game.cell[oldY][oldX].setPiece(temp);
						game.cell[newY][newX].removePiece();
						game.cell[newY][newX].setPiece(captured);
					}
					else{
						Piece temp = game.cell[getRow()][getCol()].getPiece();
						game.cell[newY][newX].setPiece(temp);
						game.cell[getRow()][getCol()].removePiece();
						if(game.cell[kingY][kingX].getPiece().isInCheck(game) == true){
						pos.remove(a);
						a--;
					}
					game.cell[oldY][oldX].setPiece(temp);
					game.cell[newY][newX].removePiece();
				}
				}
				}
			}
		}
		return pos;
	}
	
	public boolean isKnight(){
		return true;
	}
}


