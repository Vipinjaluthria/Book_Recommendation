package com.example.plutoacademy.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.HomeListItemBinding;
import com.example.plutoacademy.service.model.Article;
import com.example.plutoacademy.view.callback.OnClickHomeCallback;

import java.util.List;
import java.util.Objects;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ArticleViewHolder> {

    List<? extends Article> articleList;


    public void setArticleList(final List<? extends Article> articleList) {
        if (this.articleList == null) {
            this.articleList = articleList;
            notifyItemRangeInserted(0, articleList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return HomeAdapter.this.articleList.size();
                }

                @Override
                public int getNewListSize() {
                    return articleList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return Objects.equals(HomeAdapter.this.articleList.get(oldItemPosition).getUrl(),
                            HomeAdapter.this.articleList.get(newItemPosition).getUrl());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Article newArticle = articleList.get(newItemPosition);
                    Article oldArticle = articleList.get(oldItemPosition);
                    return Objects.equals(newArticle.getUrl(), oldArticle.getUrl())
                            && Objects.equals(oldArticle.getAuthor(), newArticle.getAuthor());
                }
            });
            this.articleList = articleList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HomeListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.home_list_item,
                        parent, false);

        binding.setCallback(new OnClickHomeCallback());

        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.binding.setArticle(articleList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return articleList == null ? 0 : articleList.size();
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        final HomeListItemBinding binding;

        public ArticleViewHolder(HomeListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
