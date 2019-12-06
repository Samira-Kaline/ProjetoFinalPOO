
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class BilheteDAO {
    public void adicionarBilhete(Bilhete b) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("INSERT INTO bilhete VALUES(?,?,?,?)");
            stmt.setInt(1, b.getId());
            stmt.setInt(2, b.getValor());
            stmt.setInt(3, b.getSessao().getId());
            stmt.setInt(4, b.getCliente().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
    
    public List<Bilhete> listarBilhete() {
        List<Bilhete> listaRetorno = new LinkedList<>();
        SessaoDao sessaoDao = new SessaoDao();
        ClienteDAO clienteDao = new ClienteDAO();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM bilhete ");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Sessao s = sessaoDao.getSessaoPeloId(rs.getInt("id_sessao"));
                Cliente c = clienteDao.getClientePeloId(rs.getInt("id_cliente"));
                Bilhete b = new Bilhete(rs.getInt("id_bilhete"),
                                        rs.getInt("valor"), 
                                        s, 
                                        c);
                listaRetorno.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return listaRetorno;
    }
    
    public Bilhete getBilhetePeloId(int id_bilhete) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        SessaoDao sessaoDao = new SessaoDao();
        ClienteDAO clienteDao = new ClienteDAO();
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM bilhete WHERE id_bilhete=?");
            stmt.setInt(1, id_bilhete);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Sessao s = sessaoDao.getSessaoPeloId(rs.getInt("id_sessao"));
                Cliente c = clienteDao.getClientePeloId(rs.getInt("id_cliente"));
                
                Bilhete b = new Bilhete(rs.getInt("id_bilhete"),
                                        rs.getInt("valor"), 
                                        s, 
                                        c);                
                return b;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return null;
    }
    
    public void updateBilhete(Bilhete b) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE bilhete SET valor=?, id_sessao=?, id_cliente=? WHERE id_bilhete=?");
            stmt.setInt(1, b.getValor());
            stmt.setInt(2, b.getSessao().getId());
            stmt.setInt(3, b.getCliente().getId());
            stmt.setInt(4, b.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }

    public void deletarBilhete(int id_bilhete) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM bilhete WHERE id_bilhete=?");
            stmt.setInt(1, id_bilhete);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    
    }
}
