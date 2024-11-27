import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite{
    protected int lifeModifierCount = 0;
    protected boolean doesValidateLevel = false;
    public SolidSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }
    //Lina: add new construction override signature with levelModifier argument
    public SolidSprite(double x, double y, Image image, double width, double height, int lifeModifier, boolean validateLevel) {
        super(x, y, image, width, height);
        this.lifeModifierCount = lifeModifier;
        this.doesValidateLevel = validateLevel;
    }
    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x,y,(double) width,(double) height);
    }

    public boolean intersect(Rectangle2D.Double hitBox){
        return this.getHitBox().intersects(hitBox);
    }
}