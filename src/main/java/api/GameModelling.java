package api;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


public class GameModelling {

    private List<Game> games;
    private List<FavoriteList> favoritLists = new ArrayList<>();

    public GameModelling(List<Game> games) {
        this.games = games;
    }

    public void addGame(String name, String developer, String publisher, int metacritic, Genre genre, String accessibility, String subPlatform) {
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

    public List<Game> getGamesByPlatform(String platform) {
        List<Game> tempList = new ArrayList<>();
        for (Game g : games) {
            if (g.getSubPlatform().equals(platform)) {
                tempList.add(g);
            }
        }
        return tempList;
    }

    public void createFavoriteList(String name, Boolean isGoodList) throws ListAlreadyCreatedException {
        for(FavoriteList fl : favoritLists) {
            if (fl.getName().equals(name)) {
                throw new ListAlreadyCreatedException("list already created");
            }
        }
        if(isGoodList){
            FavoritGoodGames fgg = new FavoritGoodGames(name);
            favoritLists.add(fgg);
            System.out.println(fgg.getName() + " added to the favorite lists");
        }else{
            FavoriteBadGames fbg = new FavoriteBadGames(name);
            favoritLists.add(fbg);
            System.out.println(fbg.getName() + " added to the favorite lists");
        }
    }




    public void addToFavoriteLists() {

    }


}
