package CRUD;

import domain.HibernateUtil;
import domain.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;
import java.util.ArrayList;

public class PlayerCRUD {

  Transaction transaction = null;


    public void create(String nickName, String phone, int balance) {
        Player playerobj = null;
        try (Session curSession = HibernateUtil.getSessionFactory().openSession()) {
            transaction = curSession.beginTransaction();


            playerobj = new Player();
            playerobj.setNickName(nickName);
            playerobj.setPhone(phone);
            playerobj.setBalance(balance);
            curSession.save(playerobj);


            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
        public void update(Player player) {

            try (Session curSession = HibernateUtil.getSessionFactory().openSession()) {

                transaction = curSession.beginTransaction();
                Query query = curSession.createQuery("update Player set " +
                        "nickName = :nickname," +
                        "phone = :phone," +
                        "balance = :balance" +
                        " where id = :id");
                query.setParameter("id",player.getId());
                query.setParameter("nickname", player.getNickName());
                query.setParameter("phone", player.getPhone());
                query.setParameter("balance", player.getBalance());
                int result = query.executeUpdate();


                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }

        }

        public void deletePlayer ( int id){
            try (Session curSession = HibernateUtil.getSessionFactory().openSession()) {
                transaction = curSession.beginTransaction();

                Player player = curSession.get(Player.class, id);
                if (player != null) {
                    curSession.delete(player);
                    System.out.println("player " + id + " is deleted");
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }

        public Player getPlayer ( int id){
            Player player = null;
            try (Session curSession = HibernateUtil.getSessionFactory().openSession()) {
                transaction = curSession.beginTransaction();

                player = curSession.get(Player.class, id);
                System.out.println(player.getNickName());
                System.out.println(player.getPhone());
                System.out.println(player.getBalance());

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return player;
        }


        public List getListPlayer () {
            List playerList = new ArrayList();
            try (Session curSession = HibernateUtil.getSessionFactory().openSession()){
                transaction = curSession.beginTransaction();

                playerList = curSession.createQuery("FROM Player ").list();
                System.out.println(playerList);
            }catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return playerList;
        }
    }

