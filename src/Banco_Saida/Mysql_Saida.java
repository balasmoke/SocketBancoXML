package Banco_Saida;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mysql_Saida {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql:///xml_saida";
    private static final String USER = "root";
    private static final String PSWD = "";
    private static Connection conexao = null;

    public static Connection getConnection() {
        if (conexao == null) {
            try {
                Class.forName(DRIVER).newInstance();
                conexao = DriverManager.getConnection(URL, USER, PSWD);
                System.out.println("Conexao Aberta");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
                System.out.println("Erro ao Conectar ao Banco -> " + e.getMessage());
            }
        }
        return conexao;
    }

    public static ResultSet getResultSet(String query, Object... p) {
        PreparedStatement pst;
        ResultSet rs = null;

        try {
            pst = getConnection().prepareStatement(query);
            for (int i = 0; i < p.length; i++) {
                pst.setObject(i + 1, p[i]);
            }
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Erro ao execultar o ResultSet -> " + ex.getMessage());
        }
        return rs;
    }

    private static int getLastId(PreparedStatement p) throws SQLException {
        ResultSet rs = null;
        try {
            rs = p.getGeneratedKeys();
            rs.next();
        } catch (SQLException e) {
            System.out.println("Erro ao adquirir o lastID " + e.getMessage());
        }
        return rs.getInt(1);
    }

    public static int executeQuery(String query, Object... p) {
        int update = 0;
        PreparedStatement pst = null;
        try {
            pst = getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < p.length; i++) {
                pst.setObject(i + 1, p[i]);
            }
            pst.execute();

            update = getLastId(pst);
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro ao execultar a Query -> " + e.getMessage());
        }
        return update;
    }

    public static void terminar() {
        try {
            (Mysql_Saida.getConnection()).close();
        } catch (SQLException ex) {
            System.out.println("Erro ao finalizar o Banco -> " + ex.getMessage());
        }
        System.out.println("Conexao Fechada");

    }
    
}