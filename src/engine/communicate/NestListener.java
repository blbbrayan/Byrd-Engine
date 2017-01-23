package engine.communicate;

public interface NestListener extends ConnectionListener{
	
	public void onAccountConnected(Account account, UserClient userClient);
	
}
