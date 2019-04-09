package api;

import java.util.ArrayList;
import java.util.List;

public abstract class FavoriteList {

    private List<Game> favGames = new ArrayList<>();
    private String name;

    public String getName() {
        return name;
    }

    public FavoriteList(String name){
        this.name = name;
    }

    public void addToList(Game game){}

    public List<Game> getFavGames() {
        return favGames;
    }
}
