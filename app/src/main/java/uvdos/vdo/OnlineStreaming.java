package uvdos.vdo;

/**
 * Created by on 9/22/2016.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
public class OnlineStreaming extends UrlProvider implements View.OnClickListener{
    ProgressDialog progressDialog;
    VideoView videoview;

    String VideoURL;


    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.streaming_video);

        Intent intent = getIntent();
        VideoURL = intent.getStringExtra("URI");

        videoview = (VideoView) findViewById(R.id.VideoView);

        progressDialog = new ProgressDialog(OnlineStreaming.this);

        progressDialog.setTitle("Streaming ...");

        progressDialog.setMessage("Streaming ...");

        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);

        progressDialog.show();

        try {
            MediaController mediaController = new MediaController(OnlineStreaming.this);
            mediaController.setAnchorView(videoview);
            Uri video = Uri.parse(VideoURL);
            videoview.setMediaController(mediaController);
            videoview.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();

        }
        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                videoview.start();
            }
        });
    }


    @Override
    public void onClick(View view) {

    }
}

