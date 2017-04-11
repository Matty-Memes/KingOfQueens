package javalampstudos.kingofqueens.kingOfQueens.oldCode.AndrewsOldCode;

import android.graphics.Canvas;

import javalampstudos.kingofqueens.kingOfQueens.util.Text;

/**
 * Created by brian on 11/04/2017.
 */

public class AndyManaCounter extends Text{


    private int counter;

    // Constructor
    public AndyManaCounter(float x, float y, String text)

    {
        super(x, y, text);
        // Counter always starts at 0
        counter = 0;

    }

    // adds to the counter and turns it into a string
    public void incrementCounter()

    {
        // counter++;
        text = counter + "";

    }


    public void draw(Canvas canvas)

    {
        // Call the drawText method in Text
        super.draw(canvas);

    }

}
