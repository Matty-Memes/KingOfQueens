package javalampstudos.kingofqueens.kingOfQueens.oldCode.NathansOldCode;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import javalampstudos.kingofqueens.GameLoop;
import javalampstudos.kingofqueens.kingOfQueens.objects.GameObject;
import javalampstudos.kingofqueens.kingOfQueens.world.LayerViewport;
import javalampstudos.kingofqueens.kingOfQueens.world.ScreenViewport;

/**
 * Created by Nathan- 40131544 on 11/03/2017.
 */

/*public class TileMap extends GameObject {


    float drawXOffset, drawYOffset = 0;
    float tileXOffset, tileYOffset;
    float drawWidth, drawHeight = 0;

    public TileMap (float x, float y, float width, float height, Bitmap sprite) {
        super(x, y, width, height, sprite);
    }

    public void draw(Canvas canvas, LayerViewport layerViewport, ScreenViewport screenViewport)

    {

        tileXOffset = layerViewport.x;
        tileYOffset = layerViewport.y;

        while(drawXOffset <  layerViewport.width) {
            drawYOffset = 0;
            tileYOffset = layerViewport.y;

            System.out.println("Working 1");

            while(drawYOffset < layerViewport.height) {
                System.out.println("Working 2");

                drawWidth = width - tileXOffset;
                if (drawWidth > layerViewport.width - drawXOffset) {
                    drawWidth = layerViewport.width - drawXOffset;
                    System.out.println("Working 3");
                }

                drawHeight = height - tileYOffset;
                if (drawHeight > layerViewport.height - drawYOffset) {
                    drawHeight = layerViewport.height - drawYOffset;
                    System.out.println("Working 4");
                }


                float sourceScaleWidth = (float) sprite.getWidth()
                        / width;
                float sourceScaleHeight = (float) sprite.getHeight()
                        / height;

                drawSourceRect.set((int) (tileXOffset * sourceScaleWidth),
                        (int) (tileYOffset * sourceScaleHeight),
                        (int) ((tileXOffset + drawWidth) * sourceScaleWidth),
                        (int) ((tileYOffset + drawHeight) * sourceScaleHeight));

                // Determine the x- and y-aspect rations between the layer and screen viewports
                float screenXScale = (float) screenViewport.width / (layerViewport.width);
                float screenYScale = (float) screenViewport.height / (layerViewport.height);

                float screenX = screenViewport.left + screenXScale * Math.max(
                        0.0f,
                        (x - (width / 2)
                                - (layerViewport.x - layerViewport.width / 2)));
                float screenY = screenViewport.top + screenYScale * Math.max(
                        0.0f,
                        ((layerViewport.y + layerViewport.height / 2)
                                - (y + height / 2)));

                // Set the region to the canvas to which we will draw
                drawScreenRect.set((int) screenX, (int) screenY,
                        (int) (screenX + drawWidth * screenXScale),
                        (int) (screenY + drawHeight * screenYScale));

                *//*drawSourceRect.set((int) tileXOffset, (int) tileYOffset, (int) (tileXOffset + drawWidth), (int) (tileYOffset + drawHeight));
                drawScreenRect.set((int) drawXOffset, (int) drawYOffset, (int) drawWidth, (int) drawHeight);*//*

                System.out.println("SourceRect: " + drawSourceRect);
                System.out.println("ScreenRect: " + drawScreenRect);

                canvas.drawBitmap(sprite, drawSourceRect, drawScreenRect, null);
                System.out.println("Draw");

                drawYOffset += drawHeight;
                tileYOffset = (tileYOffset + drawHeight) % height;
            }

            System.out.println("Working 5");


            System.out.println("TileX: " + tileXOffset);
            drawXOffset += drawWidth;
            tileXOffset = (tileXOffset + drawWidth) % width;
            System.out.println("TileX: " + tileXOffset);
        }

    }


    public void TestDraw(Canvas canvas, LayerViewport layerViewport, ScreenViewport screenViewport) {

        while (x + width < 500.f) {
            System.out.println("Working 1");

            while (y + height < 500.f) {
                System.out.println("Working 2");
                super.draw(canvas, layerViewport, screenViewport);
            }
        }

    }

    @Override
    public void update() {
        updateRect();
    }

    @Override
    public void updateRect() {

    }
}*/
