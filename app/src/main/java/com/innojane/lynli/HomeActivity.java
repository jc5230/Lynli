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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.innojane.lynli.SelectableCardsAdapter.Item;

public class HomeActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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