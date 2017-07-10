/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mensaje;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author alex
 */
public class ObjectSocket {

    Socket socket = null;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ObjectSocket(Socket socket) throws IOException {
        this.socket = socket;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());

    }

    public ObjectInputStream getInput() {
        return input;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public void close() throws IOException {
        socket.close();
    }
}
