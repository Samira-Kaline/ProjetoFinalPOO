
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class SessaoDao {
    public void adicionarSessao(Sessao s) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("INSERT INTO sessao VALUES(?,?,?,?)");
            stmt.setInt(1, s.getId());
            stmt.setInt(2, s.getSala());
            stmt.setDate(3, (Date) s.getHorario());
            stmt.setInt(4, s.getFilme().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
    
    public List<Sessao> listarSessao() {
        List<Sessao> listaRetorno = new LinkedList<>();
        FilmeDAO filmeDao = new FilmeDAO();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM sessao");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Filme f = filmeDao.getFilmePeloId(rs.getInt("id_filme"));
                Sessao s = new Sessao(f,
                    rs.getDate("horario"),
                    rs.getInt("sala"),
                    rs.getInt("id_sessao"));
                listaRetorno.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return listaRetorno;
    }
    
    public Sessao getSessaoPeloId(int id_sessao) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        FilmeDAO filmeDao = new FilmeDAO();
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM sessao WHERE id_sessao=?");
            stmt.setInt(1, id_sessao);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Filme f = filmeDao.getFilmePeloId(rs.getInt("id_filme"));
                Sessao s = new Sessao(f,
                    rs.getDate("horario"),
                    rs.getInt("sala"),
                    rs.getInt("id_sessao"));
                return s;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return null;
    }
    
    public void updateSessao(Sessao s) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE Sessao SET horario=?, sala=?, id_filme=? WHERE id_sessao=?");
            stmt.setDate(1, (Date) s.getHorario());
            stmt.setInt(2, s.getSala());
            stmt.setInt(3, s.getFilme().getId());
            stmt.setInt(4, s.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }

    public void deletarFilme(int id_sessao) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM sessao WHERE id_sessao=?");
            stmt.setInt(1, id_sessao);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    
    }
}
