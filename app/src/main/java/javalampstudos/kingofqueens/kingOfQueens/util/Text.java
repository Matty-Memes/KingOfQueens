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
import javalampstudos.kingofqueens.kingOfQueens.engine.graphics.CanvasFragment;


// Java Imports



public abstract class Text

{
    // x and y co-ordinates
    public float x, y;
    // actual text
    public String text;
    // paint object for style
    public Paint paint;


    // Text Constructor
    public Text (float x, float y, String text)

    {
        this.x = x;
        this.y = y;
        this.text = text;

        // Initialize paint object

        paint = new Paint();

        paint.setTextAlign(Paint.Align.LEFT);
        // this needs to be scaled
        paint.setTextSize(16);
        paint.setColor(Color.WHITE);


    }


    // method for updating - is this necessary??

    /*

    public void update()

    {


    }

    */

    // method for drawing

    public void draw (Canvas canvas)

    {

      // the paint is always the same
      canvas.drawText(text, x, y, paint);

    }


}
