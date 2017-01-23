package engine.communicate;

public interface ClientListener extends ConnectionListener{
	
	public void onError(Chirp chirp);
	public void onConnect(UserClient server);
	public void onConnectionLost(UserClient server);
	
}
