public class door extends SolidSprite {
    private String destinationLevel; // Nom du niveau à charger
    private double destinationX, destinationY; // Position du joueur dans le niveau suivant

    public door(double x, double y, double width, double height, String destinationLevel, double destinationX, double destinationY) {
        super(x, y, null, width, height); // Pas besoin d'image pour l'instant
        this.destinationLevel = destinationLevel;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
    }

    public String getDestinationLevel() {
        return destinationLevel;
    }

    public double getDestinationX() {
        return destinationX;
    }

    public double getDestinationY() {
        return destinationY;
    }
}
