package uvdos.vdo;

/**
 * Created by on 9/21/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.MyViewHolder> {

    private ArrayList<VideoDetails> videoDetailsList = new ArrayList<VideoDetails>();
    Context context  ;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView videoName,videoSize;
        ImageView thumbNail;

        public MyViewHolder(View view) {
            super(view);
            videoName = (TextView) view.findViewById(R.id.name);
            videoSize = (TextView) view.findViewById(R.id.size);
            thumbNail = (ImageView)view.findViewById(R.id.thumbNail);
        }
    }


    public VideoAdapter(List<VideoDetails> videoDetailsList) {
        this.videoDetailsList = (ArrayList<VideoDetails>) videoDetailsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        VideoDetails videoDetails = videoDetailsList.get(position);
        holder.videoName.setText(videoDetails.getDisplayName());
        holder.videoSize.setText(videoDetails.getSize());



        //   Bitmap thumb = ThumbnailUtils.createVideoThumbnail(videoDetails.getData(),
        //       MediaStore.Images.Thumbnails.MINI_KIND);

        //      holder.thumbNail.setImageBitmap(thumb);

        String url = videoDetails.getData();

        Glide.with(holder.itemView.getContext())

                .load(videoDetails.getData())
                .centerCrop()
                .crossFade()
                .into(holder.thumbNail);
        //.onLoadCleared((Drawable) videoDetailsList);

    }
    @Override
    public int getItemCount() {
        return videoDetailsList.size();
    }


}

