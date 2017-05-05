package javalampstudos.kingofqueens.kingOfQueens.util;

/**
 * Created by Andrew on 09/04/2017.
 */

// Local Imports

// Android Imports
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import javalampstudos.kingofqueens.GameLoop;
import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasFragment;


// Java Imports

public class Text

{
    // x and y co-ordinates
    public float x, y;
    // actual text
    public String text;
    // paint object for style
    public Paint paint;


    // Text Constructor
    public Text (float x, float y, String text, GameLoop loop)

    {
        this.x = x;
        this.y = y;
        this.text = text;

        // Initialize paint object

        paint = new Paint();

        // Need the asset manager for the font
        // relates to the paint stuff as well
        AssetManager assetManager = loop.fragment.getActivity().getAssets();

        paint.setTextAlign(Paint.Align.CENTER);
        // this needs to be scaled
        paint.setTextSize(23);
        paint.setColor(Color.WHITE);
        paint.setTypeface(paint.setTypeface(Typeface.createFromAsset(assetManager,
                "txt/OpenSans-BoldItalic.ttf")));

    }

    public void update()

    {


    }

    // method for drawing

    public void draw (Canvas canvas)

    {

        // the paint is always the same
        canvas.drawText(text, x, y, paint);

    }


}
