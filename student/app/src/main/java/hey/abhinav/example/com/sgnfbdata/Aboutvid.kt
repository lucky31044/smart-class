package hey.abhinav.example.com.sgnfbdata

import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView

class Aboutvid : AppCompatActivity() {

    // Create a VideoView variable, a MediaPlayer variable, and an int to hold the current
    // video position.
    private var videoBG: VideoView? = null
    internal var mMediaPlayer: MediaPlayer? = null
    internal var mCurrentVideoPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aboutvideo)

        // Hook up the VideoView to our UI.
        videoBG = findViewById(R.id.videoView) as VideoView

        // Build your video Uri
        val uri = Uri.parse("android.resource://" // First start with this,

                + packageName // then retrieve your package name,

                + "/" // add a slash,

                + R.raw.metor) // and then finally add your video resource. Make sure it is stored
        // in the raw folder.

        // Set the new Uri to our VideoView
        videoBG!!.setVideoURI(uri)
        // Start the VideoView
        videoBG!!.start()

        // Set an OnPreparedListener for our VideoView. For more information about VideoViews,
        // check out the Android Docs: https://developer.android.com/reference/android/widget/VideoView.html
        videoBG!!.setOnPreparedListener { mediaPlayer ->
            mMediaPlayer = mediaPlayer
            // We want our video to play over and over so we set looping to true.
            mMediaPlayer!!.isLooping = true
            // We then seek to the current posistion if it has been set and play the video.
            if (mCurrentVideoPosition != 0) {
                mMediaPlayer!!.seekTo(mCurrentVideoPosition)
                mMediaPlayer!!.start()
            }
        }
    }

    /*================================ Important Section! ================================
    We must override onPause(), onResume(), and onDestroy() to properly handle our
    VideoView.
     */

    override fun onPause() {
        super.onPause()
        // Capture the current video position and pause the video.
        mCurrentVideoPosition = mMediaPlayer!!.currentPosition
        videoBG!!.pause()
    }

    override fun onResume() {
        super.onResume()
        // Restart the video when resuming the Activity
        videoBG!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        // When the Activity is destroyed, release our MediaPlayer and set it to null.
        mMediaPlayer!!.release()
        mMediaPlayer = null
    }

}
