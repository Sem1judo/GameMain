package domain;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class runer {
        public static void main(String[] args) {
            // Product product = new Product(1,"Product23",40,60.2, LocalDateTime.now());


            Session curSession = null;
            Transaction transaction = null;


            try {
                curSession = HibernateUtil.getSessionFactory().openSession();
                transaction = curSession.beginTransaction();

                Player player = new Player();
                player.setNickName("Jeka");
                player.setPhone("09344231");
                player.setBalance(0);

                curSession.save(player);


                Game game = new Game();
                game.setStartGame(LocalDateTime.now());
                game.setEndGame(LocalDateTime.now());
                game.setPlayer(player);

                curSession.save(game);



                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println(e.getMessage());
            }
        }
    }
