/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tela.JTableMap;

import Classe.Adicional;
import Classe.AdicionalPedido;
import Dao.PagamentoDAO;
import Classe.Pagamento;
import Classe.Pedido;
import Classe.Produto;
import Dao.AdicionaisDAO;
import Dao.AdicionalPedidoDAO;
import Dao.PedidoDAO;
import Dao.ProdutoDAO;
import Regra.RegrasPagamento;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author maiara
 */
public class PagamentoJtable extends AbstractTableModel {

    private NumberFormat f = NumberFormat.getCurrencyInstance();
    private List<Pagamento> dados;
    private String[] colunas = {"Pagamento", "Acréscimo", "Desconto", "Valor Total Pago"};

    public PagamentoJtable(List<Pagamento> dadosV) throws SQLException {
        if (dadosV == null) {
            dados = PagamentoDAO.getInstance().findAll();
        } else {
            dados = dadosV;
        }
    }

    public void addRow(Pagamento a) {
        this.dados.add(a);
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
                return dados.get(linha).getId();
            case 1:
                return dados.get(linha).getTxacrescimo();
            case 2:
                return dados.get(linha).getTxdesconto();
            case 3: {
                return f.format(dados.get(linha).getVltotal());
            }

        }
        return null;
    }

    public Pagamento get(int linha) {
        return this.dados.get(linha);
    }
}
