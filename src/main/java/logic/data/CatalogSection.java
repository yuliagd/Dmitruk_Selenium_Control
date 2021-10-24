package logic.data;

public enum CatalogSection {
PC_AND_LAPTOP ("Ноутбуки и компьютеры");


    private String name;
    private  CatalogSection (String name){
        this.name = name;
    }
    public String get() {
        return name;
    }
}
