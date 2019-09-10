
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a1602632
 */
public class ListenerUDPListener extends Thread{
    ChatGUI chat;                    // referencia para a interface grafica
    private String port = "6799";
    private DatagramSocket udpSocket = new DatagramSocket("6799");
    
    public void run() {
        while(true){
              byte[] buffer = new byte[1000];
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(messageIn);
                Mensagem msg = new Mensagem(messageIn.getData());

                if(msg.isJoinACK()){
                    this.chat.addNickname(msg.getSource(), messageIn.getAddress().getHostAddress());
                }
                this.chat.writeMessage(msg.getSource(), msg.getMessage());        
        }
    }
    
    
}
