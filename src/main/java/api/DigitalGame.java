package api;

import java.util.List;

public class DigitalGame extends Game {

    public DigitalGame(String name, String developer, String publisher, int metacritic, Genre genre, String accessibility, String subPlatform) {
        super(name, developer, publisher, metacritic, genre, accessibility, subPlatform);
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
