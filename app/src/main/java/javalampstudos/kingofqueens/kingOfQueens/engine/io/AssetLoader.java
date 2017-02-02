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
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
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

    // Deals with sfx

    public static SoundPool loadSoundpool(AssetManager soundPoolManager,
                                          String soundPath) {

        SoundPool mSoundPool;
        AudioAttributes mAttributes;
        final int mMaxChannels = 20;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_GAME).build();

            mSoundPool = new SoundPool.Builder().setMaxStreams(mMaxChannels)
                    .setAudioAttributes(mAttributes).build();
        } else {
            mSoundPool = new SoundPool(mMaxChannels, AudioManager.STREAM_MUSIC,
                    0);
        }
        try {
            AssetFileDescriptor assetDescriptor = soundPoolManager
                    .openFd(soundPath);
            mSoundPool.load(assetDescriptor, 1);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return mSoundPool;
    }

    // Deals with music

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
