import constants.PieceColor;
import domain.models.Board;
import domain.models.Game;
import domain.models.Piece;
import domain.models.Player;
import services.BoardService;
import services.BoardServiceImpl;
import services.GameServiceImpl;
import services.IGameService;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class DriverClass {

    public static void main(String []args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name player one");
        String playerOneName = scanner.nextLine();
        System.out.println("Enter color choice: red or blue");
        String colorChoice = scanner.nextLine();
        while(!isValidColorChoice(colorChoice)){
            System.out.println("Wrong choice, pick red or blue");
            colorChoice = scanner.nextLine();
        }
        Piece piece = new Piece(PieceColor.getEnum(colorChoice));
        Player playerOne = new Player(playerOneName, piece);
        System.out.println("Enter name player two");
        String playerTwoName = scanner.nextLine();
        PieceColor playerTwoColor = playerOne.getPiece().getColor()
                .equals(PieceColor.BLUE) ? PieceColor.RED : PieceColor.BLUE;
        Piece playerTwoPiece = new Piece(playerTwoColor);
        Player playerTwo = new Player(playerTwoName, playerTwoPiece);
        System.out.println(playerOne.getName() + " is " + playerOne.getPiece().getColor().toString());
        System.out.println(playerTwo.getName() + " is " + playerTwo.getPiece().getColor().toString());
        System.out.println("**************Enter snake ladder!!!!***************");

        Game game = new Game(Board.getInstance(), playerOne, playerTwo);
        BoardService boardService = new BoardServiceImpl(game.getBoard());
        IGameService gameService = new GameServiceImpl(boardService, game);
        gameService.setPlayers(playerOne, playerTwo);

        int i = 0;
        Player winner = null;

        while(winner == null){
            try{
                if( i % 2 == 0){
                    System.out.println("roll dice " + playerOne.getName());
                    String command = scanner.nextLine();
                    if(command.equalsIgnoreCase("roll dice")){
                        int number = diceRoll();
                        System.out.println("dice number is: " + number);
                        gameService.moveToCell(playerOne.getPiece(), number);
                    }else if(command.equalsIgnoreCase("exit")){
                        winner = playerTwo;
                    }
                }else{
                    System.out.println("roll dice " + playerTwo.getName());
                    String command = scanner.nextLine();
                    if(command.equalsIgnoreCase("roll dice")){
                        int number = diceRoll();
                        System.out.println("dice number is: " + number);
                        gameService.moveToCell(playerTwo.getPiece(), number);
                    }else if(command.equalsIgnoreCase("exit")){
                        winner = playerOne;
                    }
                }
                System.out.println();
                winner = checkForWinner(playerOne, playerTwo);
                i++;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("winner is " + winner.getName());

    }


    private static boolean isValidColorChoice(String chosenColor){
        return PieceColor.getEnum(chosenColor) != null;
    }

    private static int diceRoll(){
        return ThreadLocalRandom.current().nextInt(1, 6 + 1);
    }

    private static Player checkForWinner(Player playerOne, Player playerTwo){
        return playerOne.getPiece().getCurrentCell().getNumber() == 100 ? playerOne
                : (playerTwo.getPiece().getCurrentCell().getNumber() == 100 ? playerTwo : null);
    }
}
