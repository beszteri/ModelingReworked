package api;


import java.util.List;

public abstract class Game {

    protected String name;
    protected String developer;
    protected String publisher;
    protected String metacritic;
    protected String genre;
    protected String accessibility;
    protected String subPlatform;

    public Game(List<String> attributes) {
        this.name = attributes.get(0);
        this.developer = attributes.get(1);
        this.publisher = attributes.get(2);
        this.metacritic = attributes.get(3);
        this.genre = attributes.get(4);
        this.accessibility = attributes.get(5);
        this.subPlatform = attributes.get(6);
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

    public String getMetacritic() {
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
