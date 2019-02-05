/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REGRAS;

import CLASSE.Adicional;
import CLASSE.AdicionalPedido;
import CLASSE.Pedido;
import CLASSE.Produto;
import DAO.AdicionaisDAO;
import DAO.AdicionalPedidoDAO;
import DAO.PedidoDAO;
import DAO.ProdutoDAO;
import DAO.UsuarioDAO;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author maiara
 */
public class RegrasPedido {

    private static RegrasPedido instance;
    private Calendar cal = new GregorianCalendar();

    public static RegrasPedido getInstance() {
        if (instance == null) {
            instance = new RegrasPedido();
        }

        return instance;
    }

    public void calcularDiferencaHoras(Date horaPedido, JLabel label) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.set(Calendar.HOUR, horaPedido.getHours());
        c1.set(Calendar.MINUTE, horaPedido.getMinutes());

        c2.set(Calendar.HOUR, c2.getTime().getHours());
        c2.set(Calendar.MINUTE, c2.getTime().getMinutes());

        //diferenca em minutos
        long diffMinutos = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (60 * 1000);

        //diferenca em horas e minutos
        long diff = c2.getTimeInMillis() - c1.getTimeInMillis();
        long hours = (60 * 60 * 1000);
        long diffHoras = diff / hours;
        long diffHorasMinutos = (diff % hours) / (60 * 1000);

        if (diffHoras < 0) {
            label.setText("Pedido expirado");
            label.setForeground(Color.RED);
        } else {
            label.setText("Pedido há " + diffHoras + " hrs e " + diffHorasMinutos + " min");

            if (diffHoras >= 1) {
                label.setForeground(Color.RED);
            } else {
                label.setForeground(Color.green);
            }
        }

    }

    public void alteraStatusPedidos(List<Pedido> lista) {
        for (Pedido ped : lista) {
            ped.setStpedido(2);
            PedidoDAO.getInstance().merge(ped);
        }
    }

    public String carregaPedidosRealizados(Pedido ped) throws SQLException {
        String desc = "";
        String adic = "";

        Produto prod = ProdutoDAO.getInstance().getById(ped.getProdutoId());

        List<AdicionalPedido> adicionalPedido = AdicionalPedidoDAO.getInstance().findAdicionaisDeProdutos(ped.getId(), ped.getProdutoId());

        for (AdicionalPedido adPed : adicionalPedido) {
            Adicional ad = AdicionaisDAO.getInstance().getById(adPed.getAdicionalId());
            if (adic.equalsIgnoreCase("")) {
                adic += ad.getNmadicional();
            } else {
                adic += ", " + ad.getNmadicional();
            }
        }

        desc += "Produto: " + prod.getNmproduto();

        if (!adic.equalsIgnoreCase("")) {
            if (desc.equalsIgnoreCase("")) {
                desc = adic;
            } else {
                desc += ", com os adicionais de " + adic;
            }
        }

        if (!ped.getObspedido().equalsIgnoreCase("")) {
            desc += "\n" + "Observação: " + ped.getObspedido();
        }

        return desc;
    }

    public void salvarPedidoEfetivamente(int comanda) {
        try {
            List<Pedido> pedidos = PedidoDAO.getInstance().findAtualizarPedido(comanda);

            for (Pedido p : pedidos) {
                p.setDtatualizacao(cal.getTime());
                p.setStpedido(1);
                PedidoDAO.getInstance().merge(p);
            }
        } catch (Exception e) {

        }
    }

    public String salvarPedido(String comanda, Produto prod, String obs) {
        Pedido ped = new Pedido();
        if (!comanda.equalsIgnoreCase("")) {
            ped.setComandaId(Integer.parseInt(comanda));
        }
        ped.setProdutoId(prod.getId());
        ped.setUsuarioId(UsuarioDAO.getInstance().getUsuarioLogado().getId());
        ped.setStpedido(0);
        ped.setObspedido(obs);

        String retorno = validaInformacoesPedido(ped);
        if (retorno.equalsIgnoreCase("")) {
            PedidoDAO.getInstance().persist(ped);
            return "";
        } else {
            return retorno;
        }
    }

    private String validaInformacoesPedido(Pedido classe) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Pedido>> constraintViolations = validator.validate(classe);

        String msgError = "";
        for (ConstraintViolation error : constraintViolations) {
            msgError = "\n" + error.getMessage();
        }

        return msgError;
    }

    public int verificaSalvarPedido(int comanda) throws SQLException {
        List<Pedido> pedidos = PedidoDAO.getInstance().findAllAtender(0, comanda);

        if (pedidos.size() != 0) {
            Object[] botoes = {"Sim", "Não"};
            int resposta = JOptionPane.showOptionDialog(null,
                    "Deseja sair sem salvar o pedido?",
                    "Confirmação", // o título da janela
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    botoes, botoes[0]);
            return resposta;

        } else {
            return 0;
        }

    }
}
