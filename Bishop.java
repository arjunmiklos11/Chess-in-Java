import java.util.ArrayList;

public class Bishop extends Piece{

	public Bishop(String name, int row, int col, String team){
		super(name, row, col, team);
	}
	
	public ArrayList<Cell> nextMove(GameBoard game){
		ArrayList<Cell> pos = new ArrayList<Cell>();
		boolean past = false;
		//down and right
		for(int x = 1; x + getCol() < game.COLS && getRow() + x < game.ROWS; x++){
			if(isCapture(game.cell[getRow() + x][x + getCol()]) && past == false){
				pos.add(new Cell(getRow() + x, x + getCol()));
				past = true;
			}
			else if(new Cell(getRow() + x, x + getCol()).isValid() && past == false){
				if(validMove(game.cell[getRow() + x][x + getCol()])){
					pos.add(new Cell(getRow() + x, x + getCol()));
				}
				else{
					past = true;
				}
			}
		}
		past = false;
		//down and left
		for(int x = -1; x + getCol() >= 0 && getRow() - x < game.ROWS; x--){
			if(isCapture(game.cell[getRow() - x][x + getCol()]) && past == false){
				pos.add(new Cell(getRow() - x, x + getCol()));
				past = true;
			}
			else if(new Cell(getRow() - x, x + getCol()).isValid() && past == false){
				if(validMove(game.cell[getRow() - x][x + getCol()])){
					pos.add(new Cell(getRow() - x, x + getCol()));
				}
				else{
					past = true;
				}
			}
		}
		past = false;
		//Up and right
		for(int x = 1; x + getCol() < game.COLS && getRow() - x >= 0; x++){
			if(isCapture(game.cell[getRow() - x][x + getCol()]) && past == false){
				pos.add(new Cell(getRow() - x, x + getCol()));
				past = true;
			}
			else if(new Cell(getRow() - x, x + getCol()).isValid() && past == false){
				if(validMove(game.cell[getRow() - x][x + getCol()])){
					pos.add(new Cell(getRow() - x, x + getCol()));
				}
				else{
					past = true;
				}
			}
		}
		past = false;
		//Up and left
		for(int x = -1; x + getCol() >= 0 && getRow() + x >= 0; x--){
			if(isCapture(game.cell[getRow() + x][x + getCol()]) && past == false){
				pos.add(new Cell(getRow() + x, x + getCol()));
				past = true;
			}
			else if(new Cell(getRow() + x, x + getCol()).isValid() && past == false){
				if(validMove(game.cell[getRow() + x][x + getCol()])){
					pos.add(new Cell(getRow() + x, x + getCol()));
				}
				else{
					past = true;
				}
			}
		}
		past = false;
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
	
	public boolean isBishop(){
		return true;
	}
}


