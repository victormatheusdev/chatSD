
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/** 
 * Thead que fica ouvindo em uma porta especifica
 * @author Rodrigo Campiolo 
 */


public class ClientListener extends Thread {
    ChatGUI chat;                    // referencia para a interface grafica
    private String port;
    private MulticastSocket mcSocket;
    /* Construtor */
    public ClientListener (ChatGUI c) {
	    chat = c;  
	
    } //construtor 
    public ClientListener (ChatGUI c, String port, MulticastSocket mcSocket) {
        chat = c;  
        this.port = port;
        this.mcSocket = mcSocket;
    } //construtor 
    /* execucao da thead */
    public void run() {
        try {
        while (true) {
                byte[] buffer = new byte[1000];
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                mcSocket.receive(messageIn);
                Mensagem msg = new Mensagem(messageIn.getData());

                if(msg.isJoinACK()){
                    this.chat.addNickname(msg.getSource(), messageIn.getAddress().getHostAddress());
                }
                this.chat.writeMessage(msg.getSource(), msg.getMessage());                
            }
    } catch (Exception e) {
            System.out.println(e);
    }
    } //run

} // class ClientListener
