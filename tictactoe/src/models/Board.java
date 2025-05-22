package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimension;
    private List<List<Cell>> grid;

    public Board(int dimension) {
        this.dimension = dimension;
        grid = new ArrayList<List<Cell>>();
        for (int i = 0; i < this.dimension; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < this.dimension; j++) {
                grid.get(i).add(new Cell(i, j));
            }
        }
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }

    public void printBoard(){
        for (List<Cell> row: grid){
            System.out.print('|');
            for (Cell cell: row){
                if (cell.getStatus() == CellStatus.OCCUPIED){
                    System.out.print(" " + cell.getPlayer().getSymbol() + " ");
                }
                else{
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
}
