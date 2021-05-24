package com.example.plutoacademy.view.adapter.experts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.ExpertsSocialListItemBinding;
import com.example.plutoacademy.service.model.experts.Links;
import com.example.plutoacademy.view.callback.experts.OnClickExpertsCallback;

import java.util.List;
import java.util.Objects;

public class ExpertsLinksAdapter extends RecyclerView.Adapter<ExpertsLinksAdapter.ArticleViewHolder> {

    List<? extends Links> expertsLinks;


    public void setExpertsLinks(final List<? extends Links> expertsLinks) {
        if (this.expertsLinks == null) {
            this.expertsLinks = expertsLinks;
            notifyItemRangeInserted(0, expertsLinks.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ExpertsLinksAdapter.this.expertsLinks.size();
                }

                @Override
                public int getNewListSize() {
                    return expertsLinks.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return Objects.equals(ExpertsLinksAdapter.this.expertsLinks.get(oldItemPosition).getLink(),
                            ExpertsLinksAdapter.this.expertsLinks.get(newItemPosition).getLink());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Links newLinks = expertsLinks.get(newItemPosition);
                    Links oldLinks = expertsLinks.get(oldItemPosition);
                    return Objects.equals(newLinks.getLink(), oldLinks.getLink())
                            && Objects.equals(oldLinks.getTitle(), newLinks.getTitle())
                            && Objects.equals(oldLinks.getLogo(), newLinks.getLogo());
                }
            });
            this.expertsLinks = expertsLinks;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ExpertsSocialListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.experts_social_list_item,
                        parent, false);
        binding.setCallback(new OnClickExpertsCallback());

        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.binding.setLinks(expertsLinks.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return expertsLinks == null ? 0 : expertsLinks.size();
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        final ExpertsSocialListItemBinding binding;

        public ArticleViewHolder(ExpertsSocialListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
