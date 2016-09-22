package uvdos.vdo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by  on 9/21/2016.
 */
public class UIPlayer extends Activity {
    //static final int MIN_WIDTH=100;
    //private FrameLayout.LayoutParams mRootParam;
    private VideoView videoView;
    private MediaController mediaController;
    private MediaPlayer mediaPlayer;
    AudioManager audioManager;
    public String videoPath;
    public String videoName;
    public String uri;
    Uri myUri;
    ArrayList<VideoDetails> videoDetailsList = new ArrayList<VideoDetails>();
    int currentVideoIndex, videoListSize;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_ui);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);


        // TODO parse gson parameter
        Gson gson = new Gson();


        String videoObject = getIntent().getStringExtra("videoObject");
        VideoDetails videoDetails = gson.fromJson(videoObject, VideoDetails.class);

        String videoListObject = getIntent().getStringExtra("videoListObject");
        Type type = new TypeToken<ArrayList<VideoDetails>>() {
        }.getType();
        videoDetailsList = gson.fromJson(videoListObject, type);

        String currentIndex = getIntent().getStringExtra("currentVideoIndex");
        Log.d("Index", currentIndex);
        currentVideoIndex = Integer.parseInt(currentIndex);

        String listSize = getIntent().getStringExtra("listSize");
        videoListSize = Integer.parseInt(listSize);

        videoPath = videoDetails.getPath();
        videoName = videoDetails.getDisplayName();

        Toast.makeText(UIPlayer.this, String.valueOf(videoDetailsList.get(currentVideoIndex).getData()), Toast.LENGTH_LONG).show();

        PlayVideo(currentVideoIndex);
    }

    private void PlayVideo(int currentVideoIndex) {

        //TODO video play using media controller

        final ImageButton mlockbutton = (ImageButton) findViewById(R.id.btn_lock);
        videoView = (VideoView) findViewById(R.id.videoview);
        videoView.setMediaController((new android.widget.MediaController(this)));
        //uri = "content://media/external/video/media/"+ videoDetailsList.get(currentVideoIndex).getId();
        myUri = Uri.parse(videoDetailsList.get(currentVideoIndex).getData());
        videoView.setVideoURI(myUri);
        videoView.requestFocus();
        videoView.start();
    }




    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }


}
