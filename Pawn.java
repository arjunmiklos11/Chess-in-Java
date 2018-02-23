import java.util.ArrayList;

public class Pawn extends Piece{
	
	public Pawn(String name, int row, int col, String team){
		super(name, row, col, team);
	}
	
	public ArrayList<Cell> nextMove(GameBoard game){
		ArrayList<Cell> pos = new ArrayList<Cell>();
		if(getTeam().equals("black") && new Cell(getRow() - 1, getCol()).isValid() && game.cell[getRow() - 1][getCol()].isEmpty()){
			pos.add(new Cell(getRow() - 1, getCol()));
			if(getTeam().equals("black") && getFirstMove() == true && game.cell[getRow() - 2][getCol()].isEmpty()){
				pos.add(new Cell(getRow() - 2, getCol()));
			}
		}
		if(getTeam().equals("white") && new Cell(getRow() + 1, getCol()).isValid() && game.cell[getRow() + 1][getCol()].isEmpty()){
			pos.add(new Cell(getRow() + 1, getCol()));
			if(getTeam().equals("white") && getFirstMove() == true && game.cell[getRow() + 2][getCol()].isEmpty()){
				pos.add(new Cell(getRow() + 2, getCol()));
			}
		}
		if(getTeam().equals("black") 
		   && new Cell(getRow() - 1, getCol() - 1).isValid()
		   && game.cell[getRow() - 1][getCol() - 1].isEmpty() == false
		   && game.cell[getRow() - 1][getCol() - 1].getPiece().getTeam().equals("white")){
			pos.add(new Cell(getRow() - 1, getCol() - 1));
		}
		if(getTeam().equals("black") 
		   && new Cell(getRow() - 1, getCol() + 1).isValid()
		   && game.cell[getRow() - 1][getCol() + 1].isEmpty() == false
		   && game.cell[getRow() - 1][getCol() + 1].getPiece().getTeam().equals("white")){
			pos.add(new Cell(getRow() - 1, getCol() + 1));
		}
		if(getTeam().equals("white") 
		  && new Cell(getRow() + 1, getCol() - 1).isValid()
		  && game.cell[getRow() + 1][getCol() - 1].isEmpty() == false
		  && game.cell[getRow() + 1][getCol() - 1].getPiece().getTeam().equals("black")){
			pos.add(new Cell(getRow() + 1, getCol() - 1));
		}
		if(getTeam().equals("white") 
		  && new Cell(getRow() + 1, getCol() + 1).isValid()
		  && game.cell[getRow() + 1][getCol() + 1].isEmpty() == false
		  && game.cell[getRow() + 1][getCol() + 1].getPiece().getTeam().equals("black")){
			pos.add(new Cell(getRow() + 1, getCol() + 1));
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
	
	public boolean isPawn(){
		return true;
	}
	
	
}


