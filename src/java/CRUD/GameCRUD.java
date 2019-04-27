package CRUD;

import domain.Game;
import domain.HibernateUtil;
import domain.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.*;


public class GameCRUD {


    public Game createGame(Player player, int bet) {
        Game game = null;
        try (Session curSession = HibernateUtil.getSessionFactory().openSession()) {
            curSession.beginTransaction();
            game = new Game();
            game.setPlayer(player);
            game.setCurrentBet(bet);
            game.initGame();

            curSession.save(game);  // Why here don't need transaction.commit ?
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game;
    }

    public void update(Game game) {
        Transaction transaction = null;
        try (Session curSession = HibernateUtil.getSessionFactory().openSession()) {
           transaction= curSession.beginTransaction();

            Query query = curSession.createQuery("update Game " +
                    "set " +
                    "currentBet = :currentBet," +
                    "player = :player," +
                    "randomNumber = :randomNumber" +
                    " where id = :id");
            query.setParameter("id", game.getId());
            query.setParameter("player", game.getPlayer());
            query.setParameter("randomNumber", game.getRandomNumber());
            query.setParameter("currentBet", game.getCurrentBet());
            int result = query.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Game getGame(int gameId) {
        Game game = null;
        Transaction transaction = null;
        try (Session curSession = HibernateUtil.getSessionFactory().openSession()) {
            transaction = curSession.beginTransaction();

            game = curSession.get(Game.class, gameId);
            System.out.println(game.getPlayer());
            System.out.println(game.getCurrentBet());
            System.out.println(game.getRandomNumber());
            System.out.println(game.getStartGame());
            System.out.println(game.getEndGame());

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return game;
    }

    public void deleteGame(int idGame) {
        Transaction transaction = null;
        try (Session curSession = HibernateUtil.getSessionFactory().openSession()) {
            transaction = curSession.beginTransaction();

            Game game = curSession.get(Game.class, idGame);
            if (game != null) {
                curSession.delete(game);
                System.out.println("Game " + idGame + " is deleted");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List getList() {
        List gameList = new ArrayList();
        Transaction transaction = null;
        try (Session curSession = HibernateUtil.getSessionFactory().openSession()) {
            transaction = curSession.beginTransaction();

            gameList = curSession.createQuery("FROM Game ").list();
            System.out.println(gameList);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return gameList;
    }
}


