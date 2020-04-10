package com.example.mvvmsample.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmsample.models.Place;
import com.example.mvvmsample.repository.PlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<Place>> listOfPlaces;
    private PlaceRepository placeRepository;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    public void init() {
        if (listOfPlaces != null) {
            return;
        } else {
            placeRepository = PlaceRepository.getInstance();
            listOfPlaces = placeRepository.getPlaces();
        }
    }

    public LiveData<List<Place>> getPlacesList() {
        return listOfPlaces;
    }

    public void addNewPlace(final Place place) {
        isUpdating.setValue(true);
        new AsyncTask<Object, Object, Object>() {
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                List<Place> placeList = listOfPlaces.getValue();
                placeList.add(place);
                isUpdating.postValue(false);
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<Boolean> isLoading(){
        return  isUpdating;
    }
}
