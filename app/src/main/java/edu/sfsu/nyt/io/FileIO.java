package edu.sfsu.nyt.io;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileIO {

    private final String URL_STRING = "https://rss.nytimes.com/services/xml/rss/nyt/Africa.xml";
    private final String FILENAME = "news_feed_africa.xml";
    private Context context = null;

    public FileIO(Context context) {
        this.context = context;
        Log.i("log", "FileIO Constructor - 0");
    }

    public void downloadFile() {
        try {
            URL url = new URL(URL_STRING);
            InputStream in = url.openStream();
            FileOutputStream out = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);

            Log.i("log", "FileInput " + out.toString());

            while(bytesRead != -1) {
                out.write(buffer, 0, bytesRead);
                bytesRead = in.read(buffer);
            }

            out.close();
            in.close();
        } catch (IOException e) {
            Log.e("News reader", e.toString());
        }
    }
}