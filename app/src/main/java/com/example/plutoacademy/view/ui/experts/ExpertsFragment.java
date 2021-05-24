package com.example.plutoacademy.view.ui.experts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.example.plutoacademy.databinding.FragmentExpertsBinding;
import com.example.plutoacademy.service.model.Blog;
import com.example.plutoacademy.service.model.experts.ExpertsList;
import com.example.plutoacademy.view.adapter.experts.ExpertsAdapter;
import com.example.plutoacademy.view.ui.books.BooksViewModel;

public class ExpertsFragment extends Fragment {

    private ExpertsAdapter expertsAdapter;
    private FragmentExpertsBinding binding;

    ExpertsViewModel expertsViewModel;
    private int page = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_experts,
                container, false);
        expertsAdapter = new ExpertsAdapter();
        binding.expertsList.setAdapter(expertsAdapter);
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
        ExpertsViewModel.Factory factory = new ExpertsViewModel.Factory(requireActivity().getApplication(), page);
        expertsViewModel = new ViewModelProvider(this, factory).get(ExpertsViewModel.class);
        binding.setIsLoading(true);
        observeViewModel(expertsViewModel);

        // Search
        binding.search.setActivated(true);
        binding.search.setQueryHint("Type your keyword here");
        binding.search.onActionViewExpanded();
        binding.search.setIconified(false);
        binding.search.clearFocus();
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                expertsAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void observeViewModel(ExpertsViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getObservableProject().observe(getViewLifecycleOwner(), new Observer<ExpertsList>() {
            @Override
            public void onChanged(@Nullable ExpertsList expertsList) {
                if (expertsList != null) {
                    binding.setIsLoading(false);
                    expertsAdapter.setExpertsList(expertsList.getExperts());
                    expertsViewModel.setExpertsList(expertsList);
                    binding.expertsList.addOnScrollListener(new com.example.plutoacademy.view.util.EndlessRecyclerOnScrollListener() {
                        @Override
                        public void onLoadMore() {
                            Log.d("Books", "onChanged: onLoadMore: Reached the end!!!");
                            binding.expertsList.post(new Runnable() {
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
                            binding.expertsList.post(new Runnable() {
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
                    Log.d("Experts", "onChanged: Experts");
                }
            }
        });
    }

    private void addDataToList() {
        expertsViewModel.changeExpertsList(page);
        expertsAdapter.notifyDataSetChanged();
    }

    private void getSearchBooksList(String val) {
        expertsViewModel.getSearchExpertsList(val);
        expertsAdapter.notifyDataSetChanged();
    }

}