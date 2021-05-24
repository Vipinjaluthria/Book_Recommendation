package com.example.plutoacademy.view.ui.books;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.FragmentBooksBinding;
import com.example.plutoacademy.service.model.books.BooksList;
import com.example.plutoacademy.view.adapter.books.BooksAdapter;

public class BooksFragment extends Fragment {

    private BooksAdapter booksAdapter;
    private FragmentBooksBinding binding;

    BooksViewModel booksViewModel;
    private int page = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_books,
                container, false);
        booksAdapter = new BooksAdapter();
        binding.booksList.setAdapter(booksAdapter);
        binding.setIsLoading(true);

        // search

        binding.search.setActivated(true);
        binding.search.setQueryHint("Search Book");
        binding.search.onActionViewExpanded();
        binding.search.setIconified(false);
        binding.search.clearFocus();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BooksViewModel.Factory factory = new BooksViewModel.Factory(requireActivity().getApplication(), page);
        booksViewModel = new ViewModelProvider(this, factory).get(BooksViewModel.class);
        binding.setIsLoading(true);
        observeViewModel(booksViewModel);
    }

    private void observeViewModel(BooksViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getObservableProject().observe(getViewLifecycleOwner(), new Observer<BooksList>() {
            @Override
            public void onChanged(@Nullable BooksList booksList) {
                if (booksList != null) {
                    binding.setIsLoading(false);
                    booksAdapter.setBooksList(booksList.getBooks());
                    booksViewModel.setBooksList(booksList);
                    binding.booksList.addOnScrollListener(new com.example.plutoacademy.view.util.EndlessRecyclerOnScrollListener() {
                        @Override
                        public void onLoadMore() {
                            Log.d("Books", "onChanged: onLoadMore: Reached the end!!!");
                            binding.booksList.post(new Runnable() {
                                public void run() {
                                    Log.d("Books", "onChanged: onLoadMore: run()");
                                    page++;
                                    addDataToList();
                                }
                            });
                        }
                    });

                    binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(final String query) {
                            binding.booksList.post(new Runnable() {
                                public void run() {
                                    Log.d("Books", "onChanged: onQueryTextSubmit: run()  "
                                            .concat(query));
                                    getSearchBooksList(query);
                                }
                            });
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            Log.d("Books", "onChanged: onQueryTextChange");
                            return false;
                        }
                    });
                } else {
                    Log.d("Books", "onChanged: Books");
                }
            }
        });
    }

    private void addDataToList() {
        booksViewModel.changeBooksList(page);
        booksAdapter.notifyDataSetChanged();
    }

    private void getSearchBooksList(String val) {
        booksViewModel.getSearchBooksList(val);
        booksAdapter.notifyDataSetChanged();
    }

}