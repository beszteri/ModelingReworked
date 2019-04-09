package api;

import java.util.List;

public class PhysicalGame extends Game {

    public PhysicalGame(String name, String developer, String publisher, int metacritic, Genre genre, String accessibility, String subPlatform) {
        super(name, developer, publisher, metacritic, genre, accessibility, subPlatform);
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
