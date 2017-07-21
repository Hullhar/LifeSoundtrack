package lifesoundtrack.sanchezdavid.com.lifesoundtrack;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidsanchez on 12/7/17.
 */

public class SongParser {

    public static List<Song> parser(String data){
        List<Song> songList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(data);
            JSONObject object = null;
            Song song = new Song();
            for(int i = 0; i < array.length(); i++){
                object = array.getJSONObject(i);
                song.setId(object.getLong("id"));
                song.setName(object.getString("name"));
                song.setTitle(object.getString("title"));
                String urlImg = object.getString("img");
                if(urlImg.equalsIgnoreCase("")){
                    //Fijar img a fichero local
                    song.setImg("");
                } else {
                    song.setImg(urlImg);
                }
                songList.add(song);
            }
        } catch (JSONException e) {
            Log.d("getUserSongs","Not correct Json was received");
        }
        return songList;
    }
}
