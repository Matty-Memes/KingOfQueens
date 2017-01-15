package javalampstudos.kingofqueens.kingOfQueens.engine.io;

/**
 * Created by 40083349 on 11/01/2017.
 */

import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
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

    public static MediaPlayer loadMusic(AssetManager musicManager,
                                        String musicPath) {
        // Create a new media player and try to load/prep the music clip
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            // Create a suitable file descriptor to the music clip

            AssetFileDescriptor musicDescriptor = musicManager
                    .openFd(musicPath);

            // Get the media player ready to play the music clip
            mediaPlayer.setDataSource(musicDescriptor.getFileDescriptor(),
                    musicDescriptor.getStartOffset(),
                    musicDescriptor.getLength());
            mediaPlayer.setLooping(true);
            mediaPlayer.prepare();

            // Indicate that the media is available to be played
        } catch(IOException e) {
            e.printStackTrace();
        }

        return mediaPlayer;
    }


}
