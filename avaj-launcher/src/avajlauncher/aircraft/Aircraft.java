package avajlauncher.aircraft;

public class Aircraft{
    
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    // underline in UML means static
    private static long idCounter = 1;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    private long nextId() {
        return idCounter++;
    }

}