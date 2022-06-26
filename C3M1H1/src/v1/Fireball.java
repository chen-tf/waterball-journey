package v1;

import java.time.Duration;

public class Fireball extends ChannelSubscriber {
    public Fireball() {
        super("火球");
    }

    @Override
    public void update(Channel channel, Video video) {
        if (video.getLength().compareTo(Duration.ofMinutes(1)) <= 0) {
            unsubscribe(channel);
        }
    }
}
