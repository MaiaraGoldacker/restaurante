package Regra;

import Dao.AdicionaisDAO;
import Dao.ComandaDAO;
import Dao.ProdutoDAO;
import Dao.UsuarioDAO;
import Classe.Adicional;
import Classe.AdicionalPedido;
import Classe.Comanda;
import Classe.Produto;
import Classe.Usuario;
import Dao.AdicionalPedidoDAO;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static jdk.nashorn.internal.objects.NativeString.trim;

/**
 *
 * @author maiara
 */
public class RegrasGerenciamento {

    private Instant agora = Instant.now();
    private Date myDate = Date.from(agora);
    private static RegrasGerenciamento instance;

    public static RegrasGerenciamento getInstance() {
        if (instance == null) {
            instance = new RegrasGerenciamento();
        }

        return instance;
    }

    public void salvarAdicionalPedido(int idAdicional, int idPedido, int idProduto) {
        AdicionalPedido adPed = new AdicionalPedido();
        adPed.setAdicionalId(idAdicional);
        adPed.setPedidoId(idPedido);
        adPed.setProdutoId(idProduto);

        adPed.setDtatualizacao(myDate);

        AdicionalPedidoDAO.getInstance().persist(adPed);
    }

    public String salvarAdicional(String nmAdicional, Float vlAdicional) {
        Adicional a = new Adicional();
        a.setIeativo((short) 1);
        if (!nmAdicional.equalsIgnoreCase("")) {
            a.setNmadicional(nmAdicional);
        }

        a.setVladicional(vlAdicional);
        a.setDtatualizacao(myDate);

        String msgError = validarInformacoesAdicionais(a);

        if (!msgError.equalsIgnoreCase("")) {
            return msgError;
        } else {
            AdicionaisDAO.getInstance().persist(a);
            return "";
        }
    }

    public String alterarAdicional(int id, String nmAdicional, Float vlAdicional) {
        Adicional a = AdicionaisDAO.getInstance().getById(id);
        boolean isAlterou = false;

        if (!nmAdicional.equalsIgnoreCase("")) {
            if (!a.getNmadicional().equalsIgnoreCase(nmAdicional)) {
                a.setNmadicional(nmAdicional);
                isAlterou = true;
            }
        }
        if (a.getVladicional() != vlAdicional) {
            a.setVladicional(vlAdicional);
            isAlterou = true;
        }

        if (isAlterou) {

            String msgError = validarInformacoesAdicionais(a);
            if (!msgError.equalsIgnoreCase("")) {
                return msgError;
            } else {
                a.setDtatualizacao(myDate);
                AdicionaisDAO.getInstance().merge(a);
                return "";
            }
        }
        return "";
    }

    private String validarInformacoesAdicionais(Adicional classe) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Adicional>> constraintViolations = validator.validate(classe);

        String msgError = "";
        for (ConstraintViolation error : constraintViolations) {
            msgError = "\n" + error.getMessage();
        }

