/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tela.JTableMap;

import Dao.ProdutoDAO;
import Classe.Produto;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maiara
 */
public class ProdutoCustomJtable extends AbstractTableModel {

    private List<Produto> dados;
    private String[] colunas = {"Produto", "Valor"};
    private NumberFormat f = NumberFormat.getCurrencyInstance();

    public ProdutoCustomJtable(List<Produto> dadosV) throws SQLException {
        if (dadosV != null) {
            dados = dadosV;
        } else {
            dados = ProdutoDAO.getInstance().findAll();
        }
    }

    public void addRow(Produto p) {
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
        switch (coluna) {
            case 0:
                return dados.get(linha).getNmproduto();
            case 1:
                return f.format(dados.get(linha).getVlproduto());
        }
        return null;
    }

    public Produto get(int linha) {
        return this.dados.get(linha);
    }
}
