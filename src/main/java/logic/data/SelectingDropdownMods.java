package logic.data;

public enum SelectingDropdownMods {
    INDEX("byIndex"),
    VALUE("byValue"),
    TEXT("byVisibleText");

    private String name;

    private SelectingDropdownMods(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
