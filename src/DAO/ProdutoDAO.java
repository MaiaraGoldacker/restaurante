package DAO;

import CLASSE.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author maiara
 */
public class ProdutoDAO {

    private static ProdutoDAO instance;
    protected EntityManager entityManager;

    public static ProdutoDAO getInstance() {
        if (instance == null) {
            instance = new ProdutoDAO();
        }

        return instance;
    }

    private ProdutoDAO() {
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
      Produto p = entityManager.find(Produto.class, id);
      p.setIeativo((short) 0);
      merge(p);
    }

    @SuppressWarnings("unchecked")
    public List<Produto> findAll() throws SQLException {
        List<Produto> prod = new ArrayList<Produto>();
        String url = "jdbc:mysql://localhost:3306/Trabalho1";
        Connection conn = DriverManager.getConnection(url, "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT ID FROM Produto WHERE IEATIVO = 1");
        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }

        conn.close();
        return prod;
    }
    
    public List<Produto> findAllbyClassif(int idClassificacao) throws SQLException {
        List<Produto> prod = new ArrayList<Produto>();
        String url = "jdbc:mysql://localhost:3306/Trabalho1";
        Connection conn = DriverManager.getConnection(url, "root", "root");
     
        PreparedStatement stmt;
        stmt = conn.prepareStatement("SELECT ID FROM Produto WHERE IEATIVO = 1 and classificacao = ?");
        stmt.setInt(1, idClassificacao);
        ResultSet rs = stmt.executeQuery();
       
        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
        }
        conn.close();
        return prod;
    }
    
    public Produto getById(final int id) {
        return entityManager.find(Produto.class, id);
    }

    public void persist(Produto produto) {   
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(produto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Produto produto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(produto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    private void remove(Produto produto) {
        try {
            entityManager.getTransaction().begin();
            produto = entityManager.find(Produto.class, produto.getId());
            entityManager.remove(produto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}
