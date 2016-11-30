package javalampstudos.kingofqueens.kingOfQueens.engine.io;

/**
 * Created by 40083349 on 30/11/2016.
 */

import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

public class AssetLoader

{

    public static Bitmap loadBitmap(AssetManager bitmapManager,
                                    String bitmapPath) {
        Bitmap bitmap = null;
        InputStream bitmapInputStream = null;
        try {
            InputStream inputStream = bitmapManager.open(bitmapPath);
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bitmap = BitmapFactory.decodeStream(inputStream, null,
                    bitmapOptions);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(bitmapInputStream != null)
                try {
                    bitmapInputStream.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
        }

        return bitmap;
    }



}
