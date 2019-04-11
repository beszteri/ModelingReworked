package api;

public class FavoriteBadGames extends FavoriteList {
    public FavoriteBadGames(String name) {
        super(name);
    }

    @Override
    public void addToList(Game game){

            favGames.add(game);

    }
}
