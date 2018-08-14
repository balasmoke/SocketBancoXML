package Socket;

import Banco_Saida.Produtos_Dao_saida;
import Banco_Saida.Produtos_saida;
import Bando_chegada.Produtos_Dao_chegada;
import Bando_chegada.Produtos_chegada;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Servidor {
    //C:\\Users\\gmsil\\Desktop\\htmls-Sandro\\produtos.xml
    public static void main(String[] args) {
        try {
            
            while (true) {                
            ServerSocket servidor = new ServerSocket(8888);//clibera a porta para o socket
            System.out.println("Servidor ouvindo a porta 8080");
            
            Socket cliente = servidor.accept();
            
            
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            FileOutputStream file = new FileOutputStream("C:\\Users\\gmsil\\Desktop\\loal\\transferido.xml");
            byte[] buf = new byte[4096];
                while (true) {                    
                    int len = entrada.read(buf);
                    if (len == -1) {
                        break;
                    }
                    file.write(buf, 0, len);
                }
                
            /*
            File arquivo = (File) entrada.readObject();
            arquivo.renameTo(new File(""));*/
            new Frame_Server().setVisible(true);

            entrada.close();
            cliente.close();
            System.out.println("Arquivo recebido com sucesso" + 
                               "\nConexão com o servidor encerrada!");
            }
		} catch (IOException e) {
		//} catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public ArrayList<Produtos_chegada> Ler_XML(String enderoCo){
        
        ArrayList<Produtos_chegada> lista = new ArrayList<>();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            Document doc = db.parse(enderoCo);
            int id=0;
            NodeList listaDeProdutos = doc.getElementsByTagName("produtos");
            for (int i = 0; i < listaDeProdutos.getLength() ; i++) {
                String nome = "";
                double preco = 0;
                
                Node noProduto = listaDeProdutos.item(i);
                
                if (noProduto.getNodeType() == Node.ELEMENT_NODE) {
                    
                    Element elementoProduto = (Element) noProduto;
                    
                    id = Integer.parseInt(elementoProduto.getAttribute("id"));
                    
                    System.out.println("ID = " + id);
                    
                    NodeList listaDeNosFilhos = elementoProduto.getChildNodes();
                    
                    for (int j = 0; j < listaDeNosFilhos.getLength(); j++) {
                        
                        Node noFilho = listaDeNosFilhos.item(j);
                        
                        if (noFilho.getNodeType() == Node.ELEMENT_NODE) {
                            
                            Element elementoFilho = (Element) noFilho;
                            
                            switch(elementoFilho.getTagName()){
                                
                                case "nome":
                                    System.out.println("Nome: "+ elementoFilho.getTextContent());
                                    nome = elementoFilho.getTextContent();
                                    break;
                                case "preco":
                                    System.out.println("Preco: "+ elementoFilho.getTextContent());
                                    preco = Double.parseDouble(elementoFilho.getTextContent());
                                    break;
                            }
                            
                        }
                        
                    }
                }
                Produtos_chegada produtos = new Produtos_chegada(id,nome, preco);
                lista.add(produtos);
            }
            
            
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
