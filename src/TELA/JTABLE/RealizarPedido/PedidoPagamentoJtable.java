/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TELA.JTABLE.RealizarPedido;

import DAO.ProdutoDAO;
import CLASSE.Pedido;
import CLASSE.Produto;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maiara
 */
public class PedidoPagamentoJtable extends AbstractTableModel{
       
  public List<Pedido> listaPedidosPagto;
    private String[] colunas = {"Produto" , "Valor"};
    private NumberFormat f = NumberFormat.getCurrencyInstance();
     
    public void addRow(Pedido p){
        this.listaPedidosPagto.add(p);
        this.fireTableDataChanged();
    }
 
    public String getColumnName(int num){
        return this.colunas[num];
    }
 
    @Override
    public int getRowCount() {
        return listaPedidosPagto.size();
    }
 
    @Override
    public int getColumnCount() {
        return colunas.length;
    }
 
    @Override
    public Object getValueAt(int linha, int coluna) {
       Produto p = ProdutoDAO.getInstance().getById(listaPedidosPagto.get(linha).getProdutoId());
        
        switch(coluna){
            case 0: return p.getNmproduto();
            case 1: return f.format(p.getVlproduto());
            case 2: return listaPedidosPagto.get(linha).getObspedido();
        }   
        return null;
    }
    
    public Pedido get(int linha){
    return this.listaPedidosPagto.get(linha);
}   
    
}
