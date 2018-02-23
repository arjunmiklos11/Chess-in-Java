import java.util.ArrayList;

public class Rook extends Piece{

	public Rook(String name, int row, int col, String team){
		super(name, row, col, team);
	}
	
	public ArrayList<Cell> nextMove(GameBoard game){
		ArrayList<Cell> pos = new ArrayList<Cell>();
		boolean past = false;
		//Right
		for(int i = getCol() + 1; i < game.COLS; i++){
			if(isCapture(game.cell[getRow()][i]) && past == false){
				pos.add(new Cell(getRow(), i));
				past = true;
			}
			else if(new Cell(getRow(), i).isValid() && past == false){
				if(validMove(game.cell[getRow()][i])){
					pos.add(new Cell(getRow(), i));
				}
				else{
					past = true;
				}
			}
		}
		past = false;
		//Left
		for(int i = getCol() - 1; i >= 0; i--){
			if(isCapture(game.cell[getRow()][i]) && past == false){
				pos.add(new Cell(getRow(), i));
				past = true;
			}
			else if(new Cell(getRow(), i).isValid() && past == false){
				if(isCapture(new Cell(getRow(), i))){
					pos.add(new Cell(getRow(), i));
					past = true;
				}
				if(validMove(game.cell[getRow()][i])){
					pos.add(new Cell(getRow(), i));
				}
				else{
					past = true;
				}
			}
		}
		past = false;
		//Down
		for(int i = getRow() + 1; i < game.ROWS; i++){
			if(isCapture(game.cell[i][getCol()]) && past == false){
				pos.add(new Cell(i, getCol()));
				past = true;
			}
			else if(new Cell(i, getCol()).isValid() && past == false){
				if(validMove(game.cell[i][getCol()])){
					pos.add(new Cell(i, getCol()));
				}
				else{
					past = true;
				}
			}
		}
		past = false;
		//Up
		for(int i = getRow() - 1; i >= 0; i--){
			if(isCapture(game.cell[i][getCol()]) && past == false){
				pos.add(new Cell(i, getCol()));
				past = true;
			}
			else if(new Cell(i, getCol()).isValid() && past == false){
				if(validMove(game.cell[i][getCol()])){
					pos.add(new Cell(i, getCol()));
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
	
	public boolean isRook(){
		return true;
	}
}


