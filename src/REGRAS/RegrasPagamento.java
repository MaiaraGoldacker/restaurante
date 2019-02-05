/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REGRAS;

import DAO.AdicionaisDAO;
import DAO.AdicionalPedidoDAO;
import DAO.ProdutoDAO;
import CLASSE.Adicional;
import CLASSE.AdicionalPedido;
import CLASSE.Comanda;
import CLASSE.Pagamento;
import CLASSE.Pedido;
import CLASSE.Produto;
import DAO.PagamentoDAO;
import DAO.PedidoDAO;
import DAO.UsuarioDAO;
import TELA.ConsultaLucro;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author maiara
 */
public class RegrasPagamento {

    private NumberFormat f = NumberFormat.getCurrencyInstance();
    private static RegrasPagamento instance;
    private Calendar cal = new GregorianCalendar();

    public static RegrasPagamento getInstance() {
        if (instance == null) {
            instance = new RegrasPagamento();
        }
        return instance;
    }

    public Double calcularTotalPagto(List<Pedido> pedidos, String desconto, String acrescimo, boolean ieBruto) {
        try {
            if (desconto.equalsIgnoreCase("")) {
                desconto = "0";
            }

            if (acrescimo.equalsIgnoreCase("")) {
                acrescimo = "0";
            }

            double acresimoDoub = Double.parseDouble(acrescimo);
            double descDoub = Double.parseDouble(desconto);
            double valor = 0;

            for (Pedido ped : pedidos) {
                Produto prod = ProdutoDAO.getInstance().getById(ped.getProdutoId());

                List<AdicionalPedido> adicPed = AdicionalPedidoDAO.getInstance().findAdicionaisDeProdutos(ped.getId(), prod.getId());
                double vlAdicional = 0;
                for (AdicionalPedido adicPedidos : adicPed) {
                    Adicional a = AdicionaisDAO.getInstance().getById(adicPedidos.getAdicionalId());

                    vlAdicional += a.getVladicional();
                }

                valor += prod.getVlproduto() + vlAdicional;
            }
            if (ieBruto) {
                return valor;
            } else {
                valor = CalcularDesconto(valor, descDoub);
                valor = CalcularAcrescimo(valor, acresimoDoub);
                return valor;
            }
        } catch (Exception ex) {
            return 0.0;
        }
    }

    public double CalcularDesconto(double totalBruto, double desconto) {
        if (desconto != 0) {
            return (totalBruto - (totalBruto * (desconto / 100)));
        } else {
            return totalBruto;
        }
    }

    public double CalcularAcrescimo(double totalBruto, double acrecimo) {
        if (acrecimo != 0) {
            return (totalBruto + (totalBruto * (acrecimo / 100)));
        } else {
            return totalBruto;
        }
    }

    public String carregaAdicionaisPedidos(Pedido pedido) throws SQLException {
        String adicionais = "";
        List<AdicionalPedido> adicionalPedido = AdicionalPedidoDAO.getInstance().findAdicionaisDeProdutos(pedido.getId(), pedido.getProdutoId());

        for (AdicionalPedido adPed : adicionalPedido) {
            Adicional ad = AdicionaisDAO.getInstance().getById(adPed.getAdicionalId());
            if (adicionais.equalsIgnoreCase("")) {
                adicionais = ad.getNmadicional();
            } else {
                adicionais += ", " + ad.getNmadicional();
            }

        }
        return adicionais;
    }

    /* public Double carregaLucro(Pagamento pag) {
        try {
            double vlTot = 0;
            double vlAdic = 0;
            List<Pedido> peds = PedidoDAO.getInstance().findAllByPagamento(pag.getId());

            for (Pedido ped : peds) {
                List<AdicionalPedido> adicsPed = AdicionalPedidoDAO.getInstance().findAdicionaisDeProdutos(ped.getId(), ped.getProdutoId());

                for (AdicionalPedido adics : adicsPed) {
                    Adicional ad = AdicionaisDAO.getInstance().getById(adics.getAdicionalId());
                    vlAdic += ad.getVladicional();
                }

                Produto p = ProdutoDAO.getInstance().getById(ped.getProdutoId());

                vlTot += p.getVlproduto();
            }

            return vlTot + vlAdic;
        } catch (Exception e) {
            return 0.0;
        }

    }*/
    public List<Pagamento> atualizaListaFiltros(String dataIni, String dataFim, int idUsuario, String idpagto) throws ParseException, SQLException {
        List<Pagamento> pags = new ArrayList<Pagamento>();
        DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        Date dateini = null;
        Date datefim = null;
        if (!dataIni.equalsIgnoreCase("  /  /    ") && !dataFim.equalsIgnoreCase("  /  /    ")) {
            dateini = new java.sql.Date(((java.util.Date) formatter.parse(dataIni)).getTime());
            datefim = new java.sql.Date(((java.util.Date) formatter.parse(dataFim)).getTime());
        }
        int id = 0;
        if (!idpagto.equalsIgnoreCase("")) {
            id = Integer.parseInt(idpagto);
        }
        pags = PagamentoDAO.getInstance().findAllPersonalizado(dateini, datefim, idUsuario, id);
        return pags;
    }

    public String salvarPagamento(List<Comanda> listaComandasSelecionadas, String acrescimo, String desconto, String valorTotal) {
        try {
            int idPagto = 0;
            Pagamento p = new Pagamento();
            for (Comanda com : listaComandasSelecionadas) {

                if ("".equalsIgnoreCase(acrescimo)) {
                    acrescimo = "0";
                }

                if ("".equalsIgnoreCase(desconto)) {
                    desconto = "0";
                }

                p.setTxdesconto(Double.valueOf(desconto));
                p.setTxacrescimo(Double.valueOf(acrescimo));

                p.setDtatulizacao(cal.getTime());
                p.setUsuarioId(UsuarioDAO.getInstance().getUsuarioLogado().getId());
                p.setVltotal(Float.parseFloat(valorTotal));
                p.setIeativo((short) 1);

                idPagto = PagamentoDAO.getInstance().persist(p);

                List<Pedido> pedidos = PedidoDAO.getInstance().findAllAtender(2, com.getId());

                for (Pedido ped : pedidos) {
                    ped.setPagamentoId(idPagto);
                    ped.setStpedido(3);
                    PedidoDAO.getInstance().merge(ped);
                }

            }
            return "Pagamento realizado com sucesso! Pagamento nº " + idPagto;
        } catch (SQLException ex) {
            Logger.getLogger(RegrasPagamento.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}
