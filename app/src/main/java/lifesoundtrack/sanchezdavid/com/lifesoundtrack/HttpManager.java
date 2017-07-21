package lifesoundtrack.sanchezdavid.com.lifesoundtrack;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by davidsanchez on 12/7/17.
 */

public class HttpManager {

    public static String getData(String uri)  {
        String content = "";
        HttpURLConnection connection = null;
        BufferedReader buffer = null;
        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line = buffer.readLine()) != null){
                content = line + "\n"; // It could be a problem
            }
        } catch (Exception ex){
            Log.d("getData","Problems with connection");
        }
        finally{
            try {
                if (buffer != null) {
                    buffer.close();
                }
                if (connection  != null){
                    connection.disconnect();
                }
            }catch (IOException ex) {
                Log.d("getData","Problems closing the buffer");
            }
        }
        return content;
    }
}
