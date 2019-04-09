package api;

public enum Genre {
    ACTION_ADVENTURE("Action Adventure"),
    RACING("Racing"),
    HORROR("Horror"),
    ADVENTURE("Adventure"),
    JRPG("JRPG"),
    ACTION_RPG("Action RPG"),
    PUZZLE("Puzzle"),
    FIGHTING("fighting"),
    SHOOTER("Shooter"),
    PLATFORMER("Platformer"),
    MOBA("MOBA"),
    MMORPG("MMORPG"),
    RTS("RTS"),
    ROLE_PLAYING("Role-Playing");

    private final String name;

    private Genre(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


}
