package com.example.plutoacademy.view.ui.experts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.plutoacademy.service.model.experts.ExpertsDetails;
import com.example.plutoacademy.service.repository.ExpertsDetialsRepository;
import com.example.plutoacademy.service.repository.ExpertsRepository;

import org.jetbrains.annotations.NotNull;

public class ExpertsProfileViewModel extends ViewModel {

    private final LiveData<ExpertsDetails> expertsDetailsLiveData;

    ExpertsDetialsRepository expertsRepository;

    public ExpertsProfileViewModel(@NotNull Application application, String val) {
        super();
        expertsRepository = ExpertsDetialsRepository.getInstance();
        expertsDetailsLiveData = expertsRepository.getExpertsDetails(val);
    }

    public LiveData<ExpertsDetails> getObservableProject() {
        return expertsDetailsLiveData;
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;
        private final String val;

        public Factory(@NonNull Application application, String val) {
            this.application = application;
            this.val = val;
        }

        @NotNull
        @Override
        public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ExpertsProfileViewModel(application, val);
        }
    }

}
