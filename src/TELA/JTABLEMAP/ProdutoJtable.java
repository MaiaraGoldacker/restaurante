/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TELA.JTABLEMAP;

import DAO.ProdutoDAO;
import CLASSE.Produto;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maiara
 */
public class ProdutoJtable extends AbstractTableModel {

    private List<Produto> dados;
    private String[] colunas = {"Produto", "Valor", "Observação", "Classificação"};
    private NumberFormat f = NumberFormat.getCurrencyInstance();

    public ProdutoJtable(List<Produto> dadosV) throws SQLException {
        if (dadosV == null) {
            dados = ProdutoDAO.getInstance().findAll();
        } else {
            dados = dadosV;
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
            case 2:
                return dados.get(linha).getDsobservacaoproduto();
            case 3:
                
                if (dados.get(linha).getClassificacao() == 0){
                    return "Sem Classificação";
                } else if (dados.get(linha).getClassificacao() == 1){
                    return "Hamburguer";
                } else if (dados.get(linha).getClassificacao() == 2){
                    return "Sobremesa";
                } else if (dados.get(linha).getClassificacao() == 3){
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
