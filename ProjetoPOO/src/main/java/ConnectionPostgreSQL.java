import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionPostgreSQL {
	private String url;
	private String user;
	private String password;
	
	public ConnectionPostgreSQL(){
		this.url = "jdbc:postgresql://localhost:5432/postgres";
		this.user = "postgres";
		this.password = "12345"; 
	}
	
	public Connection getConection() throws SQLException {
		Connection con = DriverManager.getConnection(url, user, password);
		return con;
	}
        
        public void close(ResultSet rs, PreparedStatement stmt, Connection conexao){
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	
}
