package services;

import domain.models.Game;
import domain.models.Piece;
import domain.models.Player;

public interface IGameService {

    void setPlayers(Player playerOne, Player playerTwo);

    void moveToCell(Piece piece, int number);
}
