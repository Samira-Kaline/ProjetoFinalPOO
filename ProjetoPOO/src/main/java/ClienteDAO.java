
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
public class ClienteDAO {
    public void adicionarCliente(Cliente c) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("INSERT INTO cliente VALUES(?,?,?,?)");
            stmt.setInt(1, c.getId());
            stmt.setString(2, c.getNome());
            stmt.setString(3, c.getRg());
            stmt.setString(4, c.getCpf());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
    
    public List<Cliente> listarClientes() {
        List<Cliente> listaRetorno = new LinkedList<>();

        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM cliente ORDER BY id_cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente(rs.getInt("id_cliente"),
                                        rs.getString("nome"), 
                                        rs.getString("rg"), 
                                        rs.getString("cpf"));
                listaRetorno.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return listaRetorno;
    }
    
    public Cliente getClientePeloId(int id_cliente) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE id_cliente=?");
            stmt.setInt(1, id_cliente);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente c = new Cliente(rs.getInt("id_cliente"),
                                    rs.getString("nome"), 
                                    rs.getString("rg"),
                                    rs.getString("cpf"));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return null;
    }
    
    public void updateCliente(Cliente c) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE cliente SET nome=?, rg=?, cpf=? WHERE id_cliente=?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getRg());
            stmt.setString(3, c.getCpf());
            stmt.setInt(4, c.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }

    public void deletarCliente(int id_cliente) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM cliente WHERE id_cliente=?");
            stmt.setInt(1, id_cliente);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    
    }
}
