package com.innojane.lynli;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class SelectableCardsAdapter extends RecyclerView.Adapter<SelectableCardsAdapter.ItemViewHolder> {
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
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_item_brief_view, parent, false);
        return new ItemViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position) {
        Item item = items.get(position);
        viewHolder.bindItem(item);
        viewHolder.expandCardView(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class Item {

        private String title;
        private List<String> avatarURLs;

        // Expanded Information
        private String bgURL;
        private String address;

        Item() {
        }

        Item(String title) {
            this.title = title;
        }

        Item(String title, List<String> avatarURLs) {
            this.title = title;
            this.avatarURLs = avatarURLs;
        }

        void setBgURL(String bgURL) {
            this.bgURL = bgURL;
        }

        void setAddress(String address) {
            this.address = address;
        }

        void addAvatarURL(String avatarURL) {
            avatarURLs.add(avatarURL);
        }

        boolean hasBg() {
            return (bgURL == null || bgURL.isEmpty())  ? false : true;
        }

        boolean hasAddress() {
            return (address == null || address.isEmpty()) ? false : true;
        }

        String getBgURL() {
            return bgURL;
        }

        String getTitle() {
            return title;
        }

        String getAddress() {
            return address;
        }

        List<String> getAvatarURLs() {
            return avatarURLs;
        }


    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        private final View briefView;
        private final TextView titleView;
        private final RecyclerView avatarRecyclerView;

        // Expanded
        private final ImageView bgView;
        private final TextView addressView;
        private final ImageButton rightButton;

        private AvatarAdapter avatarAdapter;

        ItemViewHolder(View itemView) {
            super(itemView);
            briefView = itemView.findViewById(R.id.card_brief_view);
            titleView = briefView.findViewById(R.id.card_title_text);
            avatarRecyclerView = briefView.findViewById(R.id.recyclerview_avatar);

            // Expanded
            bgView = briefView.findViewById(R.id.card_bgimage);
            addressView = briefView.findViewById(R.id.card_address_text);
            rightButton = briefView.findViewById(R.id.card_button_right);

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

            if (item.hasBg()) {
                bgView.setVisibility(View.VISIBLE);
                Glide.with(bgView.getContext())
                        .load(item.bgURL)
                        .placeholder(R.drawable.background)
                        .error(R.drawable.background)
                        .into(bgView);
            }
            if (item.hasAddress()) {
                addressView.setText(item.address);
            }
        }

        private void expandCardView(Item item) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (addressView.getVisibility() == View.GONE && item.hasAddress()) {
                        addressView.animate().alpha(1.0f);
                        addressView.setVisibility(View.VISIBLE);
                        rightButton.setVisibility(View.VISIBLE);
                    } else {
                        addressView.setVisibility(View.GONE);
                        rightButton.setVisibility(View.GONE);
                    }
                }
            });
        }

    }
}
