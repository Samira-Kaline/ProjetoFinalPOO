
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aluno
 */
public class ItensVendaDAO {
    public void adicionarItensVenda(ItensVenda iv) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("INSERT INTO itensvenda VALUES(?,?,?)");
            stmt.setInt(3, iv.getP().getId());
            stmt.setInt(4, iv.getV().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
    
    public List<ItensVenda> listarItensVenda() {
        List<ItensVenda> listaRetorno = new LinkedList<>();
        ProdutoDAO produtoDao= new ProdutoDAO();
        VendaDAO vendaDao = new VendaDAO();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM itensvenda");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = produtoDao.getProdutoPeloId(rs.getInt("id_produto"));
                Venda v = vendaDao.getVendaPeloId(rs.getInt("id_venda"));
                ItensVenda iv = new ItensVenda(rs.getInt("qtd"), 
                                        p, 
                                        v);
                listaRetorno.add(iv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return listaRetorno;
    }
    
    public ItensVenda getItensVendaPeloIdVenda(int id_venda) {
        ProdutoDAO produtoDao= new ProdutoDAO();
        VendaDAO vendaDao = new VendaDAO();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        SessaoDao sessaoDao = new SessaoDao();
        ClienteDAO clienteDao = new ClienteDAO();
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM bilhete WHERE id_venda=?");
            stmt.setInt(1, id_venda);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Produto p = produtoDao.getProdutoPeloId(rs.getInt("id_produto"));
                Venda v = vendaDao.getVendaPeloId(rs.getInt("id_venda"));
                ItensVenda iv = new ItensVenda(rs.getInt("qtd"), 
                                        p, 
                                        v);                
                return iv;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return null;
    }
    
    public void updateItensVenda(ItensVenda iv) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE bilhete SET qtd=? WHERE id_venda=?");
            stmt.setInt(1, iv.getQtd());
            stmt.setInt(2, iv.getP().getId());
            stmt.setInt(3, iv.getV().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }

    public void deletarItensVenda(int id_itensvenda) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM itensvenda WHERE id_itensvenda=?");
            stmt.setInt(1, id_itensvenda);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    
    }
}
