package lifesoundtrack.sanchezdavid.com.lifesoundtrack;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by davidsanchez on 17/7/17.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    Context context;
    List<Song> userSongList;

    public SongAdapter(Context context, List<Song> userSongList){
        this.context = context;
        this.userSongList = userSongList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(userSongList.get(position).getTitle());
        holder.group.setText(userSongList.get(position).getName());
        String img = userSongList.get(position).getImg();
        if(img.equalsIgnoreCase("")){
            //Img from file
            holder.img.setImageResource(R.mipmap.ic_launcher);
        }else {
            //If we have an URL
            Picasso.with(holder.img.getContext()).load(img).into(holder.img);

        }
    }


    @Override
    public int getItemCount() {
        return userSongList.size();
    }

    public void setFilter(List<Song> filteredList){
        this.userSongList = filteredList;
        notifyDataSetChanged(); //Update if some change occurs
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {

        CardView cardView;
        TextView title;
        TextView group;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.mySongCardView);
            title = (TextView) itemView.findViewById(R.id.mySongTitleSong);
            group = (TextView) itemView.findViewById(R.id.mySongGroup);
            img = (ImageView) itemView.findViewById(R.id.mySongImg);
        }
    }

}
