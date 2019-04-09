package api;


import java.util.List;

public abstract class Game {

    protected String name;
    protected String developer;
    protected String publisher;
    protected int metacritic;
    protected String genre;
    protected String accessibility;
    protected String subPlatform;

    public Game(String name, String developer, String publisher, int metacritic, String genre, String accessibility, String subPlatform) {
        this.name = name;
        this.developer = developer;
        this.publisher = publisher;
        this.metacritic = metacritic;
        this.genre = genre;
        this.accessibility = accessibility;
        this.subPlatform = subPlatform;
    }

    public String getName() {
        return name;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getMetacritic() {
        return metacritic;
    }

    public String getGenre() {
        return genre;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public String getSubPlatform() {
        return subPlatform;
    }
}
