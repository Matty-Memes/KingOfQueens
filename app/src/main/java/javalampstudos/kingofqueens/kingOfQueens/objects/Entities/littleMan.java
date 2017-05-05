package javalampstudos.kingofqueens.kingOfQueens.objects.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import javalampstudos.kingofqueens.GameLoop;
import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.Animation;
import javalampstudos.kingofqueens.kingOfQueens.objects.Entity;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;
import javalampstudos.kingofqueens.kingOfQueens.util.GraphicsUtil;
import javalampstudos.kingofqueens.kingOfQueens.world.LayerViewport;
import javalampstudos.kingofqueens.kingOfQueens.world.ScreenViewport;

/**
 * Created by Nathan on 06/02/2017.
 */

public class littleMan extends Entity {

    public littleMan(float x, float y, float width, float height, Bitmap sprite, boolean player) {
        super(x, y, width, height, sprite, player);
    }



    public enum CollisionSide {
        LEFT, TOP, RIGHT, BOTTOM, NONE
    }

    private Animation animation = new Animation(sprite, 4);
    private Rect sourceRect = new Rect();




    public void draw(Canvas canvas, LayerViewport layerViewport, ScreenViewport screenViewport)

    {

        animation.getSourceRect(sourceRect);
        if (sprite == null)

        {
            System.out.println("There is no sprite");
        }


        else if (GraphicsUtil.getClippedSourceAndScreenRect(this, layerViewport, screenViewport, drawSourceRect, drawScreenRect)) {
            canvas.drawBitmap(sprite, sourceRect, drawScreenRect, null);
        }


    }


    public static CollisionSide collision (GameObject object1, ArrayList<GameObject> objects, GameLoop.MoveDirection moveDirection) {

        CollisionSide collisionSide = CollisionSide.NONE;

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i);


            switch (moveDirection) {

                case LEFT:
                    if (((object1.getLeft() - object1.width / 10) < objects.get(i).getRight()) &&
                            (object1.getRight() > objects.get(i).getLeft()) &&
                            (object1.getBottom() < objects.get(i).getTop()) &&
                            (object1.getTop() > objects.get(i).getBottom())) {
                        object1.x -= (object1.getLeft() - objects.get(i).getRight());
                        collisionSide = CollisionSide.LEFT;
                    }
                    break;

                case RIGHT:
                    if ((object1.getLeft() < objects.get(i).getRight()) &&
                            ((object1.getRight() + object1.width / 10) > objects.get(i).getLeft()) &&
                            (object1.getBottom() < objects.get(i).getTop()) &&
                            (object1.getTop() > objects.get(i).getBottom())) {
                        object1.x -= (object1.getRight() - objects.get(i).getLeft());
                        collisionSide = CollisionSide.RIGHT;
                    }
                    break;

                case DOWN:
                    if ((object1.getLeft() < objects.get(i).getRight()) &&
                            (object1.getRight() > objects.get(i).getLeft()) &&
                            ((object1.getBottom() - object1.width / 10) < objects.get(i).getTop()) &&
                            (object1.getTop() > objects.get(i).getBottom())) {
                        object1.y -= (object1.getBottom() - objects.get(i).getTop());
                        collisionSide = CollisionSide.BOTTOM;
                    }
                    break;


                case UP:
                    if (((object1.getLeft()) < objects.get(i).getRight()) &&
                            (object1.getRight() > objects.get(i).getLeft()) &&
                            (object1.getBottom() < objects.get(i).getTop()) &&
                            ((object1.getTop() + object1.width / 10) > objects.get(i).getBottom())) {
                        object1.y -= (object1.getTop() - objects.get(i).getBottom());
                        collisionSide = CollisionSide.TOP;
                    }
                    break;
            }
        }

        return collisionSide;
    }


    //Identify if the player is close enough to interact with an interactable GameObject
    public static int interactBounds (GameObject player, ArrayList<GameObject> objects, Entity tile) {

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i);

            if (((player.getLeft() - tile.width / 2) < objects.get(i).getRight()) &&
                    ((player.getRight() + tile.width / 2) > objects.get(i).getLeft()) &&
                    ((player.getBottom() - tile.width / 2) < objects.get(i).getTop()) &&
                    ((player.getTop() + tile.width / 2) > objects.get(i).getBottom())) {

                //return index of GameObject in the passed in ArrayList
                return i;
            }
        }

        //No interactable Game Object found, return no index
        return -1;
    }


    //Animation Frame update
    public void updateCurrentFrame(GameLoop.MoveDirection moveDirection) {
        animation.updateCurrentFrame(moveDirection);
    }




    /*public CollisionSide collision (GameObject object1, GameObject object2, GameLoop.MoveDirection moveDirection) {

        CollisionSide collisionSide = CollisionSide.NONE;

        switch(moveDirection) {

            case LEFT:
                if (((object1.getLeft() - 7) < object2.getRight()) &&
                        (object1.getRight() > object2.getLeft()) &&
                        (object1.getBottom() < object2.getTop()) &&
                        (object1.getTop() > object2.getBottom())) {
                    object1.x -= (object1.getLeft() - object2.getRight());
                    collisionSide = CollisionSide.LEFT;
                } break;

            case RIGHT:
                if ((object1.getLeft() < object2.getRight()) &&
                        ((object1.getRight() + 7) > object2.getLeft()) &&
                        (object1.getBottom() < object2.getTop()) &&
                        (object1.getTop() > object2.getBottom())) {
                    object1.x -= (object1.getRight() - object2.getLeft());
                    collisionSide = CollisionSide.RIGHT;
                } break;

            case DOWN:
                if ((object1.getLeft() < object2.getRight()) &&
                        (object1.getRight() > object2.getLeft()) &&
                        ((object1.getBottom() - 7) < object2.getTop()) &&
                        (object1.getTop() > object2.getBottom())) {
                    object1.y -= (object1.getBottom() - object2.getTop());
                    collisionSide = CollisionSide.BOTTOM;
                } break;


            case UP:
                if (((object1.getLeft()) < object2.getRight()) &&
                        (object1.getRight() > object2.getLeft()) &&
                        (object1.getBottom() < (object2.y + object2.height / 2)) &&
                        ((object1.getTop() + 7) > object2.getBottom())) {
                    object1.y -= (object1.getTop() - object2.getBottom());
                    collisionSide = CollisionSide.TOP;
                } break;
        }

        return collisionSide;
    }*/


}
