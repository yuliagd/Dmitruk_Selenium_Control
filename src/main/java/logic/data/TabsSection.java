package logic.data;

public enum TabsSection {
    PRODUCT ("Все о товаре"),
    CHARACTERISTICTS ("Характеристики"),
    COMMENTS ("Оставить отзыв"),
    QUESTIONS("Задать вопрос"),
    PHOTO ("Фото");

    private String name;

    private TabsSection(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
