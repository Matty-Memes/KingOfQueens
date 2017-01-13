package javalampstudos.kingofqueens.kingOfQueens.objects.Cards;


// Local Imports
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;

// Android Imports
import android.graphics.Bitmap;

public class BasicCard extends GameObject {

    private String name;

    // need all the fields from the superclass
    public BasicCard(int x, int y, int width, int height, Bitmap sprite, String name) {

        super(x, y, width, height, sprite);
        this.name = name;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
