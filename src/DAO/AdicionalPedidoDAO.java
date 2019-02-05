package DAO;

import CLASSE.AdicionalPedido;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public class AdicionalPedidoDAO {
    private static AdicionalPedidoDAO instance;
    protected EntityManager entityManager;

    public static AdicionalPedidoDAO getInstance() {
        if (instance == null) {
            instance = new AdicionalPedidoDAO();
        }

        return instance;
    }

    private AdicionalPedidoDAO() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidosPU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

   /* public void removeById(final int id) {
      AdicionalPedido a = entityManager.find(AdicionalPedido.class, id);
    //  a.setIeativo((short) 0);
    remove(entityManager.find(AdicionalPedido.class, id));
      //merge(a);
    
    }*/

    @SuppressWarnings("unchecked")
    public List<AdicionalPedido> findAll() throws SQLException {
        List<AdicionalPedido> com = new ArrayList<AdicionalPedido>();
        String url = "jdbc:mysql://localhost:3306/Trabalho1";
        Connection conn = DriverManager.getConnection(url, "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT ID FROM ADICIONAL_PEDIDO WHERE IEATIVO=1");
        while (rs.next()) {
            com.add(getById(rs.getInt("ID")));
        }

        conn.close();
        return com;
    }
    
    public List<AdicionalPedido> findAdicionalPedidos(int idAdicional, int idProduto, int idPedido) throws SQLException {
           List<AdicionalPedido> prod = new ArrayList<AdicionalPedido>();
        String url = "jdbc:mysql://localhost:3306/Trabalho1";
        Connection conn = DriverManager.getConnection(url, "root", "root");

        PreparedStatement stmt;
        stmt = conn.prepareStatement("SELECT ID FROM ADICIONAL_PEDIDO where Adicional_ID = ? and Produto_ID = ? and Pedido_ID = ?");
        stmt.setInt(1, idAdicional);
        stmt.setInt(2, idProduto);
        stmt.setInt(3, idPedido);
        ResultSet rs = stmt.executeQuery();
       
        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }

        conn.close();
        return prod;
    }
    
    
      public List<AdicionalPedido> findAdicionaisDeProdutos(int idPedido, int idProduto) throws SQLException {
           List<AdicionalPedido> prod = new ArrayList<AdicionalPedido>();
        String url = "jdbc:mysql://localhost:3306/Trabalho1";
        Connection conn = DriverManager.getConnection(url, "root", "root");

        PreparedStatement stmt;
        stmt = conn.prepareStatement("SELECT ID FROM ADICIONAL_PEDIDO where Produto_ID = ? and Pedido_ID = ?");
        stmt.setInt(1, idProduto);
        stmt.setInt(2, idPedido);
        ResultSet rs = stmt.executeQuery();
       
        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }

        conn.close();
        return prod;
    }

    public AdicionalPedido getById(final int id) {
        return entityManager.find(AdicionalPedido.class, id);
    }

    public void persist(AdicionalPedido adicional) {
        //getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(adicional);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(AdicionalPedido adicional) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(adicional);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(AdicionalPedido adicional) {
        try {
            entityManager.getTransaction().begin();
            adicional = entityManager.find(AdicionalPedido.class, adicional.getId());
            entityManager.remove(adicional);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