        return msgError;
    }

    public String salvarComanda(String observacao) {
        Comanda c = new Comanda();
        c.setIeativo((short) 1);
        if (!trim(observacao).equalsIgnoreCase("")) {
            c.setDsobservacao(trim(observacao));
        } else {
            c.setDsobservacao(" ");
        }
        c.setDtatualizacao(myDate);
        ComandaDAO.getInstance().persist(c);

        return "";
    }

    public String alterarComanda(int id, String observacao) {
        Comanda c = ComandaDAO.getInstance().getById(id);
        boolean isAlterou = false;

        if (!c.getDsobservacao().equalsIgnoreCase(observacao)) {
            c.setDsobservacao(observacao);
            isAlterou = true;
        }

        if (isAlterou) {
            c.setDtatualizacao(myDate);
            ComandaDAO.getInstance().merge(c);

        }
        return "";
    }

    public String salvarUsuario(String usuario, String nome, String senha, String senhaConfirmar, int permissao) throws InvalidKeySpecException, SQLException, NoSuchAlgorithmException, NoSuchProviderException {
        Usuario usua = new Usuario();
        boolean testaUsuario = false;
        String msgError = "";

        if (!usuario.equalsIgnoreCase("")) {
            usua = UsuarioDAO.getInstance().findByUser(usuario);
            testaUsuario = true;

        }
        if (usua.getId() != null && testaUsuario) {
            return "Este usuário já está sendo utilizado, escolha outro!";
        } else {
            Usuario u = new Usuario();
            u.setIeativo((short) 1);

            if (!usuario.equalsIgnoreCase("")) {
                u.setDsusuario(usuario);
            }
            if (!nome.equalsIgnoreCase("")) {
                u.setNmusuario(nome);
            }

            if (!senha.equalsIgnoreCase("")) {
                if (!senhaConfirmar.equalsIgnoreCase(senha)) {
                    msgError = "Senhas digitas estão diferentes, confira.";
                }

                byte[] salt = Passwords.getInstance().getNextSalt();

                String securePassword = Passwords.getInstance().getSecurePassword(senha, salt);
                u.setSalt(salt);
                u.setDssenha(securePassword);
            }

            u.setIetipopermissao(permissao);

            msgError += validarInformacoesUsuario(u);

            if (!msgError.equalsIgnoreCase("")) {
                return msgError;
            } else {
                UsuarioDAO.getInstance().persist(u);
                return "";
            }
        }
    }

    private String validarInformacoesUsuario(Usuario classe) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(classe);

        String msgError = "";
        for (ConstraintViolation error : constraintViolations) {
            msgError = "\n" + error.getMessage();
        }

        return msgError;
    }

    public String alterarUsuario(int id, String usuario, String nome, String senha, String senhaConfirmar, int permissao) throws InvalidKeySpecException, SQLException, NoSuchAlgorithmException, NoSuchProviderException {
        String msgError = "";
        Usuario usu = UsuarioDAO.getInstance().findByUser(usuario);
        Usuario u = UsuarioDAO.getInstance().getById(id);
        boolean isAlterou = false;

        if (usu.getId() != null) {
            if (usu.getDsusuario().equalsIgnoreCase(usuario) && usu.getId() != id) {
                msgError = "Este usuário já está sendo utilizado, escolha outro!";
                isAlterou = true;
            }
        }

        if (!senhaConfirmar.equalsIgnoreCase(senha)) {
            msgError += " Senhas digitas estão diferentes, confira.";
            isAlterou = true;
        }
        if (msgError.equalsIgnoreCase("")) {

            if (!usuario.equalsIgnoreCase("")) {
                if (!u.getDsusuario().equalsIgnoreCase(usuario)) {
                    u.setDsusuario(usuario);
                    isAlterou = true;
                }
            }

            if (!usuario.equalsIgnoreCase("")) {
                if (!u.getNmusuario().equalsIgnoreCase(nome)) {
                    u.setNmusuario(nome);
                    isAlterou = true;
                }
            }

            String securePassword = Passwords.getInstance().getSecurePassword(senha, u.getSalt());

            if (!securePassword.equalsIgnoreCase(u.getDssenha())) {
                u.setDssenha(securePassword);
                isAlterou = true;
            }

            if (u.getIetipopermissao() != permissao) {
                u.setIetipopermissao(permissao);
                isAlterou = true;
            }
        }

        if (isAlterou) {
            msgError += validarInformacoesUsuario(u);

            if (msgError.equalsIgnoreCase("")) {
                UsuarioDAO.getInstance().merge(u);
                return "";
            } else {
                return msgError;
            }
        }
        return "";
    }

    public String salvarProduto(String observacao, String nome, Float valor, int classificacao) {
        Produto p = new Produto();
        p.setIeativo((short) 1);
        p.setDsobservacaoproduto(observacao);
        p.setNmproduto(nome);
        p.setClassificacao(classificacao);
        p.setVlproduto(valor);
        p.setDtatualizacao(myDate);

        String retorno = validarInformacoesProduto(p);

        if ("".equalsIgnoreCase(retorno)) {
            ProdutoDAO.getInstance().persist(p);
            return "";
        } else {
            return retorno;
        }
    }

    private String validarInformacoesProduto(Produto classe) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(classe);

        String msgError = "";
        for (ConstraintViolation error : constraintViolations) {
            msgError = "\n" + error.getMessage();
        }

        return msgError;
    }

    public String alterarProduto(int id, String observacao, String nome, Float valor, int classificacao) {
        Produto p = ProdutoDAO.getInstance().getById(id);
        boolean isAlterou = false;

        if (!p.getDsobservacaoproduto().equalsIgnoreCase(observacao)) {
            p.setDsobservacaoproduto(observacao);
            isAlterou = true;
        }

        if (!p.getNmproduto().equalsIgnoreCase(nome)) {
            p.setNmproduto(nome);
            isAlterou = true;
        }

        if (p.getClassificacao() != classificacao) {
            p.setClassificacao(classificacao);
            isAlterou = true;
        }

        if (p.getVlproduto() != valor) {
            p.setVlproduto(valor);
            isAlterou = true;
        }

        if (isAlterou) {

            String retorno = validarInformacoesProduto(p);

            if (retorno.equalsIgnoreCase("")) {
                p.setDtatualizacao(myDate);
                ProdutoDAO.getInstance().merge(p);
                return "";
            } else {
                return retorno;
            }
        }
        return "";
    }

    public void excluirAdicional(int id) {
        AdicionaisDAO.getInstance().removeById(id);
    }

    public void excluirComanda(int id) {
        ComandaDAO.getInstance().removeById(id);
    }

    public void excluirProduto(int id) {
        ProdutoDAO.getInstance().removeById(id);
    }

    public void excluirUsuario(int id) {
        UsuarioDAO.getInstance().removeById(id);
    }

    public boolean testarPrimeiroLogin() throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException {
        List<Usuario> usuarios = UsuarioDAO.getInstance().findAll();

        if (usuarios.size() == 0) {

            Usuario usuario = new Usuario();

            usuario.setDsusuario("admin");
            usuario.setIeativo((short) 1);
            usuario.setNmusuario("admin");

            String senha = "admin";

            byte[] salt = Passwords.getInstance().getNextSalt();
            String securePassword = Passwords.getInstance().getSecurePassword(senha, salt);
            usuario.setSalt(salt);
            usuario.setDssenha(securePassword);
            UsuarioDAO.getInstance().persist(usuario);
            return true;
        }
        return false;
    }

    public int verificarLogin(String usuario, String senha) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException {
        //0 - Login inválido        
        //1 - Login Ok
        if (!trim(usuario).equalsIgnoreCase("") || !trim(senha).equalsIgnoreCase("")) {
            Usuario usua = UsuarioDAO.getInstance().findByUser(usuario);

            if (usua.getId() == null) {
                if (testarPrimeiroLogin()) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                
            
            /*if (!securePassword.equalsIgnoreCase(u.getDssenha())) {
                u.setDssenha(securePassword);
                isAlterou = true;
            }*/
                Usuario u = UsuarioDAO.getInstance().getById(usua.getId());
                String securePassword = Passwords.getInstance().getSecurePassword(senha, u.getSalt());

                if (securePassword.equalsIgnoreCase(usua.getDssenha())) {
                    UsuarioDAO.getInstance().setUsuarioLogado(usua);
                    return 1;
                }
            }
        }
        return 0;
    }
}
