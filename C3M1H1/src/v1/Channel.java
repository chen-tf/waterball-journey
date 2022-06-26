package v1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Channel {

    private final List<Video> videos;
    private final String name;
    private final Set<ChannelVideoUploadObserver> channelVideoUploadObservers;

    public Channel(String name) {
        this.name = name;
        this.videos = new ArrayList<>();
        channelVideoUploadObservers = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void register(ChannelVideoUploadObserver observer) {
        channelVideoUploadObservers.add(observer);
    }

    public void unregister(ChannelVideoUploadObserver observer) {
        channelVideoUploadObservers.remove(observer);
    }

    public void upload(Video video) {
        System.out.printf("頻道 %s 上架了一則新影片 %s\n", getName(), video.getTitle());
        videos.add(video);
        new ArrayList<>(channelVideoUploadObservers)
                .forEach(observer -> observer.update(this, video));
    }
}
