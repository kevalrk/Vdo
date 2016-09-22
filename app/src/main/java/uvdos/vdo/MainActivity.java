package uvdos.vdo;

import android.Manifest;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<VideoDetails> videoDetailsList = new ArrayList<>();
    private RecyclerView recyclerView;
    VideoAdapter mAdapter;
    Cursor videoCursor;
    Context context;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (!Settings.System.canWrite(getApplicationContext())) {


                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

            }
        }

        //TODO handle recycle view
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new VideoAdapter(videoDetailsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // set the adapter
        recyclerView.setAdapter(mAdapter);

        prepareVideoData();

        //handle video play

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //  Handle item click

                        VideoDetails videoDetails = videoDetailsList.get(position);

                        Gson gson = new Gson();
                        Intent i = new Intent(MainActivity.this , UIPlayer.class );
                        i.putExtra("videoObject",gson.toJson(videoDetails));
                        i.putExtra("currentVideoIndex",String.valueOf(position));
                        i.putExtra("listSize",String.valueOf(videoDetailsList.size()));
                        i.putExtra("videoListObject",gson.toJson(videoDetailsList));
                        Log.d("Index", String.valueOf(position));
                        startActivity(i);

                    }
                })
        );

    }

    public void setSupportActionBar(Toolbar toolbar) {

    }


    private void prepareVideoData() {


        String[] projection = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE,

        };





        videoCursor = this.getContentResolver().query
                (MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                        projection,
                        null,
                        null,
                        null);


        while (videoCursor.moveToNext()) {
            VideoDetails videoDetails = new VideoDetails();
            videoDetails.setId(videoCursor.getInt(0));
            videoDetails.setData(videoCursor.getString(1));
            videoDetails.setDisplayName(videoCursor.getString(2));
            videoDetails.setSize(videoCursor.getString(3));
            videoDetailsList.add(videoDetails);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem =menu.findItem(R.id.search);

        //Implement Search Activity
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(searchItem);
        SearchManager searchManager= (SearchManager)getSystemService(SEARCH_SERVICE);
        ComponentName componentName=new ComponentName(this,SearchableActivity.class);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.stream:
                Intent i = new Intent(getApplicationContext(), UrlProvider.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


}
