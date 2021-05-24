package com.example.plutoacademy.view.adapter.experts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.ExpertsBooksListItemBinding;
import com.example.plutoacademy.service.model.Article;
import com.example.plutoacademy.service.model.books.Books;
import com.example.plutoacademy.view.callback.experts.OnClickExpertsCallback;

import java.util.List;
import java.util.Objects;

public class ExpertsBooksAdapter extends RecyclerView.Adapter<ExpertsBooksAdapter.ArticleViewHolder> {

    List<? extends Books> booksList;


    public void setBooksList(final List<? extends Books> booksList) {
        if (this.booksList == null) {
            this.booksList = booksList;
            notifyItemRangeInserted(0, booksList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ExpertsBooksAdapter.this.booksList.size();
                }

                @Override
                public int getNewListSize() {
                    return booksList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return Objects.equals(ExpertsBooksAdapter.this.booksList.get(oldItemPosition).getBuy(),
                            ExpertsBooksAdapter.this.booksList.get(newItemPosition).getBuy());
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
        ExpertsBooksListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.experts_books_list_item,
                        parent, false);
        binding.setCallback(new OnClickExpertsCallback());

        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.binding.setBooks(booksList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return booksList == null ? 0 : booksList.size();
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        final ExpertsBooksListItemBinding binding;

        public ArticleViewHolder(ExpertsBooksListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
