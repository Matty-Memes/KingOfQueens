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

import android.support.v4.app.Fragment;

// Local Imports

import javalampstudos.kingofqueens.GameLoop;
import javalampstudos.kingofqueens.kingOfQueens.objects.Entities.littleMan;
import javalampstudos.kingofqueens.kingOfQueens.util.GraphicsUtil;
import javalampstudos.kingofqueens.kingOfQueens.world.LayerViewport;
import javalampstudos.kingofqueens.kingOfQueens.world.ScreenViewport;

// Java Imports
import java.util.ArrayList;

// Test flipping images
import javalampstudos.kingofqueens.kingOfQueens.engine.io.AssetLoader;

public abstract class GameObject

{

    public float x, y;
    public float width, height;
    // sprites and bitmaps are interchangeable
    public Bitmap sprite;
    // Drawing information
    public Rect rect;

    protected Rect drawSourceRect = new Rect();
    protected Rect drawScreenRect = new Rect();

    ArrayList<GameObject> collisionList = new ArrayList<GameObject>();

    boolean check = true;

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

    public GameObject(float x, float y, float width, float height,
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

    public float getLeft() { return x - width / 2; }

    public float getRight() { return x + width / 2; }

    public float getTop() { return y + height / 2; }

    public float getBottom() { return y - width / 2; }


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

    public void drawOpenWorld(Canvas canvas, LayerViewport layerViewport, ScreenViewport screenViewport)

    {
        if (sprite == null)

        {
            System.out.println("There is no sprite");
        }


        else if (GraphicsUtil.getClippedSourceAndScreenRect(this, layerViewport, screenViewport, drawSourceRect, drawScreenRect)) {

            //System.out.println(check);
            /*if (check) {
                collisionList.add(this);
                //System.out.println(this);
                check = false;
            } else {
                for (int i = 0; i < collisionList.size(); i++) {
                    if(this != collisionList.get(i)) {
                        collisionList.add(this);
                    }
                }
            }
            System.out.println(this);*/


            canvas.drawBitmap(sprite, drawSourceRect, drawScreenRect, null);
            //System.out.println(collisionList.size());

            //littleMan.collision();

        } /*else for (int i = 0; i < collisionList.size(); i++) {
            if (this.equals(collisionList.get(i))) {
                collisionList.remove(i);
                System.out.println("Working");
            }
        }*/
        //System.out.println(collisionList.size());
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

    public Bitmap getBitmap() {
        return sprite;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof GameObject)) return false;
        GameObject o = (GameObject) obj;
        return o.x == this.x;
    }

}
