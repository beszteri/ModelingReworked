package api;

public enum Genre {
    Action_Adventure("Action Adventure"),
    Racing("Racing"),
    Horror("Horror"),
    Adventure("Adventure"),
    JRPG("JRPG"),
    Action_RPG("Action RPG"),
    Puzzle("Puzzle"),
    fighting("fighting"),
    Shooter("Shooter"),
    Platformer("Platformer"),
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
