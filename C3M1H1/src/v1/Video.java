package v1;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class Video {
    private final String title;
    private final String description;
    private final Duration length;
    private final Set<ChannelSubscriber> liker;

    public Video(String title, String description, Duration length) {
        this.title = title;
        this.description = description;
        this.length = length;
        this.liker = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Duration getLength() {
        return length;
    }

    public void like(ChannelSubscriber channelSubscriber) {
        liker.add(channelSubscriber);
    }
}
