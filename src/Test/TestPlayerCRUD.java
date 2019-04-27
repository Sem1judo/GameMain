import CRUD.PlayerCRUD;

import domain.Player;
import org.junit.Before;
import org.junit.Test;

public class TestPlayerCRUD {

    private PlayerCRUD playerCRUD;

    @Before
    public void initDao(){
        playerCRUD = new PlayerCRUD();
    }

    @Test
    public void create() {
        Player player = new Player();
        PlayerCRUD playerCRUD = new PlayerCRUD();
        playerCRUD.create("Delete", "0947594531", 1000);

    }

    @Test
    public void update() {
        Player player = playerCRUD.getPlayer(1);
        player.setBalance(77777);
        playerCRUD.update(player);



    }

    @Test
    public void delete() {
        Player player = new Player();
        PlayerCRUD playerCRUD = new PlayerCRUD();
        playerCRUD.deletePlayer(4);

    }

    @Test
    public void read() {
        Player player = new Player();
        PlayerCRUD playerCRUD = new PlayerCRUD();
        playerCRUD.getPlayer(1);

    }

    @Test
    public void getList() {
        Player player = new Player();
        PlayerCRUD playerCRUD = new PlayerCRUD();
        playerCRUD.getListPlayer();

    }
}
