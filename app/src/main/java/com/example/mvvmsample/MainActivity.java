package com.example.mvvmsample;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmsample.adapters.PlaceAdapter;
import com.example.mvvmsample.models.Place;
import com.example.mvvmsample.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabActBtn;
    private PlaceAdapter placeAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabActBtn = findViewById(R.id.fab);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.init();
        mainActivityViewModel.getPlacesList().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                placeAdapter.notifyDataSetChanged();
            }
        });
        mainActivityViewModel.isLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean == true) {
                    showProgress();
                } else {
                    hideProgressBar();
                    recyclerView.smoothScrollToPosition(mainActivityViewModel.getPlacesList().getValue().size() - 1);
                }
            }
        });
        fabActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.addNewPlace(new Place("hello", "https://specials-images.forbesimg.com/imageserve/5e1fce48a854780006e8a0b7/960x0.jpg?fit=scale"));
            }
        });
    initRecyclerView();

    }

    private void initRecyclerView() {
        placeAdapter = new PlaceAdapter(this, mainActivityViewModel.getPlacesList().getValue());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(placeAdapter);
    }

    private void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
