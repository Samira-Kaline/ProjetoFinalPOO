
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
 * @author alunoFoda
 */
public class FilmeDAO {
    
    public void adicionarFilme(Filme f) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("INSERT INTO filme VALUES(?,?,?,?)");
            stmt.setInt(1, f.getId());
            stmt.setString(2, f.getNome());
            stmt.setString(3, f.getGenero());
            stmt.setString(4, f.getClassificacao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
    
    public List<Filme> listarFilmes() {
        List<Filme> listaRetorno = new LinkedList<>();

        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM filme ORDER BY id_filme");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Filme f = new Filme(rs.getInt("id_filme"),
                                        rs.getString("nome"), 
                                        rs.getString("genero"), 
                                        rs.getString("classificacao"));
                listaRetorno.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }

        return listaRetorno;
    }
    
    public Filme getFilmePeloId(int id_filme) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("SELECT * FROM filme WHERE id_filme=?");
            stmt.setInt(1, id_filme);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Filme f = new Filme(rs.getInt("id_filme"),
                                    rs.getString("nome"), 
                                    rs.getString("genero"),
                                    rs.getString("classificacao"));
                return f;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao);
        }
        return null;
    }
    
    public void updateFilme(Filme f) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE Filme SET nome=?, genero=?, classificacao=? WHERE id_filme=?");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getGenero());
            stmt.setString(3, f.getClassificacao());
            stmt.setInt(4, f.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }

    public void deletarFilme(int id_filme) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM filme WHERE id_filme=?");
            stmt.setInt(1, id_filme);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    
}
