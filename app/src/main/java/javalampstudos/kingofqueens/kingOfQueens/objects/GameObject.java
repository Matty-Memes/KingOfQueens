package javalampstudos.kingofqueens.kingOfQueens.objects;

/**
 * Created by 40083349 on 13/01/2017.
 */

// Android Imports

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

// Local Imports

import javalampstudos.kingofqueens.GameLoop;

public abstract class GameObject

{

    public float x, y;
    public int width, height;
    // sprites and bitmaps are interchangeable
    public Bitmap sprite;
    // Drawing information
    public Rect rect;


    public GameObject(float x, float y, int width, int height,
                      Bitmap sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        // instantiate a new rect
        this.rect = new Rect();
    }

    public void update ()

    {
        // for moving objects velocity would go here
        // this values are global anyway and set by the creation of the card

        updateRect();

    }

    // all update calls eventually lead here - must relate to x and y co-ordinates

    public void updateRect ()

    {
        rect.set((int) (x - (width / 2 * GameLoop.gameScaling)),
                (int) (y - (height / 2 * GameLoop.gameScaling)),
                (int) (x + (width / 2 * GameLoop.gameScaling)),
                (int) (y + (height / 2 * GameLoop.gameScaling)));

    }

    public void draw (Canvas canvas)

    {
        if (sprite == null)

        {
            System.out.println("There is no sprite");
        }


        canvas.drawBitmap(sprite, null, rect, null);

    }

    // check if the object is on screen

    public static boolean isOnScreen(GameObject object) {
        if(object.x <= -object.width * GameLoop.gameScaling
                || object.x >= GameLoop.width
                || object.y <= -object.height * GameLoop.gameScaling
                || object.y > GameLoop.height)
            return false;
        return true;
    }

    public static boolean isOffScreen(GameObject object)

    {
        return !isOnScreen(object);
    }






}
