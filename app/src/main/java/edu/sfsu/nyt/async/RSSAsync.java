package edu.sfsu.nyt.async;

import android.os.AsyncTask;

import edu.sfsu.nyt.io.FileIO;

public class RSSAsync {

    private FileIO io = null ;

    public RSSAsync() {
        new DownloadRSS().execute("https://rss.nytimes.com/services/xml/rss/nyt/Africa.xml");
    }

    public static class DownloadRSS extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            return "";
        }
    }

    public static class HttpAsync extends AsyncTask<String, Integer, String> {
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            io.downloadFile();

            /*
            OkHttpClient client  = new OkHttpClient();

            Request request = new Request.Builder().url(params[0]).build(); // params is an ArrayList Object

            Response response;

            try {
                response = client.newCall(request).execute();

                if(!response.isSuccessful())
                    return null;

                return response.body().string();
            } catch(IOException e) {
                e.printStackTrace();
                return null;
            }
            */
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String result) {

        }
    }
}