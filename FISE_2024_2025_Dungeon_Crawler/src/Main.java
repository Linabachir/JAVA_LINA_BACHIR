import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
public class Main {

    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    GameTime gameTime;
    private ArrayList<DynamicSprite> pacmanList = new ArrayList<>(); // Liste des pacman
    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(850,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(200,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50);

        DynamicSprite pacman = new DynamicSprite(200,300, // automove
                ImageIO.read(new File("./img/pacmanSheetLowRes.png")),48,50,true);

        //Lina: Initialiser le timer
        gameTime = new GameTime(60); // 60 secondes
        renderEngine = new RenderEngine(displayZoneFrame, gameTime);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);
        //
        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());
        Timer gameTimeTimer = new Timer(1000, (time) -> gameTime.update()); // 1 seconde

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();
        gameTimeTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level = new Playground("./data/level1.txt");
        //SolidSprite testSprite = new DynamicSprite(100,100,test,0,0);
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        renderEngine.addToRenderList(pacman);
        physicEngine.addToMovingSpriteList(pacman);
        physicEngine.setEnvironment(level.getSolidSpriteList());
        adjustPacmanList(2); // Crée exactement 2 additionnal pacmans
        // (pour faciliter le creation au niveau supérieur)

        displayZoneFrame.addKeyListener(gameEngine);
    }
    // Méthode pour ajuster la liste de pacman au nombre désiré
    private void adjustPacmanList(int desiredCount) throws Exception {
        Random random = new Random();

        // Ajouter des pacman si nécessaire
        while (pacmanList.size() < desiredCount) {
            int randomX = random.nextInt(800); // Limité à la largeur de la fenêtre
            int randomY = random.nextInt(550); // Limité à la hauteur de la fenêtre
            DynamicSprite newPacman = new DynamicSprite(randomX, randomY,
                    ImageIO.read(new File("./img/pacmanSheetLowRes.png")), 48, 50, true);
            pacmanList.add(newPacman);
            renderEngine.addToRenderList(newPacman);
            physicEngine.addToMovingSpriteList(newPacman);        }


    }

    public static void main (String[] args) throws Exception {
	// write your code here
        Main main = new Main();
    }
}
