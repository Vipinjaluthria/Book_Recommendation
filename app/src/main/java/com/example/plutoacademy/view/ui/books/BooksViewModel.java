package com.example.plutoacademy.view.ui.books;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.plutoacademy.service.model.books.*;
import com.example.plutoacademy.service.repository.BooksRepository;
import com.example.plutoacademy.view.ui.experts.ExpertsViewModel;

import org.jetbrains.annotations.NotNull;

public class BooksViewModel extends ViewModel {

//    private final LiveData<Blog> blogLiveData;
//    public ObservableField<Blog> blog = new ObservableField<>();

    private LiveData<BooksList> booksListLiveData;
    public ObservableField<BooksList> booksList = new ObservableField<>();

    private LiveData<BooksSearch> booksSearchLiveData;
    public ObservableField<BooksSearch> booksSearch = new ObservableField<>();

    BooksRepository booksRepository;

    public BooksViewModel(@NonNull Application application, int page) {
        super();
//        this.blogLiveData = BlogRepository.getInstance().getBlog("techcrunch");
        booksRepository = BooksRepository.getInstance();
        this.booksListLiveData = booksRepository.getBooksList(page, 24);
    }

//    public LiveData<Blog> getObservableProject() {
//        return blogLiveData;
//    }

//    public void setBlog(Blog blog) {
//        this.blog.set(blog);
//    }

    public LiveData<BooksList> getObservableProject() {
        return booksListLiveData;
    }

    public void changeBooksList(int page) {
        this.booksListLiveData = booksRepository.appendBooksList(booksList.get(), page, 24);
    }

    public void getSearchBooksList(String val) {
        this.booksSearchLiveData = booksRepository.getBooksSearch(booksSearch.get(), val);
    }

    public void setBooksList(BooksList booksList) {
        this.booksList.set(booksList);
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
            return (T) new BooksViewModel(application, page);
        }
    }
}