package com.example.plutoacademy.view.adapter.books;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.RecommendersListItemBinding;
import com.example.plutoacademy.service.model.books.Recommenders;
import com.example.plutoacademy.view.callback.books.OnClickSourceCallback;

import java.util.List;
import java.util.Objects;

public class RecommenderAdapter extends RecyclerView.Adapter<RecommenderAdapter.RecommenderViewHolder> {

    List<? extends Recommenders> recommendersList;

    public void setRecommendersList(final List<? extends Recommenders> recommendersList) {
        if (this.recommendersList == null) {
            this.recommendersList = recommendersList;
            notifyItemRangeInserted(0, recommendersList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return RecommenderAdapter.this.recommendersList.size();
                }

                @Override
                public int getNewListSize() {
                    return recommendersList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return Objects.equals(RecommenderAdapter.this.recommendersList.get(oldItemPosition).getSource(),
                            RecommenderAdapter.this.recommendersList.get(newItemPosition).getSource());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Recommenders newRecommenders = recommendersList.get(newItemPosition);
                    Recommenders oldRecommenders = recommendersList.get(oldItemPosition);
                    return Objects.equals(newRecommenders.getSource(), oldRecommenders.getSource())
                            && Objects.equals(oldRecommenders.getExpert(), newRecommenders.getExpert())
                            && Objects.equals(oldRecommenders.getQuote(), newRecommenders.getQuote());
                }
            });
            this.recommendersList = recommendersList;
            result.dispatchUpdatesTo(this);
        }
    }


    @NonNull
    @Override
    public RecommenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecommendersListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.recommenders_list_item, parent, false);
        binding.setOnClickSource(new OnClickSourceCallback());
        return new RecommenderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommenderViewHolder holder, int position) {
        holder.binding.setRecommenders(recommendersList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {

        return recommendersList == null ? 0 : recommendersList.size();
    }

    static class RecommenderViewHolder extends RecyclerView.ViewHolder {

        final RecommendersListItemBinding binding;

        public RecommenderViewHolder(RecommendersListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
