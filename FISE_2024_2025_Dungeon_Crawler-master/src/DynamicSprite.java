import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite{
    private Direction direction = Direction.EAST;
    private double speed = 5;
    private double runSpeed = 50;
    private double timeBetweenFrame = 250;
    private boolean isWalking =true;
    private boolean isRunning = false;
    private final int spriteSheetNumberOfColumn = 10;
// définissons des attributs

    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }
   public void setRunning (boolean Running){
        this.isRunning = isRunning;

    }
    public void setRunning2(boolean isRunning) {
        if (isRunning) {
            this.speed = 10; // Vitesse accélérée
        } else {
            this.speed = 5; // Vitesse normale
        }
    }

    private double getCurrentSpeed(){
        return isRunning ? runSpeed : speed;
    }
    private void moves() {
        double currentSpeed = getCurrentSpeed(); // Récupère la vitesse actuelle (course ou marche)
        switch (direction) {
            case NORTH -> this.y -= currentSpeed; // Déplacement vers le haut
            case SOUTH -> this.y += currentSpeed; // Déplacement vers le bas
            case EAST -> this.x += currentSpeed; // Déplacement vers la droite
            case WEST -> this.x -= currentSpeed; // Déplacement vers la gauche
        }
    }
    public void moveIfPossible2(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment)) {
            moves(); // Si le déplacement est possible, on déplace le héros
        }
    }
    private boolean isMovingPossible2(ArrayList<Sprite> environment) {
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch (direction) {
            case EAST:
                moved.setRect(super.getHitBox().getX() + getCurrentSpeed(), super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:
                moved.setRect(super.getHitBox().getX() - getCurrentSpeed(), super.getHitBox().getY(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() - getCurrentSpeed(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:
                moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() + getCurrentSpeed(),
                        super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }
        for (Sprite s : environment) {
            if ((s instanceof SolidSprite) && (s != this)) {
                if (((SolidSprite) s).intersect(moved)) {
                    return false; // Collision détectée, ne peut pas se déplacer
                }
            }
        }
        return true; // Pas de collision, peut se déplacer
    }
// Le constructeur initialise un sprite dynamique avec une position et une image, et des dimensions
    private boolean isMovingPossible(ArrayList<Sprite> environment){
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch(direction){
            case EAST: moved.setRect(super.getHitBox().getX()+speed,super.getHitBox().getY(),
                                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:  moved.setRect(super.getHitBox().getX()-speed,super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()-speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()+speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        for (Sprite s : environment){
            if ((s instanceof SolidSprite) && (s!=this)){
                if (((SolidSprite) s).intersect(moved)){
                    return false;
                }
            }
        }
        return true;
    }
// Cette méthode vérifie si le mouvement du sprite est possible dans l'environnement donné (en tenant compte des collisions).
//Elle calcule la position du sprite après un mouvement selon sa direction (NORTH, SOUTH, EAST, WEST)
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private void move(){
        switch (direction){
            case NORTH -> {
                this.y-=speed;
            }
            case SOUTH -> {
                this.y+=speed;
            }
            case EAST -> {
                this.x+=speed;
            }
            case WEST -> {
                this.x-=speed;
            }
        }
    }
// déplace le sprite dans la direction spécifiée
    public void moveIfPossible(ArrayList<Sprite> environment){
        if (isMovingPossible(environment)){
            move();
        }
    }

    @Override
    public void draw(Graphics g) {
        int index= (int) (System.currentTimeMillis()/timeBetweenFrame%spriteSheetNumberOfColumn);

        g.drawImage(image,(int) x, (int) y, (int) (x+width),(int) (y+height),
                (int) (index*this.width), (int) (direction.getFrameLineNumber()*height),
                (int) ((index+1)*this.width), (int)((direction.getFrameLineNumber()+1)*this.height),null);
    }
}
