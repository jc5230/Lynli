package com.innojane.lynli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bumptech.glide.Glide;
import com.innojane.lynli.SelectableCardsAdapter.Item;

public class HomeActivity extends AppCompatActivity{

    private AvatarAdapter avatarAdapter;
    private RecyclerView avatarRecyclerView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        avatarRecyclerView = findViewById(R.id.recyclerview_avatar);

        List<String> urls = new ArrayList<>();
        urls.add("https://raw.githubusercontent.com/jc5230/Lynli/main/app/sampledata/sampleavatar/avatar_1.png");
        urls.add("https://raw.githubusercontent.com/jc5230/Lynli/main/app/sampledata/sampleavatar/avatar_2.png");

        avatarAdapter = new AvatarAdapter(HomeActivity.this, urls);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        avatarRecyclerView.setLayoutManager(layoutManager);
        avatarRecyclerView.setAdapter(avatarAdapter);

    }

}
/*
public class HomeActivity extends AppCompatActivity implements ActionMode.Callback {

    private ActionMode actionMode;
    private RecyclerView recyclerView;
    private SelectableCardsAdapter adapter;
    private SelectionTracker<Long> selectionTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = recyclerView.findViewById(R.id.recycler_view_home);
        setUpRecyclerView(recyclerView);
    }

    protected void setUpRecyclerView(RecyclerView recyclerView) {
        adapter = new SelectableCardsAdapter();
        adapter.setItems(generateItems());

        recyclerView.setAdapter(adapter);

        selectionTracker =
                new SelectionTracker.Builder<>(
                        "card_selection",
                        recyclerView,
                        new SelectableCardsAdapter.KeyProvider(adapter),
                        new SelectableCardsAdapter.DetailsLookup(recyclerView),
                        StorageStrategy.createLongStorage())
                        .withSelectionPredicate(SelectionPredicates.createSelectAnything())
                        .build();

        adapter.setSelectionTracker(selectionTracker);
        selectionTracker.addObserver(
                new SelectionTracker.SelectionObserver<Long>() {
                    @Override
                    public void onSelectionChanged() {
                        if (selectionTracker.getSelection().size() > 0) {
                            if (actionMode == null) {
                                actionMode = startSupportActionMode(HomeActivity.this);
                            }
                            actionMode.setTitle(String.valueOf(selectionTracker.getSelection().size()));
                        } else if (actionMode != null) {
                            actionMode.finish();
                        }
                    }
                });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Item> generateItems() {
        String titlePrefix = "Chen's Home";
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            items.add(
                    new Item(titlePrefix + " " + (i + 1), Arrays.asList(1, 2, 3)));
        }

        return items;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        selectionTracker.clearSelection();
        this.actionMode = null;
    }
}
*/