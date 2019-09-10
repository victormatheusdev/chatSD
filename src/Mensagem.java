/*
 * Classe representa uma mensagem
 * @author Rodrigo
 */

public class Mensagem {
    
    private byte type;           // tipo da mensagem 
    private String source;       // origem (apelido)
    private String message;      // conteudo da mensagem

    
    /* Construtor 1 */
    public Mensagem (byte typeMsg, String nick, String msg) {
        type = typeMsg;
        source = nick;
        message = msg;
    } // construtor 1

    
    /* Construtor 2 */
    public Mensagem (byte[] msg) {
        int sizeApelido = (int) msg[1];
        
        type = msg[0];
        source = new String(msg, 2, sizeApelido);
        message = new String(msg,2+sizeApelido,msg.length-(sizeApelido+2));
    } // construtor 2
    public boolean isJoinACK(){
        return (this.type == ((Integer) 2).byteValue());
    }
    
    /**
     * Transformar a mensagem em uma sequencia de bytes
     * byte 0 = tipo da mensagem
     * byte 1 = tamanho apelido
     * byte 2 a 2+tamanho apelido = apelido
     * byte 2+tamalho apelido a final = mensagem
     */
    public byte[] getBytes() {
        int size = 1 + 1 + source.length() + message.length(); // tamanho do vetor
        byte[] msg = new byte[size];
        
        msg[0] = type;
        msg[1] = (byte) source.length();
        System.arraycopy(source.getBytes(), 0, msg, 2, source.length());
        System.arraycopy(message.getBytes(), 0, msg, 2+source.length(), message.length());
        
        return msg;
    } //getBytes

    
    /** metodos para obter o conteudo da mensagem */
    public byte getType() { return type; }
    public String getSource() { return source; }
    public String getMessage() { return message; }
    
} // class Mensagem
