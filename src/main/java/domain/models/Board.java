package domain.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {

    private HashMap<Integer, Cell> numberToCellMap;
    private HashMap<Cell, Snake> cellSnakeHashMap;
    private HashMap<Cell, Ladder> cellLadderHashMap;
    private volatile static Board board;
    private static final int[][] snakePositions = new int[][]{{17, 4}, {20, 6}, {24, 16},
            {34, 12}, {32, 30}, {89, 53}, {74, 45}, {99,41}};
    private static final int[][] ladderPositions = new int[][]{{4, 25}, {13, 46}, {33, 49}, {50, 69}, {42, 63},
            {62, 81}, {74, 92}};

    public HashMap<Integer, Cell> getNumberToCellMap() {
        return numberToCellMap;
    }

    public HashMap<Cell, Snake> getCellSnakeHashMap() {
        return cellSnakeHashMap;
    }


    public HashMap<Cell, Ladder> getCellLadderHashMap() {
        return cellLadderHashMap;
    }

    private Board(){
        numberToCellMap = new HashMap<>();
        cellSnakeHashMap = new HashMap<>();
        cellLadderHashMap = new HashMap<>();
        setUpCells();
        setUpsnakes();
        setUpLadders();
    }

    public static Board getInstance(){
        if(board == null){
            synchronized (Board.class){
                if(board == null){
                    board = new Board();
                }
            }
        }
        return board;
    }

    private void setUpCells(){
        for(int i = 1; i <= 100; i++){
            Cell cell = new Cell(i);
            numberToCellMap.put(i, cell);
        }
    }

    private void setUpsnakes(){
        for(int[] position : snakePositions){
            int startPosition = position[0];
            int endPosition = position[1];
            Cell startCell = numberToCellMap.get(startPosition);
            Cell endCell = numberToCellMap.get(endPosition);
            Snake snake = new Snake(startCell, endCell);
            cellSnakeHashMap.put(startCell, snake);
        }
    }

    private void setUpLadders(){
        for(int[] position : ladderPositions){
            int startPosition = position[0];
            int endPosition = position[1];
            Cell startCell = numberToCellMap.get(startPosition);
            Cell endCell = numberToCellMap.get(endPosition);
            Ladder ladder = new Ladder(startCell, endCell);
            cellLadderHashMap.put(startCell, ladder);
        }
    }


}
