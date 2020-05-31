package services;

import domain.models.Cell;
import domain.models.Ladder;
import domain.models.Snake;

public interface BoardService {

    Cell getCellByNumber(int number);

    Snake checkAndGetSnakeAtCell(Cell cell);

    Ladder checkAndGetLadder(Cell cell);
}
