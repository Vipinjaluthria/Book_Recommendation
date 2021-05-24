package com.example.plutoacademy.view.adapter.experts;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.ExpertListItemBinding;
import com.example.plutoacademy.service.model.experts.Experts;
import com.example.plutoacademy.view.callback.experts.OnClickExpertsCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpertsAdapter extends RecyclerView.Adapter<ExpertsAdapter.ArticleViewHolder> implements Filterable {

    ValueFilter valueFilter;
    List<? extends Experts> expertsList;

    public void setExpertsList(final List<? extends Experts> expertsList) {
        if (this.expertsList == null) {
            this.expertsList = expertsList;
            notifyItemRangeInserted(0, expertsList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ExpertsAdapter.this.expertsList.size();
                }

                @Override
                public int getNewListSize() {
                    return expertsList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return Objects.equals(ExpertsAdapter.this.expertsList.get(oldItemPosition).getSlug(),
                            ExpertsAdapter.this.expertsList.get(newItemPosition).getSlug());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Experts newExperts = expertsList.get(newItemPosition);
                    Experts oldExperts = expertsList.get(oldItemPosition);
                    return Objects.equals(newExperts.getSlug(), oldExperts.getSlug())
                            && Objects.equals(oldExperts.getDesignation(), newExperts.getDesignation());
                }
            });
            this.expertsList = expertsList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ExpertListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.expert_list_item,
                        parent, false);
        binding.setCallback(new OnClickExpertsCallback());
        return new ArticleViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
//        holder.binding.setArticle(articleList.get(position));
        holder.binding.setExperts(expertsList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
//        return articleList == null ? 0 : articleList.size();
        return expertsList == null ? 0 : expertsList.size();
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }


    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                List<Experts> filterList = new ArrayList<>(); // TODO check
                for (int i = 0; i < expertsList.size(); i++) {
                    if ((Objects.requireNonNull(expertsList.get(i).getName()).toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(expertsList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = expertsList.size();
                results.values = expertsList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            expertsList = (List<? extends Experts>) results.values;
            notifyDataSetChanged();
        }

    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        final ExpertListItemBinding binding;
        public ArticleViewHolder(ExpertListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
