package Socket;

import Banco_Saida.Produtos_Dao_saida;
import Banco_Saida.Produtos_saida;
import Bando_chegada.Produtos_Dao_chegada;
import Bando_chegada.Produtos_chegada;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
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

public class Cliente {
    //c
    public void socketCliente(String endereco, String ip, int porta){
        try {
            File arquivo = new File(endereco);
            
            
                Socket cliente = new Socket(ip, porta);
                //JOptionPane.showMessageDialog(null, ip+":"+porta);
                System.out.println("\nCliente conectado: " + cliente.getInetAddress().getHostAddress());
                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                
                FileInputStream file = new FileInputStream(endereco);
                byte[] buf = new byte[4096];
                while(true){
                    int len = file.read(buf);
                    if (len == -1)break;
                    saida.write(buf, 0, len);
                } 
                saida.close();            
            
        } catch (Exception e) {
            System.out.println("erro: "+ e.getMessage());
        }
        
    }
    //C    
    public String Gerar_XML(String endereCo){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            Document docXml = db.newDocument();
            
            Element root = docXml.createElement("xml_saida");
            docXml.appendChild(root);
            
            
            
            Produtos_Dao_saida pds = new Produtos_Dao_saida();
            
            ArrayList<Produtos_saida> lista_Pro_saida = pds.buscar();
            
            for (Produtos_saida produtos_saida : lista_Pro_saida) {
            Element pessoa = docXml.createElement("produtos");    
            Attr id = docXml.createAttribute("id");
            id.setValue(String.valueOf( produtos_saida.getid() ));
            
            pessoa.setAttributeNode(id);
            
            root.appendChild(pessoa);
            
            Element nome = docXml.createElement("nome");
            nome.appendChild(docXml.createTextNode(produtos_saida.getnome()));
            
            pessoa.appendChild(nome);
            
            Element idade = docXml.createElement("preco");
            idade.appendChild(docXml.createTextNode( String.valueOf(produtos_saida.getpreco() ) ));
            pessoa.appendChild(idade);
            }
            
            
            TransformerFactory transF = TransformerFactory.newInstance();
            Transformer tran = transF.newTransformer();
            
            DOMSource documentoFont = new DOMSource(docXml);
            
            StreamResult docFinal = new StreamResult(new File(endereCo+"\\arquivo.xml")
            );
            
            tran.transform(documentoFont, docFinal);
            
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "\\arquivo.xml";
    }
    
}