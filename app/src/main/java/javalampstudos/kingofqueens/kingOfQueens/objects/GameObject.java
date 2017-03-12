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

    // Source rect
    Rect source;
    // Destination rect
    Rect destination;



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



        // draw images in their normal orientation
        canvas.drawBitmap(sprite, null, rect, null);

        // need a seperate drawBitmap call for flipping images

        // SmallGeo is 213 x 300

        source = new Rect ();
        destination = new Rect ();

        source.set(432, 641, 0, 0);
        // second two must be greater than first two

        // combine this with the current method for drawing

        destination.set(50, 50, 150, 150);
        // Call to draw
        canvas.drawBitmap(sprite, source, destination, null);





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
