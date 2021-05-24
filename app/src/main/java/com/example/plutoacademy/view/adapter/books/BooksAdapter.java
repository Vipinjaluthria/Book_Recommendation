package com.example.plutoacademy.view.adapter.books;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.BooksListItemBinding;
import com.example.plutoacademy.service.model.Article;
import com.example.plutoacademy.service.model.books.*;
import com.example.plutoacademy.view.callback.books.OnClickBooksCallback;

import java.util.List;
import java.util.Objects;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ArticleViewHolder> {

    List<? extends Article> articleList;
    List<? extends Books> booksList;

    public void setArticleList(final List<? extends Article> articleList) {
        if (this.articleList == null) {
            this.articleList = articleList;
            notifyItemRangeInserted(0, articleList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return BooksAdapter.this.articleList.size();
                }

                @Override
                public int getNewListSize() {
                    return articleList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return Objects.equals(BooksAdapter.this.articleList.get(oldItemPosition).getUrl(),
                            BooksAdapter.this.articleList.get(newItemPosition).getUrl());
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

    public void setBooksList(final List<? extends Books> booksList) {
        if (this.booksList == null) {
            this.booksList = booksList;
            notifyItemRangeInserted(0, booksList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return BooksAdapter.this.booksList.size();
                }

                @Override
                public int getNewListSize() {
                    return booksList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return Objects.equals(BooksAdapter.this.booksList.get(oldItemPosition).getBuy(),
                            BooksAdapter.this.booksList.get(newItemPosition).getBuy());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Books newBooks = booksList.get(newItemPosition);
                    Books oldBooks = booksList.get(oldItemPosition);
                    return Objects.equals(newBooks.getBuy(), oldBooks.getBuy())
                            && Objects.equals(oldBooks.getAuthor(), newBooks.getAuthor());
                }
            });
            this.booksList = booksList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BooksListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.books_list_item,
                        parent, false);
        binding.setCallback(new OnClickBooksCallback());
        return new ArticleViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
//        holder.binding.setArticle(articleList.get(position));
        holder.binding.setBooks(booksList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
//        return articleList == null ? 0 : articleList.size();
        return booksList == null ? 0 : booksList.size();
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        final BooksListItemBinding binding;

        public ArticleViewHolder(BooksListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
