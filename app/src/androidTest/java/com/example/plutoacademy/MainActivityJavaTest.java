package com.example.plutoacademy;

import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import com.example.plutoacademy.view.adapter.HomeAdapter;
import com.example.plutoacademy.view.ui.home.HomeFragment;
import com.example.plutoacademy.view.ui.home.HomeViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.mockito.Mock;

import java.util.List;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityJavaTest {

    @Mock
    private HomeViewModel homeViewModel;

    @Mock
    private HomeAdapter homeAdapter;

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureFrameLayoutIsPresent() throws Exception {

        MainActivity activity = rule.getActivity();
        FrameLayout viewById = activity.findViewById(R.id.nav_host_fragment);
        assertThat(viewById, instanceOf(FrameLayout.class));

        List<Fragment> fragmentList = activity.getSupportFragmentManager().getFragments();
        assertEquals(fragmentList.size(), 1);
        Fragment fragment = (Fragment) fragmentList.get(0);
        assertThat(fragmentList.get(0), instanceOf(HomeFragment.class));
        fragment = (HomeFragment) fragment;

        // fragment testing
        View view = fragment.getView();
        View recyclerView = view.findViewById(R.id.home_list);
        assertThat(recyclerView, instanceOf(RecyclerView.class));
        assertEquals(view.findViewById(R.id.loading_projects).getVisibility(), View.VISIBLE);
        recyclerView = (RecyclerView) recyclerView;

        homeViewModel = ViewModelProviders.of(activity, new HomeViewModel.Factory(activity.getApplication()))
                .get(HomeViewModel.class);

        assertEquals(view.findViewById(R.id.loading_projects).getVisibility(), View.VISIBLE);
        homeAdapter = (HomeAdapter) ((RecyclerView) recyclerView).getAdapter();

        int count = homeAdapter.getItemCount();
        assertThat(count, greaterThan(0));

    }
}
