package com.example.plutoacademy.view.ui.experts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.ActivityExpertsProfileBinding;
import com.example.plutoacademy.service.model.experts.ExpertsDetails;
import com.example.plutoacademy.view.adapter.experts.ExpertsBooksAdapter;
import com.example.plutoacademy.view.adapter.experts.ExpertsLinksAdapter;

import java.util.Objects;

public class ExpertsProfileActivity extends AppCompatActivity {


    private ExpertsLinksAdapter expertsLinksAdapter;
    private ExpertsBooksAdapter expertsBooksAdapter;

    private ActivityExpertsProfileBinding binding;
    private ExpertsProfileViewModel expertsProfileViewModel;

    private ExpertsDetails expertsDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        ActivityExpertsProfileBinding activityExpertsProfileBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_experts_profile);
        final ViewGroup view = activityExpertsProfileBinding.main;
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_experts_profile,
                view, false);
        expertsLinksAdapter = new ExpertsLinksAdapter();
        expertsBooksAdapter = new ExpertsBooksAdapter();
        binding.setIsLoading(true);

        String slug = (String) getIntent().getStringExtra("slug");
        ExpertsProfileViewModel.Factory factory = new ExpertsProfileViewModel.Factory(getApplication(), slug);
        expertsProfileViewModel = new ViewModelProvider(this, factory).get(ExpertsProfileViewModel.class);

        observeViewModel(expertsProfileViewModel);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_experts_profile);
//        expertsPodcastAdapter = new ExpertsPodcastAdapter();
//        binding.expertsPodcastList.setAdapter(expertsPodcastAdapter);
//
//        expertsBooksAdapter = new ExpertsBooksAdapter();
//        binding.expertsBooksList.setAdapter(expertsBooksAdapter);
//        expertsOthersAdapter = new ExpertsOthersAdapter();
//        binding.expertsOthersList.setAdapter(expertsOthersAdapter);
//        expertsSocialAdapter = new ExpertsSocialAdapter();
//        binding.expertsSocialList.setAdapter(expertsSocialAdapter);
//
//        expertsInterviewAdapter = new ExpertsInterviewAdapter();
//        binding.expertsInterviewList.setAdapter(expertsInterviewAdapter);
//        ExpertsViewModel.Factory factory = new ExpertsViewModel.Factory(getApplication(), 1);
//        final ExpertsViewModel expertsViewModel = new ViewModelProvider(this, factory)
//                .get(ExpertsViewModel.class);
//        binding.setIsLoading(true);
//        observeViewModel(expertsViewModel);
//        sectionsListener();
//        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ExpertsProfileActivity.super.onBackPressed();
//            }
//        });
    }

    private void observeViewModel(ExpertsProfileViewModel viewModel) {
        viewModel.getObservableProject().observe(this, new Observer<ExpertsDetails>() {
            @Override
            public void onChanged(ExpertsDetails expertsDetails) {
                if (expertsDetails != null) {
                    binding.setIsLoading(false);
//                    expertsLinksAdapter.setExpertsLinks(expertsDetails.getLinks());
//                    expertsBooksAdapter.setBooksList(expertsDetails.getBooks());
                } else {
                    Log.d("ExpertsDetails", "onChanged: ExpertsDetails");
                }
            }
        });
    }

//    private void observeViewModel(ExpertsViewModel viewModel) {
//        // Update the list when the data changes
//        viewModel.getObservableProject().observe(this, new Observer<Experts>() {
//            @Override
//            public void onChanged(@Nullable Experts experts) {
//                if (experts != null) {
//                    binding.setIsLoading(false);
//                    expertsSocialAdapter.setArticleList(experts.getMainImage());
//
//                    expertsBooksAdapter.setArticleList(experts.getArticles());
//                    expertsOthersAdapter.setArticleList(experts.getArticles());
//                    expertsPodcastAdapter.setArticleList(experts.getName());
//
//                    expertsInterviewAdapter.setArticleList(experts.getArticles());
//                } else {
//                    Log.d("Books", "onChanged: Books");
//                }
//            }
//        });
//    }
//
//    private void sectionsListener() {
//        final LinearLayout personalinfo, experience, review;
//        final TextView personalinfobtn, experiencebtn, reviewbtn;
//
//        personalinfo = findViewById(R.id.personalinfo);
//        experience = findViewById(R.id.experience1);
//        review = findViewById(R.id.review1);
//        personalinfobtn = findViewById(R.id.personalinfobtn);
//        experiencebtn = findViewById(R.id.experiencebtn);
//        reviewbtn = findViewById(R.id.reviewbtn);
//        /*making personal info visible*/
//        personalinfo.setVisibility(View.VISIBLE);
//        experience.setVisibility(View.GONE);
//        review.setVisibility(View.GONE);
//
//
//        personalinfobtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                personalinfo.setVisibility(View.VISIBLE);
//                experience.setVisibility(View.GONE);
//                review.setVisibility(View.GONE);
//                personalinfobtn.setTextColor(getResources().getColor(R.color.blue));
//                experiencebtn.setTextColor(getResources().getColor(R.color.grey));
//                reviewbtn.setTextColor(getResources().getColor(R.color.grey));
//
//            }
//        });
//
//        experiencebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                personalinfo.setVisibility(View.GONE);
//                experience.setVisibility(View.VISIBLE);
//                review.setVisibility(View.GONE);
//                personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
//                experiencebtn.setTextColor(getResources().getColor(R.color.blue));
//                reviewbtn.setTextColor(getResources().getColor(R.color.grey));
//
//            }
//        });
//
//        reviewbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                personalinfo.setVisibility(View.GONE);
//                experience.setVisibility(View.GONE);
//                review.setVisibility(View.VISIBLE);
//                personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
//                experiencebtn.setTextColor(getResources().getColor(R.color.grey));
//                reviewbtn.setTextColor(getResources().getColor(R.color.blue));
//
//            }
//        });
//    }

//    public void back() {
//        super.onBackPressed();
//    }

}