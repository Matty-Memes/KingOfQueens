package javalampstudos.kingofqueens.kingOfQueens.objects;

/**
 * Created by 40083349 on 13/01/2017.
 */

// Android Imports

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Matrix;
import android.support.v4.app.Fragment;

// Local Imports

import javalampstudos.kingofqueens.GameLoop;

// Test flipping images
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

public abstract class GameObject

{

    public float x, y;
    public int width, height;
    // sprites and bitmaps are interchangeable
    public Bitmap sprite;
    // Drawing information
    public Rect rect;

    // true if this is a player card
    // false if it's an opponent card
    public boolean player;

    // For flipping images
    // Source rect

    // Have a single source rect and change the values of the dest rect
    // Top right is now top left
    // Bottom
    Rect source = new Rect(432, 641, 0, 0);
    // Destination rect

    public GameObject(float x, float y, int width, int height,
                      Bitmap sprite, boolean player) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        this.player = player;

        // instantiate a new rect
        // this isn't exposed in the objects
        this.rect = new Rect();
    }

    // work out the destination rect

    public void update ()

    {
        // velocity information could be added here

        updateRect();

    }

    // all update calls eventually lead here - must relate to x and y co-ordinates

    public void updateRect ()

    {

        // rect.set((int)x, (int)y, width, height);


        // x will be offset and should be increased

        // Specify the center point and the arguments work out the points of the rect
        // Removed the scaling
        rect.set((int) (x - (width / 2)),
                (int) (y - (height / 2)),
                (int) (x + (width / 2)),
                (int) (y + (height / 2)));

    }

    // Actual drawing happens here

    public void draw (Canvas canvas)

    {
        if (sprite == null)

        {
            System.out.println("There is no sprite");
        }


        if (player)

        {

            // draw images in their normal orientation
            canvas.drawBitmap(sprite, null, rect, null);

        }

        // for flipped cards

        else

        {

            canvas.drawBitmap(sprite, source, rect, null);

        }


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
