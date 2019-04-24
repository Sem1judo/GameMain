package CRUD;

import domain.HibernateUtil;
import domain.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.ArrayList;

public class PlayerCRUD {
    static Session curSession;
    static Transaction transaction;

    public static void create() {
        int count = 0;
        Player player = null;
        try {
            curSession = HibernateUtil.getSessionFactory().openSession();
            transaction = curSession.beginTransaction();

            for (int j = 102; j <= 105; j++) {
                count = count + 1;
                player = new Player();
                player.setNickName("NickName");
                player.setPhone("0962750188");
                player.setBalance(100);
                curSession.save(player);
            }

            transaction.commit();
        } catch (Exception sqlException) {
            if (null != curSession) {
                transaction.rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (curSession != null) {
                curSession.close();
            }
        }
    }

    public static void update(int playerId) {
        try {
            curSession = HibernateUtil.getSessionFactory().openSession();
            transaction = curSession.beginTransaction();

            Player playerObj = curSession.get(Player.class, playerId);
            playerObj.setNickName("someNewName");
            playerObj.setBalance(300);

            transaction.commit();
        } catch (Exception sqlException) {
            if (null != curSession) {
                transaction.rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (curSession != null) {
                curSession.close();
            }
        }

    }
    public static void deletePlayer(Integer playerId) {

        try {
            // Getting Session Object From SessionFactory
            curSession = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            transaction = curSession.beginTransaction();

            Player playerObj = findById(playerId);
            curSession.delete(playerObj);


            // Committing The Transactions To The Database
            transaction.commit();
        } catch(Exception sqlException) {
            if(null != curSession) {
                transaction.rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(curSession != null) {
                curSession.close();
            }
        }
    }

    public static Player findById(Integer playerId) {
        Player findPlayer = null;
        try {
            // Getting Session Object From SessionFactory
            curSession = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            transaction = curSession.beginTransaction();

            findPlayer = curSession.load(Player.class, playerId);
        } catch(Exception sqlException) {
            if(null != curSession) {
              transaction.rollback();
            }
            sqlException.printStackTrace();
        }
        return findPlayer;
    }

    public static List getListPlayer() {
        List playerList = new ArrayList();
        try {
            // Getting Session Object From SessionFactory
            curSession = HibernateUtil.getSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            transaction = curSession.beginTransaction();

            playerList = curSession.createQuery("FROM Player ").list();
        } catch(Exception sqlException) {
            if(null != curSession) {
                transaction.rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(curSession != null) {
                curSession.close();
            }
        }
        return playerList;
    }
}
