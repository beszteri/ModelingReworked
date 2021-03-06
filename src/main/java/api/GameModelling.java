package api;

import Exceptions.AlreadyAddedToTheListException;
import Exceptions.ListAlreadyCreatedException;
import Exceptions.NoSuchGameException;
import Exceptions.NoSuchListException;

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

    public void addGameToAFavlist(String name, String flName) throws AlreadyAddedToTheListException, NoSuchGameException, NoSuchListException, MetacriticException {
       if (!favoritLists.contains(new FavoritGoodGames(flName))){
            throw new NoSuchListException("No such list");
        }else if(!games.contains(new DigitalGame(name))){
           throw new NoSuchGameException("No such game");
       }else {

           Game a = null;
           for (Game g : games){
               if ( g.getName().equals(name)){
                   a = g;
                   gameValidator(name, flName);
               }
           }
           FavoriteList f = null;
           for ( FavoriteList fl : favoritLists){
               if (fl.getName().equals(flName)){
                   f = fl;
               }
           }
           f.addToList(a);
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

    public void gameValidator(String game, String flName) throws AlreadyAddedToTheListException {
        for(FavoriteList fl : favoritLists){
            if (fl.getName().equals(flName)){
                for (Game g : fl.getFavGames()){
                    if(g.getName().equals(game)){
                        throw new AlreadyAddedToTheListException("Already added");
                    }
                }
            }
        }
    }
}
