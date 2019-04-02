package api;

import java.util.List;

public class PhysicalGame extends Game {

    public PhysicalGame(List<String> attributes) {
        super(attributes);
    }

    @Override
    public String toString() {
        return "Physical Game{" +
                "name='" + name + '\'' +
                "   ,  developer='" + developer + '\'' +
                "   ,  publisher='" + publisher + '\'' +
                "   ,  metacritic='" + metacritic + '\'' +
                "   ,  genre='" + genre + '\'' +
                "   ,  accessibility='" + accessibility + '\'' +
                "   ,  platform='" + subPlatform + '\'' +
                '}';
    }
}
