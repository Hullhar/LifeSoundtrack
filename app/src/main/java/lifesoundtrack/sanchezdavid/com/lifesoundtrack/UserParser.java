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

public class UserParser {

     public static User parser(String data){
         User user = null;
         if (!data.isEmpty()) {
             try {
                 JSONObject object = new JSONObject(data);
                 user = new User();
                 user.setAlias(object.getString("alias"));
                 user.setPassword(object.getString("password"));
                 JSONArray songs = object.getJSONArray("songList");
                 List<Song> songList = new ArrayList<>();
                 for(int i = 0; i < songs.length(); i++){
                     JSONObject jsonSong = songs.getJSONObject(i);
                     Song song =  new Song (jsonSong.getString("name"), jsonSong.getString("title"), jsonSong.getLong("id"));
                     String urlImg = jsonSong.getString("img");
                     if(urlImg.equalsIgnoreCase("")){
                         //Fijar img a fichero local
                         song.setImg("");
                     } else {
                         song.setImg(urlImg);
                     }
                     songList.add(song);
                 }
                 user.setSongList(songList);

             } catch (JSONException e) {
                 Log.d("getUser", "Not correct Json was received");
             }
         }

         return user;
    }
}
