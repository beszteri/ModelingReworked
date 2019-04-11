package api;


import java.util.List;

public abstract class Game {

    protected String name;
    protected String developer;
    protected String publisher;
    protected int metacritic;
    protected Genre genre;
    protected String accessibility;
    protected String subPlatform;

    public Game(String name, String developer, String publisher, int metacritic, Genre genre, String accessibility, String subPlatform) {
        this.name = name;
        this.developer = developer;
        this.publisher = publisher;
        this.metacritic = metacritic;
        this.genre = genre;
        this.accessibility = accessibility;
        this.subPlatform = subPlatform;
    }

    public Game(String name){
        this.name = name;
    }

    public boolean equals(Object o){
        Game g = (Game)o;
        return this.name.equals(g.getName());
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

    public Genre getGenre() {
        return genre;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public String getSubPlatform() {
        return subPlatform;
    }
}
