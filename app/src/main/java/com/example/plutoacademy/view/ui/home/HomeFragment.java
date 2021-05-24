package com.example.plutoacademy.view.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.FragmentHomeBinding;
import com.example.plutoacademy.service.model.Blog;
import com.example.plutoacademy.view.adapter.HomeAdapter;

import static android.content.ContentValues.TAG;

public class HomeFragment extends Fragment {

    private HomeAdapter homeAdapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                container, false);
        homeAdapter = new HomeAdapter();
        binding.homeList.setAdapter(homeAdapter);
        binding.setIsLoading(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HomeViewModel.Factory factory = new HomeViewModel
                .Factory(requireActivity().getApplication());
        final HomeViewModel homeViewModel = new ViewModelProvider(this, factory)
                .get(HomeViewModel.class);
        binding.setIsLoading(true);
        observeViewModel(homeViewModel);
    }

    private void observeViewModel(HomeViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getObservableProject().observe(getViewLifecycleOwner(), new Observer<Blog>() {
            @Override
            public void onChanged(@Nullable Blog blog) {
                if (blog != null) {
                    binding.setIsLoading(false);
                    homeAdapter.setArticleList(blog.getArticles());
                } else {
                    Log.d("Home", "onChanged: Home");
                }
            }
        });
    }
}