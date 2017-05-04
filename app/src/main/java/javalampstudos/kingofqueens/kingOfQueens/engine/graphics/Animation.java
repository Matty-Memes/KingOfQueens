package javalampstudos.kingofqueens.kingOfQueens.engine.graphics;

import android.graphics.Bitmap;
import android.graphics.Rect;

import javalampstudos.kingofqueens.GameLoop;

/**
 * Created by Nathan on 13/03/2017.
 */

public class Animation {

    /**
     * Bitmap holding the frames of this animation
     */
    private Bitmap animationFrames;

    public Bitmap getBitmap() {
        return animationFrames;
    }

    /**
     * Width and height of each frame of the animation
     */
    private int frameWidth;
    private int frameHeight;

    /**
     * Number of frames in the animation
     */
    private int frameCount;

    /**
     * Index of the current frame of animation
     */
    private int currentFrame = 0;


    public Animation(Bitmap animationFrames, int frameCount) {

        this.animationFrames = animationFrames;
        this.frameCount = frameCount;

        frameHeight = animationFrames.getHeight();
        frameWidth = animationFrames.getWidth() / frameCount;
    }

    public void updateCurrentFrame(GameLoop.MoveDirection moveDirection) {

        System.out.println(moveDirection);

        switch(moveDirection) {

            case DOWN: currentFrame = 0; break;
            case LEFT: currentFrame = 1; break;
            case RIGHT: currentFrame = 2; break;
            case UP: currentFrame = 3; break;
        }

    }


    public void getSourceRect(Rect sourceRect) {

        if(currentFrame >= 0)
            sourceRect.set(currentFrame * frameWidth, 0, currentFrame
                    * frameWidth + frameWidth, frameHeight);
    }

}
