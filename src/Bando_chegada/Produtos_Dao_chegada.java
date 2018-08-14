package Bando_chegada;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Produtos_Dao_chegada {
    
    public void insert(Produtos_chegada pro_c){
        String query = "INSERT INTO produtos (nome, preco) VALUES (?,?)";
        Mysql_chegada.executeQuery(query, pro_c.getNome(),pro_c.getPreco());
    }
    
    public ArrayList buscar (){
        return buscas("SELECT * FROM produtos");
    }
    
    private ArrayList buscas (String query){
        ArrayList <Produtos_chegada> lista = new ArrayList<>();
        ResultSet rs = null;
        rs = Mysql_chegada.getResultSet(query);
        try {
            while (rs.next()) {
                Produtos_chegada a = new Produtos_chegada(rs.getInt("id")
                        ,rs.getString("nome"), rs.getDouble("preco"));
                
                System.err.println("id:"+a.getId()
                        + "\nnome:"+a.getNome()
                        + "\npreco:"+a.getPreco());
                
                lista.add(a);
            }
            rs.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lista;
    }
    
}