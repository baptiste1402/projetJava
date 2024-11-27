import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    DynamicSprite hero;

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    @Override
    public void update() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
            case KeyEvent.VK_A:
                hero.setRunning2(true); // Active la course
                break;


        }
    }


    @Override
    public void keyReleased(KeyEvent e){
            switch (e.getKeyCode()) {
                // Arrêt de la course avec la touche A
                case KeyEvent.VK_A:
                    hero.setRunning2(false); // Le joueur arrête de courir
                    break;
            }


    }
}

