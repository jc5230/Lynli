package com.innojane.lynli;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class SelectableCardsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Item> items;

    // An object of RecyclerView.RecycledViewPool is created to share the Views
    // between the child and the parent RecyclerViews
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public SelectableCardsAdapter() {
        this.items = new ArrayList<>();
    }

    public SelectableCardsAdapter(List<Item> items) {
        this.items = items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_item_brief_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Item item = items.get(position);
        ((ItemViewHolder) viewHolder).bindItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class Item {

        private String title;
        private List<String> avatarURLs;

        Item() {

        }

        Item(String title) {
            this.title = title;
        }

        Item(String title, List<String> avatarURLs) {
            this.title = title;
            this.avatarURLs = avatarURLs;
        }

        void addAvatarURL(String avatarURL) {
            avatarURLs.add(avatarURL);
        }

        String getTitle() {
            return title;
        }
        List<String> getAvatarURLs() {
            return avatarURLs;
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View briefView;
        private final TextView titleView;
        private final RecyclerView avatarRecyclerView;

        private AvatarAdapter avatarAdapter;

        ItemViewHolder(View itemView) {
            super(itemView);
            briefView = itemView.findViewById(R.id.card_brief_view);
            titleView = briefView.findViewById(R.id.card_title_text);
            avatarRecyclerView = briefView.findViewById(R.id.recyclerview_avatar);
        }

        private void bindItem(Item item) {
            titleView.setText(item.title);
            // Create a layout manager to assign a layout to the RecyclerView.
            LinearLayoutManager layoutManager = new LinearLayoutManager(
                    avatarRecyclerView.getContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false);
            // Since this is a nested layout, so to define how many child items
            // should be prefetched when the child RecyclerView is nested
            // inside the parent RecyclerView, we use the following method
            layoutManager.setInitialPrefetchItemCount(item.avatarURLs.size());
            avatarAdapter = new AvatarAdapter(item.avatarURLs);
            avatarRecyclerView.setLayoutManager(layoutManager);
            avatarRecyclerView.setAdapter(avatarAdapter);
            avatarRecyclerView.setRecycledViewPool(viewPool);
        }

    }
}
