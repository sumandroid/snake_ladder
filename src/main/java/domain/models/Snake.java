package domain.models;

public class Snake {

    private Cell startCell;
    private Cell endCell;

    public Cell getStartCell() {
        return startCell;
    }

    public void setStartCell(Cell startCell) {
        this.startCell = startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }

    public void setEndCell(Cell endCell) {
        this.endCell = endCell;
    }

    public Snake(Cell startCell, Cell endCell) {
        this.startCell = startCell;
        this.endCell = endCell;
    }
}
