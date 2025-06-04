package edu.sfsu.nyt.io;

import android.content.Context;
import android.util.Log;

import org.xml.sax.XMLReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import edu.sfsu.nyt.model.RSSFeedModel;

public class FileIO {

    private Context context = null;

    public FileIO(Context context) {
        this.context = context.getApplicationContext();
    }

    /**
     * The purpose of this method downloadRSSFile is to download the RSS to disk.
     */
    public void downloadRSSFile(String topic) {
        try {
            Log.i("log", "topic " + topic);
            URL url = new URL("https://rss.nytimes.com/services/xml/rss/nyt/" + topic + ".xml");
            InputStream in = url.openStream();

            String FILENAME = "news_feed_" + topic.toLowerCase() + ".xml";

            FileOutputStream out = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            byte[] buffer = new byte[1024];
            int bytesRead = in.read(buffer);

            Log.i("log", "FileInput " + out);

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

    public RSSFeedModel readFile() {
       try {
           SAXParserFactory factory = SAXParserFactory.newInstance();
           SAXParser parser = factory.newSAXParser();
           XMLReader xmlReader = parser.getXMLReader();

           // set content handler



           //RSSFeedModel feed = theRssHandler.getFeed();
           return null; // change this back to 'feed'
       } catch (Exception e) {
            Log.i("News reader", e.toString());
            return null;
       }
    }

   /*
    public RSSFeedModel readRSSFile() {
        try {
            RSSFeedModel feed = theRssHandler.getFeed();
            return feed;
        } catch (Exception e) {
        }
    }
    */
}