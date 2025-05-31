package edu.sfsu.nyt.ui.home;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.sfsu.nyt.databinding.FragmentHomeBinding;
import edu.sfsu.nyt.io.FileIO;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private FileIO io;

    /**
     * HomeFragment makes a request for data via an inner class DownloadRSS
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        io = new FileIO(getContext());

        new DownloadRSS().execute();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class DownloadRSS extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            io.downloadFile(); // FileIO method
            return null;
        }
    }
}