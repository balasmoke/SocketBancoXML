

package Banco_Saida;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlEnum;

@XmlRootElement(name = "produtos")
public class Produtos_saida {
    private int id;
    private String nome;
    private double preco;

    public Produtos_saida(int id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Produtos_saida(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public int getid() {
        return id;
    }

    public String getnome() {
        return nome;
    }

    public double getpreco() {
        return preco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    
}
