package services;

import domain.models.*;

public class BoardServiceImpl implements BoardService {

    private Board board;

    public BoardServiceImpl(Board board){
        this.board = board;
    }

    @Override
    public Cell getCellByNumber(int number) {
        Cell cell = board.getNumberToCellMap().get(number);
        if(cell == null){
            throw new RuntimeException("Invalid cell number");
        }
        return cell;
    }

    @Override
    public Snake checkAndGetSnakeAtCell(Cell cell) {
        return board.getCellSnakeHashMap().get(cell);
    }

    @Override
    public Ladder checkAndGetLadder(Cell cell) {
        return board.getCellLadderHashMap().get(cell);
    }
}
