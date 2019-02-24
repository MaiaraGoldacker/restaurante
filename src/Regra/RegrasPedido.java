/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Regra;

import Classe.Adicional;
import Classe.AdicionalPedido;
import Classe.Pedido;
import Classe.Produto;
import Dao.AdicionaisDAO;
import Dao.AdicionalPedidoDAO;
import Dao.PedidoDAO;
import Dao.ProdutoDAO;
import Dao.UsuarioDAO;
import java.awt.Color;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
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
    private Instant agora = Instant.now();
    private Date myDate = Date.from(agora);

    public static RegrasPedido getInstance() {
        if (instance == null) {
            instance = new RegrasPedido();
        }

        return instance;
    }

    public void calcularDiferencaHoras(Date horaPedido, JLabel label) {
        Duration duracao = Duration.between(horaPedido.toInstant(), myDate.toInstant());

        if (duracao.toHours() >= 1) {
            label.setForeground(Color.RED);
            label.setText("Pedido há " + duracao.toHours() + " hrs");
        } else {
            label.setText("Pedido há " + duracao.toHours() + " hrs e " + duracao.toMinutes() + " min");
        }
    }

    public void alterarStatusPedidos(List<Pedido> lista) {
        for (Pedido ped : lista) {
            ped.setStpedido(2);
            PedidoDAO.getInstance().merge(ped);
        }
    }

    public String carregarPedidosRealizados(Pedido ped) throws SQLException {
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

    public void salvarPedidoEfetivamente(int comanda) throws SQLException {
        List<Pedido> pedidos = PedidoDAO.getInstance().findAtualizarPedido(comanda);

        for (Pedido p : pedidos) {
            p.setDtatualizacao(myDate);
            p.setStpedido(1);
            PedidoDAO.getInstance().merge(p);
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

        String retorno = validarInformacoesPedido(ped);
        if (retorno.equalsIgnoreCase("")) {
            PedidoDAO.getInstance().persist(ped);
            return "";
        } else {
            return retorno;
        }
    }

    private String validarInformacoesPedido(Pedido classe) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Pedido>> constraintViolations = validator.validate(classe);

        String msgError = "";
        for (ConstraintViolation error : constraintViolations) {
            msgError = "\n" + error.getMessage();
        }

        return msgError;
    }

    public int verificarSalvarPedido(int comanda) throws SQLException {
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
