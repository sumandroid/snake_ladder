package services;

import domain.models.*;

public class GameServiceImpl implements IGameService {

    private Game game;
    private BoardService boardService;

    public GameServiceImpl(BoardService boardService, Game game) {
        this.boardService = boardService;
        this.game = game;
    }


    @Override
    public void setPlayers(Player playerOne, Player playerTwo) {
        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);
        setCurrentPositions(playerOne, playerTwo);
    }

    @Override
    public void moveToCell(Piece piece, int number) {
        if(piece.getCurrentCell().getNumber() == 1 && number != 6){
            System.out.println("Cannot move, your number was " + number);
            return;
        }
        Cell nextCell = boardService.getCellByNumber(piece.getCurrentCell().getNumber() + number);
        Snake snake = boardService.checkAndGetSnakeAtCell(nextCell);
        Ladder ladder = boardService.checkAndGetLadder(nextCell);
        if(snake != null){
            System.out.println("snake found, moved to " + snake.getEndCell().getNumber());
            piece.setCurrentCell(snake.getEndCell());
        }else if(ladder != null){
            System.out.println("ladder found, moved to " + ladder.getEndCell().getNumber());
            piece.setCurrentCell(ladder.getEndCell());
        }else{
            piece.setCurrentCell(nextCell);
            System.out.println("moved to " + piece.getCurrentCell().getNumber());
        }
    }

    private void setCurrentPositions(Player playerOne, Player playerTwo) {
        Cell startCell = game.getBoard().getNumberToCellMap().get(1);
        playerOne.getPiece().setCurrentCell(startCell);
        playerTwo.getPiece().setCurrentCell(startCell);
    }
}
