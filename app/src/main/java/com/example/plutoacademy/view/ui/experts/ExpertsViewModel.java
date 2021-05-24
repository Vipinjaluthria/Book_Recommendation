package com.example.plutoacademy.view.ui.experts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.plutoacademy.service.model.Blog;
import com.example.plutoacademy.service.model.books.BooksList;
import com.example.plutoacademy.service.model.experts.*;
import com.example.plutoacademy.service.repository.BlogRepository;
import com.example.plutoacademy.service.repository.BooksRepository;
import com.example.plutoacademy.service.repository.ExpertsRepository;

import org.jetbrains.annotations.NotNull;

public class ExpertsViewModel extends ViewModel {

//    private final LiveData<Blog> blogLiveData;
//    public ObservableField<Blog> blog = new ObservableField<>();

    private LiveData<ExpertsList> expertsListLiveData;
    public ObservableField<ExpertsList> expertsList = new ObservableField<>();

    private LiveData<ExpertsSearch> expertsSearchLiveData;
    public ObservableField<ExpertsSearch> expertsSearch = new ObservableField<>();

    ExpertsRepository expertsRepository;

    public ExpertsViewModel(@NonNull Application application, int page) {
        super();
//        this.blogLiveData = BlogRepository.getInstance().getBlog("techcrunch");
        expertsRepository = ExpertsRepository.getInstance();
        this.expertsListLiveData = expertsRepository.getExpertsList(page, 24);
    }

//    public LiveData<Blog> getObservableProject() {
//        return blogLiveData;
//    }
//
//    public void setBlog(Blog blog) {
//        this.blog.set(blog);
//    }

    public LiveData<ExpertsList> getObservableProject() {
        return expertsListLiveData;
    }

    public void changeExpertsList(int page) {
        this.expertsListLiveData = expertsRepository.appendExpertsList(expertsList.get(), page, 24);
    }

    public void getSearchExpertsList(String val) {
        this.expertsSearchLiveData = expertsRepository.getExpertsSearch(expertsSearch.get(), val);
    }

    public void setExpertsList(ExpertsList expertsList) {
        this.expertsList.set(expertsList);
    }



    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;
        private final int page;

        public Factory(@NonNull Application application, int page) {
            this.page = page;
            this.application = application;
        }

        @NotNull
        @Override
        public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ExpertsViewModel(application, page);
        }
    }
}