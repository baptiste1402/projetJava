import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.imageio.ImageIO;

public class waintingScreen {
    private JFrame startFrame;

    public waintingScreen() {
        // Initialisation de la fenêtre
        startFrame = new JFrame("Start Screen");
        startFrame.setSize(640, 800);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panneau pour l'image de fond
        JPanel panel = new ImagePanel("/Users/baptistegouedo/Downloads/FISE_2024_2025_Dungeon_Crawler-master/img/ensea.png"); // Chemin vers l'image
        panel.setLayout(new BorderLayout());
        startFrame.add(panel);

        // Ajouter un bouton "Démarrer"
        JButton startButton = new JButton("Démarrer");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(startButton, BorderLayout.SOUTH);

        // Action du bouton
        startButton.addActionListener((ActionEvent e) -> {
            startFrame.dispose(); // Fermer l'écran de démarrage
            try {
                Main main = new Main(); // Lancer le jeu
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        startFrame.setVisible(true);
    }

    // Classe interne pour dessiner une image en arrière-plan
    private static class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            try {
                backgroundImage = ImageIO.read(new File(imagePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Dessiner l'image
            }
        }
    }
}
