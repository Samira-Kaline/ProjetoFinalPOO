
import java.sql.Connection;
import java.sql.Date;
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
public class VendaDAO {
    public void adicionarVenda(Venda v) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("INSERT INTO venda VALUES(?,?,?,?)");
            stmt.setInt(1, v.getId());
            stmt.setDate(2, (Date) v.getData());
            stmt.setInt(3, v.getValor());
            stmt.setInt(4, v.getCliente().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
    
    public List<Venda> listarVenda() {
        List<Venda> listaRetorno = new LinkedList<>();
        ClienteDAO ClienteDao = new ClienteDAO();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM venda");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente c = ClienteDao.getClientePeloId(rs.getInt("id_cliente"));
                Venda v = new Venda(
                    rs.getInt("id_venda"),
                    rs.getInt("valor"),
                    rs.getDate("data"),
                    c);
                listaRetorno.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return listaRetorno;
    }
    
    public Venda getVendaPeloId(int id_venda) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        ClienteDAO ClienteDao = new ClienteDAO();
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM venda WHERE id_venda=?");
            stmt.setInt(1, id_venda);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente c = ClienteDao.getClientePeloId(rs.getInt("id_cliente"));
                Venda v = new Venda(
                    rs.getInt("id_venda"),
                    rs.getInt("valor"),
                    rs.getDate("data"),
                    c);
                
                return v;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return null;
    }
    
    public void updateVenda(Venda v) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE venda SET data=?,valor=?,id_cliente=? WHERE id_venda=?");
            stmt.setDate(1, (Date) v.getData());
            stmt.setInt(2, v.getValor());
            stmt.setInt(3, v.getCliente().getId());
            stmt.setInt(4, v.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }

    public void deletarVenda(int id_venda) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM venda WHERE id_venda=?");
            stmt.setInt(1, id_venda);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    
    }
}
