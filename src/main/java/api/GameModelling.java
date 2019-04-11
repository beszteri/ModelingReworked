package api;

import java.util.ArrayList;
import java.util.List;

public class GameModelling {

    private List<Game> games;
    private List<FavoriteList> favoritLists = new ArrayList<>();
    private List<Game> tempList = new ArrayList<>();

    public List<Game> getTempList() {
        return tempList;
    }

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

    public List<Game> getGames() {
        return games;
    }

    public List<FavoriteList> getFavoritLists() {
        return favoritLists;
    }

    public void addGameToAFavlist(String name, String flName) throws AlreadyAddedToTheListException, NoSuchGameException {
        for (FavoriteList fl : favoritLists) {
            if (fl.getName().equals(flName)) {
                for (Game g : games) {
                    if (g.getName().equals(name)) {
                        if(fl.getFavGames().contains(g)){
                            throw new AlreadyAddedToTheListException("Already added to the list");
                        }

                            fl.addToList(g);

                    }else {
                        throw new NoSuchGameException("No such game");
                    }
                }
            }
        }
    }

    public void removeFromFavoriteList(String name, String flName) { //tudom hogy cigány megoldás, de mindig concurrentmodificationerrort kaptam mert iterálás közben akartam removolni.
        List<Game> reload = new ArrayList<>();
        for (FavoriteList fl : favoritLists) {
            if (fl.getName().equals(flName)) {
                for (Game g : fl.getFavGames()) {
                    if (g.getName().equals(name)) {
                    } else {
                        reload.add(g);
                    }
                }fl.favGames.clear();
                for (Game g : reload){
                    fl.favGames.add(g);
                }
            } else {
                System.out.println("no such list");
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
    }

    public void createFavoriteList(String name, Boolean isGoodList) throws ListAlreadyCreatedException {
        for (FavoriteList fl : favoritLists) {
            if (fl.getName().equals(name)) {
                throw new ListAlreadyCreatedException("list already created");
            }
        }
        if (isGoodList) {
            FavoritGoodGames fgg = new FavoritGoodGames(name);
            favoritLists.add(fgg);
            System.out.println(fgg.getName() + " added to the favorite lists");
        } else {
            FavoriteBadGames fbg = new FavoriteBadGames(name);
            favoritLists.add(fbg);
            System.out.println(fbg.getName() + " added to the favorite lists");
        }
    }
}
