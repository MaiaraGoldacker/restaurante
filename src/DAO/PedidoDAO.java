package DAO;

import CLASSE.Pedido;
import REGRAS.Utilidades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author maiara
 */
public class PedidoDAO {

    //private static ClienteJpaDAO instance;
    private static PedidoDAO instance;
    protected EntityManager entityManager;

    public static PedidoDAO getInstance() {
        if (instance == null) {
            instance = new PedidoDAO();
        }

        return instance;
    }

    private PedidoDAO() {
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
        remove(entityManager.find(Pedido.class, id));
    }

    @SuppressWarnings("unchecked")
    public List<Pedido> findAll(int comanda) throws SQLException {
        List<Pedido> ped = new ArrayList<Pedido>();
        PreparedStatement stmt;
        stmt = Utilidades.getInstance().pegaConexaoBD().prepareStatement("SELECT ID FROM Pedido where COMANDA_ID = ?");
        stmt.setInt(1, comanda);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            ped.add(getById(rs.getInt("ID")));
        }

        Utilidades.getInstance().pegaConexaoBD().close();
        return ped;
    }

    @SuppressWarnings("unchecked")
    public List<Pedido> findAllByPagamento(int pagamento) throws SQLException {
        List<Pedido> prod = new ArrayList<Pedido>();
        PreparedStatement stmt = Utilidades.getInstance().pegaConexaoBD().prepareStatement("SELECT ID FROM Pedido where PAGAMENTO_ID = ?");
        stmt.setInt(1, pagamento);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }

        Utilidades.getInstance().pegaConexaoBD().close();
        return prod;
    }

    public List<Pedido> findAllAtender(int stPedido, int comanda) throws SQLException {
        List<Pedido> prod = new ArrayList<Pedido>();

        PreparedStatement stmt;
        stmt = Utilidades.getInstance().pegaConexaoBD().prepareStatement("SELECT ID FROM Pedido where STPEDIDO = ? AND COMANDA_ID = ?");
        stmt.setInt(1, stPedido);
        stmt.setInt(2, comanda);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }

        Utilidades.getInstance().pegaConexaoBD().close();
        return prod;
    }

    public List<Pedido> findAllAtendimento(int stPedido, int comanda) throws SQLException {
        List<Pedido> prod = new ArrayList<Pedido>();

        PreparedStatement stmt = Utilidades.getInstance().pegaConexaoBD().prepareStatement("SELECT ID FROM Pedido where STPEDIDO <> ? AND COMANDA_ID = ?");
        stmt.setInt(1, stPedido);
        stmt.setInt(2, comanda);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }

        Utilidades.getInstance().pegaConexaoBD().close();
        return prod;
    }

    @SuppressWarnings("unchecked")
    public List<Pedido> findAtualizarPedido(int comanda) throws SQLException {
        List<Pedido> prod = new ArrayList<Pedido>();

        PreparedStatement stmt = Utilidades.getInstance().pegaConexaoBD().prepareStatement("SELECT ID FROM Pedido where DTATUALIZACAO IS NULL AND COMANDA_ID = ?");
        stmt.setInt(1, comanda);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }

        Utilidades.getInstance().pegaConexaoBD().close();
        return prod;
    }

    @SuppressWarnings("unchecked")
    public List<Pedido> findAllPedidos() throws SQLException {
        List<Pedido> prod = new ArrayList<Pedido>();
       PreparedStatement stmt = Utilidades.getInstance().pegaConexaoBD().prepareStatement("SELECT ID FROM Pedido where STPEDIDO = 1 GROUP BY COMANDA_ID ORDER BY  DTATUALIZACAO");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }

        Utilidades.getInstance().pegaConexaoBD().close();
        return prod;
    }

    public Pedido getById(final int id) {
        return entityManager.find(Pedido.class, id);
    }

    public void persist(Pedido pedido) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(pedido);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Pedido cliente) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    private void remove(Pedido pedido) {
        try {
            entityManager.getTransaction().begin();
            pedido = entityManager.find(Pedido.class, pedido.getId());
            entityManager.remove(pedido);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
