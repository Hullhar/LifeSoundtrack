package lifesoundtrack.sanchezdavid.com.lifesoundtrack;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LogInActivity extends AppCompatActivity {

    private Button button;
    private User user;
    private EditText userName, userPass;
    private final String RESTURL = "http://192.168.0.201:8080/getUser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        button = (Button) findViewById(R.id.buttonSingIn);
        userName = (EditText) findViewById(R.id.logUserName);
        userPass = (EditText) findViewById(R.id.logUserPass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DEBUG
                if (userName.getText().toString().equalsIgnoreCase("prueba")){
                    user = new User("Prueba", "0000", new ArrayList<Song>());
                    user.getSongList().add(new Song("U2","Cancion prueba",1,"http://akamai-b.cdn.cddbp.net/cds/2.0/cover/79FF/8415/2665/F04F_medium_front.jpg?cid=82148243"));
                    user.getSongList().add(new Song("Rolling","Cancion prueba",2,""));
                    user.getSongList().add(new Song("ACDC","Cancion prueba",3,""));
                    user.getSongList().add(new Song("U2","Cancion prueba",4,"http://akamai-b.cdn.cddbp.net/cds/2.0/cover/79FF/8415/2665/F04F_medium_front.jpg?cid=82148243"));
                    user.getSongList().add(new Song("Rolling","Cancion prueba",5,""));
                    user.getSongList().add(new Song("ACDC","Cancion prueba",6,"http://akamai-b.cdn.cddbp.net/cds/2.0/cover/79FF/8415/2665/F04F_medium_front.jpg?cid=82148243"));

                    Intent intent = new Intent(LogInActivity.this, MySongsActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);

                }
                // END DEBUG
                if (isOnline()){
                    if (!userName.getText().toString().isEmpty() && !userPass.getText().toString().isEmpty()){
                        String request = RESTURL + "?alias=" + userName.getText() + "&password=" + userPass.getText();
                        requestLogIn(request);
                    } else {
                        Toast.makeText(getApplicationContext(),"All parameters are required", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Try it later. Not Internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo conectivity = cm.getActiveNetworkInfo();
        if ( conectivity != null && conectivity.isConnectedOrConnecting()){
            return true;
        } else {
            return false;
        }
    }

    private void requestLogIn(String uri){
        MyTask backTask = new MyTask();
        backTask.execute(uri);
    }

    private void loadUser(String data){
       user = UserParser.parser(data);
    }

    private class MyTask extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            return HttpManager.getData(strings[0]);
        }

        @Override
        protected void onPostExecute(String data){
            super.onPostExecute(data);
            loadUser(data);
            if (user != null) {
                if (user.getAlias().equals("null")) {
                    Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(LogInActivity.this, MySongsActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Invalid user name", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
