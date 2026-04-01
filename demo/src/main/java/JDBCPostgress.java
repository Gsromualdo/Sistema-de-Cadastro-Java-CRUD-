import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

public class JDBCPostgress {
    public static void main(String[] args) {
        try {
            // preparedStatement; // realiza todos os comandos de modificacao e alteração de
            // dados
            // ResultSet

            String url = "jdbc:postgresql://localhost:5432/rocket_db";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "1234");
            // props.setProperty("ssl", "true");
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("Connected to the database!");
            String sql = "INSERT INTO public.tab_cadastro(nome, idade)VALUES(?,?)";
            String nome = "Izabelle";
            Integer idade = 17;
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            preparedStatement.setInt(2, idade);
            preparedStatement.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
