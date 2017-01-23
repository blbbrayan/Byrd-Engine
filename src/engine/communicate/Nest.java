package engine.communicate;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import engine.database.ByrdBase;

public class Nest {
	
	private ServerSocket server;
	private boolean on;
	private String saveLocation;
	private boolean autoLogin;
	
	public Nest(String saveLocation){
		ByrdBase.config(saveLocation, new String[]{"UserAccounts"});
		setSaveLocation(saveLocation);
		autoLogin = false;
	}
	
	public Nest(){
		autoLogin = true;
	}
	
	public void build(int port, NestListener nestListener){
		try {
			setServer(new ServerSocket(port));
			setOn(true);
			new Thread(new ClientFinder(nestListener)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		setOn(false);
	}
	
	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public String getSaveLocation() {
		return saveLocation;
	}

	public void setSaveLocation(String saveLocation) {
		this.saveLocation = saveLocation;
	}
	
	public String getIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "getIP error";
		}
	}
	
	public boolean findUsername(Account userAccount){
		Object[] group = ByrdBase.loadAll(saveLocation, "UserAccounts");
		for(Object obj: group){
			if(obj instanceof Account){
				if(((Account) obj).getUsername().equals(userAccount.getUsername())){
					return true;
				}
			}
		}
		return false;
	}
	public boolean findAccount(Account userAccount){
		Object[] group = ByrdBase.loadAll(saveLocation, "UserAccounts");
		for(Object obj: group){
			if(obj instanceof Account){
				if(((Account) obj).getUsername().equals(userAccount.getUsername())){
					if(((Account) obj).getPassword().equals(userAccount.getPassword())){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public class ClientSetup implements Runnable{
		UserClient client;
		NestListener nestListener;
		public ClientSetup(UserClient client, NestListener nestListener){
			this.client = client;
			this.nestListener = nestListener;
		}
		public void run(){
			while(isOn()){
				try {
					Object obj = client.getObj();
					Account userAccount;
					if(obj instanceof Account){
						userAccount = (Account) obj;
						if(findUsername(userAccount)){
							if(findAccount(userAccount)){
								client.sendObj(Chirp.Connected);
								System.out.println("Connected");
								nestListener.onAccountConnected(userAccount, client);
								break;
							}else{
								client.sendObj(Chirp.IncorrectPassword);
							}
						}else{
							client.sendObj(Chirp.UnknownUserAccount);
						}
					}else if (obj instanceof Chirp){
						if(obj == Chirp.CreateUserAccount){
							userAccount = (Account) client.getGetter().readObject();
							if(!findUsername(userAccount)){
								ByrdBase.save(saveLocation, "UserAccounts", userAccount.getUsername(), userAccount, false);
								client.sendObj(Chirp.Connected);
								System.out.println("Connected");
								nestListener.onAccountConnected(userAccount, client);
								break;
							}else{
								client.sendObj(Chirp.UserAccountTaken);
							}
						}
					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}
	
	public class ClientFinder implements Runnable{
		NestListener nestListener;
		public ClientFinder(NestListener e){
			nestListener = e;
		}
		public void run(){
			while(isOn()){
				try {
					Socket socket = server.accept();
					ObjectOutputStream sender = new ObjectOutputStream(socket.getOutputStream());
					sender.flush();
					ObjectInputStream getter = new ObjectInputStream(socket.getInputStream());
					UserClient client = new UserClient(socket, getter, sender);
					if(autoLogin){
						client.sendObj(Chirp.Connected);
						System.out.println("Connected");
						nestListener.onAccountConnected(new Account("none", "none"), client);
					}else{
						new Thread(new ClientSetup(client, nestListener)).start();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
