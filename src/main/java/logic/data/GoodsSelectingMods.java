package logic.data;

public enum GoodsSelectingMods {
    RANK ("По рейтингу"),
    CHEAP ("От дешевых к дорогим"),
    EXPENSIVE ("От дорогих к дешевым"),
    POPULARITY("Популярные"),
    NOVELTY("Новинки"),
    ACTION ("Акционные");

    private String name;

    private GoodsSelectingMods(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
