package com.example.mvvmsample.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmsample.models.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceRepository {
    private static PlaceRepository instance;
    private ArrayList<Place> listOfPlaces = new ArrayList<>();

    public static PlaceRepository getInstance() {
        if (instance == null) {
            instance = new PlaceRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Place>> getPlaces() {
        setListOfPlaces();
        MutableLiveData<List<Place>> places = new MutableLiveData<>();
        places.setValue(listOfPlaces);
        return places;
    }

    private void setListOfPlaces() {
        listOfPlaces.add(new Place("pic unu", "https://images.unsplash.com/photo-1526047932273-341f2a7631f9?ixlib=rb-1.2" + ".1&ixid" +
                "=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"));
        listOfPlaces.add(new Place("pic doi", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/types-of-flowers-1579719085.jpg?crop=0" +
                ".671xw:1.00xh;0.189xw,0&resize=640:*"));
        listOfPlaces.add(new Place("pic trei", "https://images.unsplash.com/photo-1533907650686-70576141c030?ixlib=rb-1.2" +
                ".1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"));
    }
}
