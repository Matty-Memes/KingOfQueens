package javalampstudos.kingofqueens.kingOfQueens.util;

import android.graphics.Bitmap;
import android.graphics.Rect;

import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;
import javalampstudos.kingofqueens.kingOfQueens.world.LayerViewport;
import javalampstudos.kingofqueens.kingOfQueens.world.ScreenViewport;

/**
 * Created by Nathan on 07/03/2017.
 */

public class GraphicsUtil {


    public static final boolean getClippedSourceAndScreenRect(GameObject gameObject, LayerViewport layerViewport,
                                                              ScreenViewport screenViewport, Rect sourceRect, Rect screenRect) {

        // Get the bounding box for the specified sprite
        //BoundingBox spriteBound = gameObject.getBound();

        // Determine if the sprite falls within the layer viewport
        if (gameObject.x - gameObject.width / 2 < layerViewport.x + layerViewport.width / 2 &&
                gameObject.x + gameObject.width / 2 > layerViewport.x - layerViewport.width / 2 &&
                gameObject.y - gameObject.height / 2 < layerViewport.y + layerViewport.height / 2 &&
                gameObject.y + gameObject.height / 2 > layerViewport.y - layerViewport.height / 2) {



            // Work out what region of the sprite is visible within the layer viewport,

            float sourceX = Math.max(0.0f,
                    (layerViewport.x - layerViewport.width / 2)
                            - (gameObject.x - gameObject.width / 2));
            float sourceY = Math.max(0.0f,
                    (gameObject.y + gameObject.height / 2)
                            - (layerViewport.y + layerViewport.height / 2));

            float sourceWidth = ((gameObject.width - sourceX) - Math
                    .max(0.0f, (gameObject.x + gameObject.width / 2)
                            - (layerViewport.x + layerViewport.width / 2)));
            float sourceHeight = ((gameObject.height - sourceY) - Math
                    .max(0.0f, (layerViewport.y - layerViewport.height / 2)
                            - (gameObject.y - gameObject.height / 2)));

            // Determining the scale factor for mapping the bitmap onto this
            // Rect and set the sourceRect value.

            Bitmap spriteBitmap = gameObject.getBitmap();

            float sourceScaleWidth = (float) spriteBitmap.getWidth()
                    / (gameObject.width);
            float sourceScaleHeight = (float) spriteBitmap.getHeight()
                    / (gameObject.height);

            sourceRect.set((int) (sourceX * sourceScaleWidth),
                    (int) (sourceY * sourceScaleHeight),
                    (int) ((sourceX + sourceWidth) * sourceScaleWidth),
                    (int) ((sourceY + sourceHeight) * sourceScaleHeight));

            // Determine =which region of the screen viewport (relative to the
            // canvas) we will be drawing to.

            // Determine the x- and y-aspect rations between the layer and screen viewports
            float screenXScale = (float) screenViewport.width / (layerViewport.width);
            float screenYScale = (float) screenViewport.height / (layerViewport.height);

            float screenX = screenViewport.left + screenXScale * Math.max(
                    0.0f,
                    (gameObject.x - (gameObject.width / 2)
                            - (layerViewport.x - layerViewport.width / 2)));
            float screenY = screenViewport.top + screenYScale * Math.max(
                    0.0f,
                    ((layerViewport.y + layerViewport.height / 2)
                            - (gameObject.y + gameObject.height / 2)));

            // Set the region to the canvas to which we will draw
            screenRect.set((int) screenX, (int) screenY,
                    (int) (screenX + sourceWidth * screenXScale),
                    (int) (screenY + sourceHeight * screenYScale));


            return true;
        }

        // Not visible
        return false;
    }
}
