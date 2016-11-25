package javalampstudos.kingofqueens.kingOfQueens.Game;

import android.graphics.Bitmap;

/**
 * Created by brian on 24/11/2016.
 * 40111707
 */

public class BasicCard {
    private String name;
    private Bitmap bitmapImage;


    public BasicCard(Bitmap bitmapImage, String name) {
        this.bitmapImage = bitmapImage;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmapImage() {
        return bitmapImage;
    }

    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }
}
