import javax.imageio.ImageIO; // importation des fichiers nécessaires à l'interface graphique
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs"); // Crée et configure une fenêtre graphique où l’application sera affichée
        displayZoneFrame.setSize(640,800); // Taille de la fenêtre : 400x600
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE); //L'application se ferme complètement quand on ferme la fenêtre

        DynamicSprite hero = new DynamicSprite(75,75, // création du héro il apparait à la position (200,300)
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50); // taille du sprite

        renderEngine = new RenderEngine(displayZoneFrame); // Gestionnaire du rendu graphique
        physicEngine = new PhysicEngine(); // Gestionnaire des effets physiques comme les mouvements et les collisions.
        gameEngine = new GameEngine(hero); // Gestionnaire de la logique de jeu, notamment pour contrôler le héros
// chacun des objets suivant assure une mise à jour régulière toutes les 50 ms
        Timer renderTimer = new Timer(50,(time)-> renderEngine.update()); // Rafraîchit l’affichage
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update()); // Met à jour la logique du jeu
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update()); // Met à jour les aspects physiques, comme les mouvements ou les collisions.
// Démarre chaque timer, assurant ainsi un cycle continu de mises à jour
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level = new Playground("./data/level1.txt"); // Charge un fichier décrivant le niveau de jeu, incluant des éléments graphiques (sprites) et des objets solides (pour les collisions).
        //SolidSprite testSprite = new DynamicSprite(100,100,test,0,0);
        renderEngine.addToRenderList(level.getSpriteList());// Ajoute les sprites du niveau et du héros
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList()); // Configure les éléments solides avec lesquels le héros peut entrer en collision

        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main (String[] args) throws Exception {
	// write your code here
        SwingUtilities.invokeLater(waintingScreen::new);
    }
}
