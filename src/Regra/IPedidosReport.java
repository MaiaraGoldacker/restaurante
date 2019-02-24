/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Regra;

/**
 *
 * @author maiara
 */
import Regra.Utilidades;
import java.awt.Frame;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class IPedidosReport {

    public IPedidosReport() {
    }

    public void gerarRelatorioLucro(String idPagamento, int idUsuario, String edIni, String edFim) throws JRException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
        String relat = "C:\\Atividade\\Pedidos\\src\\IREPORTXML\\REL01RelatorioGestaoVendas.jasper";
        HashMap parametros = new HashMap();

        if (idPagamento.equalsIgnoreCase("")) {
            idPagamento = "0";
        }

        edIni = Utilidades.getInstance().formatarDataFiltro(edIni, true);
        edFim = Utilidades.getInstance().formatarDataFiltro(edFim, true); 

        try {
            parametros.put("pID", Integer.parseInt(idPagamento));
            parametros.put("pIdUsuario", idUsuario);
            parametros.put("pDtAtualizacaoIni", edIni);
            parametros.put("pDtAtualizacaoFim", edFim);

            JasperPrint impressao = JasperFillManager.fillReport(relat, parametros, Utilidades.getInstance().pegarConexaoBD());
            JasperViewer viewer = new JasperViewer(impressao, false);
            viewer.setTitle("Relatório de vendas no período");
            viewer.setExtendedState(Frame.MAXIMIZED_BOTH);
            viewer.setVisible(true);
        } catch (JRException ex) {

            Logger.getLogger(IPedidosReport.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
    
      public void gerarRelatorioPadrao(String caminho, String titulo) throws SQLException {
        try{
            JasperPrint impressao = JasperFillManager.fillReport(caminho, null, Utilidades.getInstance().pegarConexaoBD());
            JasperViewer viewer = new JasperViewer(impressao, false);
            viewer.setTitle(titulo);
            viewer.setExtendedState(Frame.MAXIMIZED_BOTH);
            viewer.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(IPedidosReport.class.getName()).log(Level.SEVERE, null, ex);
        }           
    }
}
