package api;

import java.util.ArrayList;
import java.util.List;

public abstract class FavoriteList {

    protected List<Game> favGames = new ArrayList<>();
    protected String name;

    public String getName() {
        return name;
    }

    public FavoriteList(String name){
        this.name = name;
    }

    public void addToList(Game game) throws MetacriticException {}

    public boolean equals(Object o){
        FavoriteList fl = (FavoriteList) o;
        return this.name.equals(fl.getName());
    }

    public List<Game> getFavGames() {
        return favGames;
    }
}
