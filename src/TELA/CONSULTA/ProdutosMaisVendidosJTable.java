/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TELA.CONSULTA;

import CLASSE.Produto;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maiara
 */
public class ProdutosMaisVendidosJTable extends AbstractTableModel {

    private List<Produto> dados;
    private String[] colunas = {"Quantidade", "Produto", "Classificação"};
    private NumberFormat f = NumberFormat.getCurrencyInstance();

    public ProdutosMaisVendidosJTable(List<Produto> dadosV) throws SQLException {
        dados = dadosV;

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
                return String.valueOf(dados.get(linha).getVlproduto()).replace(".0", "");
            case 1:
                return dados.get(linha).getNmproduto();          
            case 2:

                if (dados.get(linha).getClassificacao() == 0) {
                    return "Sem Classificação";
                } else if (dados.get(linha).getClassificacao() == 1) {
                    return "Hamburguer";
                } else if (dados.get(linha).getClassificacao() == 2) {
                    return "Sobremesa";
                } else if (dados.get(linha).getClassificacao() == 3) {
                    return "Bebida";
                } else {
                    return "Outros";
                }
        }
        return null;
    }

    public Produto get(int linha) {
        return this.dados.get(linha);
    }
}
