package javalampstudos.kingofqueens.kingOfQueens.util;

/**
 * Created by 40083349 on 14/03/2017.
 */

// Android Imports
import android.graphics.Paint;
import android.graphics.Canvas;



// Local Imports




public class andyManaCounter extends Text

{

    // Counter variable
    public int counter;

    // Constructor
    public andyManaCounter(float x, float y, String text)

    {
      // Call to superclass constructor
      super(x, y, text);
      // counter should always start at 0 and then be incremented
      counter = 0;

    }

    // adds to the counter and turns it into a string
    public void incrementCounter()

    {
      counter++;
      text = counter + "";

    }


    public void draw(Canvas canvas)

    {
       // Call the drawText method in Text
       super.draw(canvas);

    }




    //



}
