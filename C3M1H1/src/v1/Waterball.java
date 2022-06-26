package v1;

import java.time.Duration;

public class Waterball extends ChannelSubscriber {
    public Waterball() {
        super("水球");
    }

    @Override
    public void update(Channel channel, Video video) {
        if (video.getLength().compareTo(Duration.ofMinutes(3)) >= 0) {
            like(video);
        }
    }

    private void like(Video video) {
        System.out.printf("%s 對影片 %s 按讚\n", getName(), video.getTitle());
        video.like(this);
    }
}
