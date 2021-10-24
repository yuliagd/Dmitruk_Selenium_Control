package logic.data;

public enum PCAndLaptopSection {
    LAPTOP ("Ноутбуки");

    private String name;

    private PCAndLaptopSection(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}
