package DAO;

import CLASSE.Comanda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author maiara
 */

    public class ComandaDAO {
    //private static ClienteJpaDAO instance;
    private static ComandaDAO instance;
    protected EntityManager entityManager;

    public static ComandaDAO getInstance() {
        if (instance == null) {
            instance = new ComandaDAO();
        }

        return instance;
    }

    private ComandaDAO() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidosPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public void removeById(final int id) {
      Comanda c = entityManager.find(Comanda.class, id);
      c.setIeativo((short) 0);
      merge(c);
        //remove(entityManager.find(Comanda.class, id));
    }

    @SuppressWarnings("unchecked")
    public List<Comanda> findAll() throws SQLException {
        List<Comanda> com = new ArrayList<Comanda>();
        String url = "jdbc:mysql://localhost:3306/Trabalho1";
        Connection conn = DriverManager.getConnection(url, "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT ID FROM COMANDA WHERE IEATIVO=1");
        while (rs.next()) {
            com.add(getById(rs.getInt("ID")));
        }

        conn.close();
        return com;
    }

    public Comanda getById(final int id) {
        return entityManager.find(Comanda.class, id);
    }

    public void persist(Comanda comanda) {
        //getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(comanda);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Comanda comanda) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(comanda);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    private void remove(Comanda comanda) {
        try {
            entityManager.getTransaction().begin();
            comanda = entityManager.find(Comanda.class, comanda.getId());
            entityManager.remove(comanda);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
