package api;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class GameModelling {

    private List<Game> games;
    private List<FavoriteList> favoritLists = new ArrayList<>();
    private List<Game> favoritList = new ArrayList<>();
    private List<Game> tempList = new ArrayList<>();

    public GameModelling(List<Game> games, List<FavoriteList> favoritLists) {
        this.games = games;
        this.favoritLists = favoritLists;
    }

    public GameModelling(List<Game> games) {
        this.games = games;
    }

    public void addGame(String name, String developer, String publisher, int metacritic, String genre, String accessibility, String subPlatform) {
        if (accessibility.equals("Disk")) {
            games.add(new PhysicalGame(name, developer, publisher, metacritic, genre, accessibility, subPlatform));
        } else {
            games.add(new DigitalGame(name, developer, publisher, metacritic, genre, accessibility, subPlatform));
        }
    }

    public void removeGame(int id) {
        games.remove(id - 1);
    }

    public List<Game> getGames() {
        return games;
    }

    public List<FavoriteList> getFavoritLists() {
        return favoritLists;
    }

    public void addGameToAFavlist(String name, String flName) {
        for (FavoriteList fl : favoritLists) {
            if (fl.getName().equals(flName)) {
                for (Game g : games) {
                    if (g.getName().equals(name)) {
                        fl.addToList(g);
                    }
                }
            }
        }

    }

    public void removeFromFavoriteList(String name, String flName) {
        for (FavoriteList fl : favoritLists) {
            if (fl.getName().equals(flName)) {
                for (Game g : fl.getFavGames()) {
                    if (g.getName().equals(name)) {
                        fl.getFavGames().remove(g);
                    } else {
                        System.out.println("no such game");
                    }
                }
            } else {
                System.out.println("no such game");
            }
        }
    }


    public void removeFavoriteList(int id) {
        favoritLists.remove(id - 1);
    }

    public void getGamesByPlatform(String platform) {
        for (Game g : games) {
            if (g.getSubPlatform().equals(platform)) {
                tempList.add(g);
            }
        }
        for (Game g : tempList) {
            System.out.println(g.getName());
        }
    }

    public void createFavoriteList(String name) {
        FavoriteList fl = new FavoriteList(name);
        favoritLists.add(fl);
        System.out.println(fl.getName() + " added to the favorite lists");
    }

    public void addToFavoriteLists() {

    }




    public boolean enumValidator(String name) {
        List<String> enumList = new ArrayList<>();
        enumList.add("MOBA");
        enumList.add("HORROR");


        for(String s: enumList) {
            if(name.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

}
