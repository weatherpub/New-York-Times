package edu.sfsu.nyt.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import edu.sfsu.nyt.R;
import edu.sfsu.nyt.activity.DetailActivity;
import edu.sfsu.nyt.databinding.FragmentHomeBinding;
import edu.sfsu.nyt.io.FileIO;
import edu.sfsu.nyt.model.RSSFeedModel;

public class HomeFragment extends Fragment {

    private Handler handler;
    private HandlerThread handlerThread;
    private FileIO io;
    private FragmentHomeBinding binding;
    private RSSFeedModel feedModel;
    private ArrayList<String> feeds;
    private Button button;

    /**
     * HomeFragment makes a request for data via an inner class DownloadRSS
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("log", "HandlerThread 0");

        feeds = new ArrayList<>();

        handlerThread = new HandlerThread("WorkerThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());

        Log.i("log", "HandlerThread 1");

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        button = (Button) view.findViewById(R.id.btn_details);

        // Launch DetailActivity
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.i("log", "button.setOnclickListener(...)");
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                startActivity(intent);
            }
        });

        io = new FileIO(getContext()); // FileIO.java

        // world
        feeds.add("World");
        feeds.add("Africa");
        feeds.add("Americas");
        feeds.add("AsiaPacific");
        feeds.add("Europe");
        feeds.add("MiddleEast");
        // U.S
        feeds.add("U.S.");
        feeds.add("Education");
        feeds.add("Politics");
        feeds.add("Upshot");
        // Business
        feeds.add("Business");
        feeds.add("EnergyEnvironment");
        feeds.add("SmallBusiness");
        feeds.add("Economy");
        feeds.add("DealBook");
        feeds.add("MediaAdvertising");
        feeds.add("YourMoney");

        new DownloadRSSFeed().execute("Arts");
        /*
        "Business"
        "EnergyEnvironment"
        "SmallBusiness"
        "Economy"
        "DealBook"
        "MediaAdvertising"
        "YourMoney"
        "Technology"
        "PersonalTech"
        "Sports"
        "Baseball"
        "CollegeBasketball"
        "CollegeFootball"
        "Golf"
        "Hockey"
        "ProBasketball"
        "ProFootball"
        "Soccer"
        "Tennis"
        "Science"
        "Environment"
        "SpaceCosmos"
        "Health"
        "WellBlog"

        new DownloadRSSFeed().execute("Technology");
        new DownloadRSSFeed().execute("PersonalTech");
        new DownloadRSSFeed().execute("Business");
        */


        return view;
    }

    /**
     * Get the RSS file.
     */
    public class DownloadRSSFeed extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... param) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    io.downloadRSSFile(param[0]);
                }
            });
            //return null;
            return "Feed Downloaded";
        }

        //@Override
        protected void onPostExecute(String result) {
            Context context = getActivity();
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Read RSS file.
     */
    public class ReadRSSFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            // feedModel = io.readRSSFile();
            return null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handlerThread.quitSafely();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}