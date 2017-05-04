package javalampstudos.kingofqueens.kingOfQueens.world;

/**
 * Created by Nathan on 09/02/2017.
 */

public class ScreenViewport {

    public int left;


    public int top;


    public int right;


    public int bottom;


    public int width;


    public int height;

    public ScreenViewport(int left, int top, int right, int bottom) {

        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;

        width = right - left;
        height = bottom - top;
    }

}
