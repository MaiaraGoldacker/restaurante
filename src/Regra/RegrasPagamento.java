/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Regra;

import Dao.AdicionaisDAO;
import Dao.AdicionalPedidoDAO;
import Dao.ProdutoDAO;
import Classe.Adicional;
import Classe.AdicionalPedido;
import Classe.Comanda;
import Classe.Pagamento;
import Classe.Pedido;
import Classe.Produto;
import Dao.PagamentoDAO;
import Dao.PedidoDAO;
import Dao.UsuarioDAO;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author maiara
 */
public class RegrasPagamento {
    private Instant agora = Instant.now();
    private Date myDate = Date.from(agora);
    private NumberFormat f = NumberFormat.getCurrencyInstance();
    private static RegrasPagamento instance;    

    public static RegrasPagamento getInstance() {
        if (instance == null) {
            instance = new RegrasPagamento();
        }
        return instance;
    }

    public Double calcularTotalPagto(List<Pedido> pedidos, String desconto, String acrescimo, boolean ieBruto) throws SQLException {
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
            valor = calcularDesconto(valor, descDoub);
            valor = calcularAcrescimo(valor, acresimoDoub);
            return valor;
        }
    }

    public double calcularDesconto(double totalBruto, double desconto) {
        if (desconto != 0) {
            return (totalBruto - (totalBruto * (desconto / 100)));
        } else {
            return totalBruto;
        }
    }

    public double calcularAcrescimo(double totalBruto, double acrecimo) {
        if (acrecimo != 0) {
            return (totalBruto + (totalBruto * (acrecimo / 100)));
        } else {
            return totalBruto;
        }
    }

    public String carregarAdicionaisPedidos(Pedido pedido) throws SQLException {
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

    public List<Pagamento> atualizarListaFiltros(String dataIni, String dataFim, int idUsuario, String idpagto) throws ParseException, SQLException {
        List<Pagamento> pags = new ArrayList<Pagamento>();
        dataIni = Utilidades.getInstance().formatarDataFiltro(dataIni, true);
        dataFim = Utilidades.getInstance().formatarDataFiltro(dataFim, false);
        int id = 0;
        if (!idpagto.equalsIgnoreCase("")) {
            id = Integer.parseInt(idpagto);
        }
        pags = PagamentoDAO.getInstance().findAllPersonalizado(dataIni, dataFim, idUsuario, id);
        return pags;
    }

    public String salvarPagamento(List<Comanda> listaComandasSelecionadas, String acrescimo, String desconto, String valorTotal) throws SQLException {
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

            p.setDtatulizacao(myDate);
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
    }
}
