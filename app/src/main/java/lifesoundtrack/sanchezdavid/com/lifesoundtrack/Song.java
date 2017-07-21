package lifesoundtrack.sanchezdavid.com.lifesoundtrack;

import java.io.Serializable;

/**
 * Created by davidsanchez on 12/7/17.
 */

public class Song implements Serializable {
    private long id;
    private String name;
    private String title;
    private String img;

    //Builder
    public Song(){}

    public Song(String name, String title){
        this(name,title,0,"");
    }

    public Song(String name, String title, long id){
        this(name,title,id,"");
    }

    public Song(String name, String title, long id, String img){
        this.id = id;
        this.name = name;
        this.title = title;
        this.img = img;
    }

    //Methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
