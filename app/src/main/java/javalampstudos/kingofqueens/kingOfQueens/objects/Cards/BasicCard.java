package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;


// Local Imports
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

// Android Imports
import android.graphics.Bitmap;


// so it's linked to update
public class BasicCard extends GameObject {

    public String name;

    // monitors whether or not a card is destroyed
    public boolean destroyed;

    // need all the fields from the superclass
    public BasicCard(int x, int y, int width, int height, Bitmap sprite, String name, boolean destroyed) {

        super(x, y, width, height, sprite);
        this.name = name;
        this.destroyed = destroyed;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
