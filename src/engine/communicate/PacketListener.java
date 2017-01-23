package engine.communicate;

public abstract class PacketListener<T> {
    private T packetType;

    public PacketListener(T packetType) {
        this.packetType = packetType;
    }

    public abstract void onPacketReceived(T packet);

    public Class packetType(){
        return packetType.getClass();
    }

    public boolean instanceOf(Object e){
        return packetType.getClass().isInstance(e);
    }
}
