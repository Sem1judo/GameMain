import CRUD.GameCRUD;
import CRUD.PlayerCRUD;
import domain.Game;
import domain.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class GameTest {
    private PlayerCRUD playerCRUD;
    private GameCRUD gameCRUD;

    @Before
    public void start(){
         playerCRUD = new PlayerCRUD();
        gameCRUD = new GameCRUD();
    }
    @Test
    public void create() {
        Player player = playerCRUD.getPlayer(1);
        Game game = gameCRUD.createGame(player, 500);
        Assert.assertNotNull(game);
        System.out.println(game.toString());

    }
    @Test
    public void delete(){
        gameCRUD.deleteGame(3);
    }
    @Test
    public void getGame(){
        gameCRUD.getGame(2);
    }
    @Test
    public void update(){
        Game game = gameCRUD.getGame(2);
       game.setCurrentBet(999);
       game.setRandomNumber(321);
       gameCRUD.update(game);

    }
    @Test
    public void getList(){
        gameCRUD.getList();
    }
}
