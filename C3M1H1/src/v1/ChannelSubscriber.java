package v1;

public abstract class ChannelSubscriber implements ChannelVideoUploadObserver {
    private final String name;

    public ChannelSubscriber(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void subscribe(Channel channel) {
        System.out.printf("%s 訂閱了 %s\n", getName(), channel.getName());
        channel.register(this);
    }

    public void unsubscribe(Channel channel) {
        System.out.printf("%s 解除訂閱了 %s\n", getName(), channel.getName());
        channel.unregister(this);
    }
}
