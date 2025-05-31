package edu.sfsu.nyt.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import edu.sfsu.nyt.databinding.FragmentHomeBinding;
import edu.sfsu.nyt.io.FileIO;
import edu.sfsu.nyt.model.RSSFeedModel;

public class HomeFragment extends Fragment {

    private FileIO io;
    private FragmentHomeBinding binding;
    private RSSFeedModel feedModel;

    /**
     * HomeFragment makes a request for data via an inner class DownloadRSS
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        io = new FileIO(getContext());

        new DownloadRSSFeed().execute();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Get the RSS file.
     */
    public class DownloadRSSFeed extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            io.downloadRSSFile(); // FileIO method
            return null;
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
}