package api;

import java.util.List;

public class DigitalGame extends Game {

    public DigitalGame(List<String> attributes) {
        super(attributes);
    }

    @Override
    public String toString() {
        return "Digital Game{" +
                "name='" + name + '\'' +
                "   ,  developer='" + developer + '\'' +
                "   ,  publisher='" + publisher + '\'' +
                "   ,  metacritic='" + metacritic + '\'' +
                "   ,  genre='" + genre + '\'' +
                "   ,  Download size='" + accessibility + '\'' +
                "   ,  platform='" + subPlatform + '\'' +
                '}';
    }
}
