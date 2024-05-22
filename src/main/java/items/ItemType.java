package items;

public enum ItemType {
    GOLD("dollar"),
    POTION("potion"),
    SWORD("sword"),
    MITRE("mitra");

    private final String name;

    // Constructor
    ItemType(String name) {
        this.name = name;
    }

    // Getter for description
    public String getName() {
        return name;
    }
}