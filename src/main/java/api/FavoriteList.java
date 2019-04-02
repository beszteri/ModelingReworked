package api;

import java.util.ArrayList;
import java.util.List;

public class FavoriteList {

    private List<Game> favGames = new ArrayList<>();
    private String name;

    public String getName() {
        return name;
    }

    public FavoriteList(String name){
        this.name = name;
    }

    public FavoriteList(List<Game> favGames) {
        this.favGames = favGames;
    }

    public void addToList(Game game){
        favGames.add(game);
    }

    public List<Game> getFavGames() {
        return favGames;
    }
}
