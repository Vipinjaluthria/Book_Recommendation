package com.example.plutoacademy.view.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.plutoacademy.service.model.Blog;
import com.example.plutoacademy.service.repository.BlogRepository;

import org.jetbrains.annotations.NotNull;

public class HomeViewModel extends AndroidViewModel {

    private final LiveData<Blog> blogLiveData;
    public ObservableField<Blog> blog = new ObservableField<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.blogLiveData = BlogRepository.getInstance().getBlog("techcrunch");
    }

    public LiveData<Blog> getObservableProject() {
        return blogLiveData;
    }

    public void setBlog(Blog blog) {
        this.blog.set(blog);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @NotNull
        @Override
        public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new HomeViewModel(application);
        }
    }

}
