package lifesoundtrack.sanchezdavid.com.lifesoundtrack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidsanchez on 12/7/17.
 */

public class User implements Serializable {

    private String alias;
    private String password;
    private List<Song> songList;

    //Builder
    public User(){};

    public User(String alias, String password){
        this(alias,password,null);
    }

    public User(String alias, String password, List<Song> songList){
        this.alias = alias;
        this.password = password;
        if(songList == null){
            this.songList = new ArrayList<Song>();
        } else {
            this.songList = songList;
        }
    }

    //Methods


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> list) {
        this.songList = list;
    }

}
