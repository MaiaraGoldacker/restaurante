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
     //   remove(entityManager.find(Produto.class, id));
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
    
     public List<Produto> findAllMaisVendidos(int idClassificacao) throws SQLException {
        List<Produto> prod = new ArrayList<Produto>();
        String url = "jdbc:mysql://localhost:3306/Trabalho1";
        Connection conn = DriverManager.getConnection(url, "root", "root");
     
        PreparedStatement stmt;
        
        String sql = "";
        
       sql = " select pr.id, count(pr.id) apelido from pagamento pa  inner join pedido as pe on pa.id = pe.pagamento_id "   
            + " inner join produto as pr on  pr.id = pe.produto_id  where 1=1 "
            + " and pr.classificacao = ? or ?=0 "
            + " and pr.ieativo = 1 "
            + " and pa.ieativo = 1 "
            + " group by pr.id "
            + " order by count(pr.id) desc "   ;
        
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idClassificacao);
        stmt.setInt(2, idClassificacao);
        ResultSet rs = stmt.executeQuery();
       
        while (rs.next()) {
            prod.add(getById(rs.getInt("ID")));
            
             for (Produto a : prod) {
                if (a.getId() == rs.getInt("ID")) {
                    a.setVlproduto(rs.getFloat("apelido"));
                }
            }
        }
        conn.close();
        return prod;
    }

    public Produto getById(final int id) {
        return entityManager.find(Produto.class, id);
    }

    public void persist(Produto produto) {
        //getEntityManager();
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
