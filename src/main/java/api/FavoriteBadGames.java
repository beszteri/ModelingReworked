package api;

public class FavoriteBadGames extends FavoriteList {
    public FavoriteBadGames(String name) {
        super(name);
    }

    @Override
    public void addToList(Game game) throws MetacriticException {
        if (game.getMetacritic() < 87) {
            favGames.add(game);
        }else {
            throw new MetacriticException("Wrong list");
        }
    }
}
