package javalampstudos.kingofqueens.kingOfQueens.util;

/**
 * Created by 40083349 on 14/03/2017.
 */

// Android Imports
import android.graphics.Paint;
import android.graphics.Canvas;

// Local Imports
import javalampstudos.kingofqueens.GameLoop;


public class andyManaCounter extends Text

{

    // Counter variable
    public int counter;

    // Constructor
    public andyManaCounter(float x, float y, String text, GameLoop loop, boolean damage, boolean visible )

    {
        super(x, y, text, loop, damage, visible);
        // Counter always starts at 0
        counter = 0;

    }

    // adds to the counter and turns it into a string
    public void incrementCounter()

    {
        System.out.println("Here");
        counter++;
        text = counter + "";

    }


    public void draw(Canvas canvas)

    {
        // Call the drawText method in Text
        super.draw(canvas);

    }


}
