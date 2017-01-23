package engine.communicate;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class UserClient implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Socket socket;
	private ObjectInputStream getter;
	private ObjectOutputStream sender;
	
	public UserClient(){}
	public UserClient(Socket socket, ObjectInputStream getter, ObjectOutputStream sender){
		setSocket(socket);
		setGetter(getter);
		setSender(sender);
	}
	
	public Object getObj() throws ClassNotFoundException, IOException{
		return getter.readObject();
	}
	
	public void sendObj(Object obj){
		try {
			sender.writeObject(obj);
			sender.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() throws IOException{
		getter.close();
		sender.close();
		socket.close();
		socket = null;
		sender = null;
		getter = null;
	}
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public ObjectInputStream getGetter() {
		return getter;
	}
	public void setGetter(ObjectInputStream getter) {
		this.getter = getter;
	}
	public ObjectOutputStream getSender() {
		return sender;
	}
	public void setSender(ObjectOutputStream sender) {
		this.sender = sender;
	}
	
}
