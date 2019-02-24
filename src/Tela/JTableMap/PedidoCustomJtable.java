/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tela.JTableMap;

import Dao.PedidoDAO;
import Dao.ProdutoDAO;
import Classe.Pedido;
import Classe.Produto;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maiara
 */
public class PedidoCustomJtable extends AbstractTableModel {

    private List<Pedido> dados;
    private String[] colunas = {"Produto", "Valor", "Observação"};
    private NumberFormat f = NumberFormat.getCurrencyInstance();

    public PedidoCustomJtable(List<Pedido> dadosV, int comanda) throws SQLException {
        if (dadosV != null) {
            dados = dadosV;
        } else {
            dados = PedidoDAO.getInstance().findAll(comanda);
        }
    }

    public void addRow(Pedido p) {
        this.dados.add(p);
        this.fireTableDataChanged();
    }

    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Produto p = ProdutoDAO.getInstance().getById(dados.get(linha).getProdutoId());

        switch (coluna) {
            case 0:
                return p.getNmproduto();
            case 1:
                return f.format(p.getVlproduto());
            case 2:
                return dados.get(linha).getObspedido();
        }
        return null;
    }

    public Pedido get(int linha) {
        return this.dados.get(linha);
    }

}
