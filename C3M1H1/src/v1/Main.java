package v1;

import java.time.Duration;

public class Main {

    public static void main(String[] args) {
        ChannelSubscriber waterball = new Waterball();
        ChannelSubscriber fireball = new Fireball();
        Channel pewDiePie = new Channel("PewDiePie");
        Channel waterballCollege = new Channel("水球軟體學院");
        waterball.subscribe(waterballCollege);
        waterball.subscribe(pewDiePie);
        fireball.subscribe(waterballCollege);
        fireball.subscribe(pewDiePie);
        waterballCollege.upload(new Video("\"C1M1S2\"", "這個世界正是物件導向的呢！", Duration.ofMinutes(4)));
        pewDiePie.upload(new Video("\"Hello guys\"", "Clickbait", Duration.ofSeconds(30)));
        waterballCollege.upload(new Video("\"C1M1S3\"", "物件 vs. 類別", Duration.ofMinutes(1)));
        pewDiePie.upload(new Video("\"Minecraft\"", "Let’s play Minecraft", Duration.ofMinutes(30)));
    }
}
