import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine{
    private ArrayList<Displayable> renderList;
    public boolean gameOver = false;
    int gameLevel = 0;
    private GameTime gameTime; // declaration de l'attribut gameTimer pour utiliser le timer du jeu
    public RenderEngine(JFrame jFrame, GameTime gameTime) {
        renderList = new ArrayList<>();
        this.gameTime = gameTime; // garder l'horloge du jeu
    }

    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        boolean isLevelValidated = false;
        if(!gameOver && !gameTime.isGameOver()) { // verifie si jeu est terminé
            for (Displayable renderObject : renderList) { // parcours la liste des objets
                if ((renderObject instanceof DynamicSprite)) { // si un objet est une instance DynamicSprite
                    //
                    //gestion du polymorphisme de renderObject qui est de type Displayable
                    // renderObject.lifeLevel ici le compilateur n'aime pas
                    int level = ((DynamicSprite) renderObject).lifeLevel; // cast du type comme il herite de plusieurs,
                    // il faut lui dire quel classe on vise, DynamicSprite pour éviter les erreurs
                    isLevelValidated = ((DynamicSprite) renderObject).isLevelValidated;
                    if (level <= 0) {
                        // gameover
                        gameOver = true;
                    }
                    g.setColor(Color.GREEN);
                    g.setFont(new Font("Arial", Font.BOLD, 15));
                    g.drawString("Level:"+level, 10, 20);
                }
                renderObject.draw(g); // affiche l'objet à l'ecran
            }
            // Afficher le temps restant
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Time: " + gameTime.getTimeRemaining() + "s", 700, 30);
        }else {
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString("GAME OVER", 100, 300);
        }
        if(isLevelValidated){
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("LEVEL VALIDATE", 100, 300);
        }
    }

    @Override
    public void update(){
        this.repaint();
    }
}
