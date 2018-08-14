package Banco_Saida;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Produtos_Dao_saida {
    
    public void insert(Produtos_saida pro_s){
        String query = "INSERT INTO produtos (nome, preco) VALUES (?,?)";
        Mysql_Saida.executeQuery(query, pro_s.getnome(),pro_s.getpreco());
    }
    
    
    public ArrayList buscar (){
        return buscas("SELECT * FROM produtos");
    }
    
    private ArrayList buscas (String query){
        ArrayList <Produtos_saida> lista = new ArrayList<>();
        ResultSet rs = null;
        rs = Mysql_Saida.getResultSet(query);
        try {
            while (rs.next()) {
                Produtos_saida a = new Produtos_saida(rs.getInt("id")
                        ,rs.getString("nome"), rs.getDouble("preco"));
                
                System.err.println("id:"+a.getid()
                        + "\nnome:"+a.getnome()
                        + "\npreco:"+a.getpreco());
                
                lista.add(a);
            }
            rs.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
}