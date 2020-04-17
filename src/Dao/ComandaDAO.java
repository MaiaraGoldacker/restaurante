package Dao;

import Classe.Comanda;
import Regra.Utilidades;
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
    }

    @SuppressWarnings("unchecked")
    public List<Comanda> findAll() throws SQLException {
        List<Comanda> com = new ArrayList<Comanda>();
        Statement stmt = Utilidades.getInstance().pegarConexaoBD().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ID FROM COMANDA WHERE IEATIVO=1");
        while (rs.next()) {
            com.add(getById(rs.getInt("ID")));
        }

        Utilidades.getInstance().pegarConexaoBD().close();
        return com;
    }

    public Comanda getById(final int id) {
        return entityManager.find(Comanda.class, id);
    }

    public void persist(Comanda comanda) {
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
}
