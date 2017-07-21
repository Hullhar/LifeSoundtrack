package lifesoundtrack.sanchezdavid.com.lifesoundtrack;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MySongsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private User user;
    private RecyclerView recyclerView;
    private List<Song> userSongList;
    private SongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_songs);
        Bundle extras = getIntent().getExtras();
        user = (User)extras.getSerializable("user");
        userSongList = user.getSongList();
        recyclerView = (RecyclerView) findViewById(R.id.mySongRecycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SongAdapter(this,userSongList);
        recyclerView.setAdapter(adapter);

    }

    /**
     * Creates a menu when the activity is launched
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.searcher_menu,menu);
        MenuItem item = menu.findItem(R.id.searcherItem);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                //If searchView is empty, show all elements in the RecyclerView
                adapter.setFilter(userSongList);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Song> filteredList = filter(userSongList,newText);
        adapter.setFilter(filteredList);
        return false;
    }

    private List<Song> filter(List<Song> list, String filterText){
        List<Song> filteredList = new ArrayList<>();
        filterText = filterText.toLowerCase();
        for(Song song : list){
            if(song.getTitle().toLowerCase().contains(filterText) || song.getName().toLowerCase().contains(filterText))
                filteredList.add(song);
        }
        return filteredList;
    }
}
