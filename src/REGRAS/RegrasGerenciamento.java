package REGRAS;

import DAO.AdicionaisDAO;
import DAO.ComandaDAO;
import DAO.ProdutoDAO;
import DAO.UsuarioDAO;
import CLASSE.Adicional;
import CLASSE.Comanda;
import CLASSE.Produto;
import CLASSE.Usuario;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    private Calendar cal = new GregorianCalendar();
    private static RegrasGerenciamento instance;

    public static RegrasGerenciamento getInstance() {
        if (instance == null) {
            instance = new RegrasGerenciamento();
        }

        return instance;
    }

    public String salvarAdicional(String nmAdicional, Float vlAdicional) {
        Adicional a = new Adicional();
        a.setIeativo((short) 1);
        if (!nmAdicional.equalsIgnoreCase("")) {
            a.setNmadicional(nmAdicional);
        }

        a.setVladicional(vlAdicional);
        a.setDtatualizacao(cal.getTime());

        String msgError = validaInformacoesAdicionais(a);

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

            String msgError = validaInformacoesAdicionais(a);
            if (!msgError.equalsIgnoreCase("")) {
                return msgError;
            } else {
                a.setDtatualizacao(cal.getTime());
                AdicionaisDAO.getInstance().merge(a);
                return "";
            }
        }
        return "";
    }

    private String validaInformacoesAdicionais(Adicional classe) {
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
        }
        c.setDtatualizacao(cal.getTime());
        ComandaDAO.getInstance().persist(c);

        return "";
    }

    public String alterarComanda(int id, String observacao) {
        Comanda c = ComandaDAO.getInstance().getById(id);
        boolean isAlterou = false;

        if (!c.getDsobservacao().equalsIgnoreCase(trim(observacao))) {
            c.setDsobservacao(observacao);
            isAlterou = true;
        }

        if (isAlterou) {
            c.setDtatualizacao(cal.getTime());
            ComandaDAO.getInstance().merge(c);

        }
        return "";
    }

    public String salvarUsuario(String usuario, String nome, String senha, String senhaConfirmar, int permissao) throws InvalidKeySpecException, SQLException, NoSuchAlgorithmException, NoSuchProviderException {
        List<Usuario> usuarios = UsuarioDAO.getInstance().findByUser(usuario);
        String msgError = "";
        if (usuarios.size() != 0) {
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

                Passwords pass = new Passwords();
                byte[] salt = pass.getNextSalt();

                String securePassword = pass.getSecurePassword(senha, salt);
                u.setSalt(salt);
                u.setDssenha(securePassword);
            }

            u.setIetipopermissao(permissao);

            msgError += validaInformacoesUsuario(u);

            if (!msgError.equalsIgnoreCase("")) {
                return msgError;
            } else {
                UsuarioDAO.getInstance().persist(u);
                return "";
            }

        }
    }

    private String validaInformacoesUsuario(Usuario classe) {
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
        List<Usuario> usuarios = UsuarioDAO.getInstance().findByUser(usuario);
        boolean testanome = true;
        for (Usuario u : usuarios) {
            if (u.getDsusuario().equalsIgnoreCase(usuario)) {
                testanome = false;
            }
        }
        if (usuarios.size() != 0 && testanome) {
            msgError = "Este usuário já está sendo utilizado, escolha outro!";
        }
        Usuario u = UsuarioDAO.getInstance().getById(id);
        boolean isAlterou = false;

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

        if (!senha.equalsIgnoreCase("")) {
            if (!senhaConfirmar.equalsIgnoreCase(senha)) {
                msgError += " Senhas digitas estão diferentes, confira.";
            }

            Passwords pass = new Passwords();
            String securePassword = pass.getSecurePassword(senha, u.getSalt());

            if (!securePassword.equalsIgnoreCase(u.getDssenha())) {
                u.setDssenha(securePassword);
                isAlterou = true;
            }
        } else {
            msgError += " Senha é um campo obrigatório, verifique.";
        }

        if (u.getIetipopermissao() != permissao) {
            u.setIetipopermissao(permissao);
            isAlterou = true;
        }

        if (isAlterou) {
            msgError += validaInformacoesUsuario(u);

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
        p.setDtatualizacao(cal.getTime());

        String retorno = validaInformacoesProduto(p);

        if ("".equalsIgnoreCase(retorno)) {
            ProdutoDAO.getInstance().persist(p);
            return "";
        } else {
            return retorno;
        }
    }

    private String validaInformacoesProduto(Produto classe) {
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

            String retorno = validaInformacoesProduto(p);

            if (retorno.equalsIgnoreCase("")) {
                p.setDtatualizacao(cal.getTime());
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

    public void testaPrimeiroLogin() throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException {
        List<Usuario> usuarios = UsuarioDAO.getInstance().findAll();

        if (usuarios.size() == 0) {

            Usuario usuario = new Usuario();

            usuario.setDsusuario("admin");
            usuario.setIeativo((short) 1);
            usuario.setNmusuario("admin");
            Passwords pass = new Passwords();
            String senha = "admin";

            byte[] salt = pass.getNextSalt();
            String securePassword = pass.getSecurePassword(senha, salt);
            usuario.setSalt(salt);
            usuario.setDssenha(securePassword);
            UsuarioDAO.getInstance().persist(usuario);
        }
    }

    public int verificaLogin(String usuario, String senha) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException {
        int controleLogin = 0; 
        //0 - Login inválido
        //1 - Primeiro Login
        //2 - Login Ok
        
        List<Usuario> usua = UsuarioDAO.getInstance().findByUser(usuario);

        if (usua.size() == 0) {
            testaPrimeiroLogin();
           controleLogin = 1;
        }

        for (Usuario usuarioEncontrado : usua) {
            Passwords pass = new Passwords();
            String securePassword = pass.getSecurePassword(senha, usuarioEncontrado.getSalt());
            if (!securePassword.equalsIgnoreCase(usuarioEncontrado.getDssenha())) {
                UsuarioDAO.getInstance().setUsuarioLogado(usuarioEncontrado);
                controleLogin = 2;
            }
        }
        return controleLogin;
    }

}
