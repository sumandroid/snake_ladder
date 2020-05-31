package domain.models;

import constants.PieceColor;

public class Piece {
    private PieceColor color;
    private Cell currentCell;

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public Piece(PieceColor color) {
        this.color = color;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }
}
