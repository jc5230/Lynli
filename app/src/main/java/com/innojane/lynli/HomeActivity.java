package com.innojane.lynli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.innojane.lynli.SelectableCardsAdapter.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeActivity extends AppCompatActivity{

    private AvatarAdapter avatarAdapter;
    private SelectableCardsAdapter selectableCardsAdapter;
    private RecyclerView avatarRecyclerView;
    private RecyclerView homeRecyclerView;
    private View briefView;

    public HomeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ConstraintLayout homeLayout = (ConstraintLayout) findViewById(R.id.activity_home);

        List<String> urls = new ArrayList<>();
        urls.add("https://raw.githubusercontent.com/jc5230/Lynli/main/app/sampledata/sampleavatar/avatar_1.png");
        urls.add("https://raw.githubusercontent.com/jc5230/Lynli/main/app/sampledata/sampleavatar/avatar_2.png");

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        Item item = new Item("Chen's Home", urls);
        List<Item> items = new ArrayList<>();
        items.add(item);
        homeRecyclerView = findViewById(R.id.recyclerview_home);
        selectableCardsAdapter = new SelectableCardsAdapter(generateItems());
        homeRecyclerView.setLayoutManager(layoutManager);
        homeRecyclerView.setAdapter(selectableCardsAdapter);

    }

    private List<Item> generateItems() {
        List<Item> items = new ArrayList<>();
        String[] names = new String[] {"James", "Robert", "John", "Michael", "William", "David", "Richard", "Joseph", "Thomas", "Charles"};
        Random rand = new Random();
        for (int i = 0; i < names.length; i++) {
            // set title
            String title = names[i] + "'s Home";
            // set number of members
            int randomMember = rand.nextInt(5) + 1;
            // set member avatar
            List<String> urls = new ArrayList<>();
            for (int j = 0; j < randomMember; j++) {
                int randomAvatar = rand.nextInt(12) + 1;
                String url = "https://raw.githubusercontent.com/jc5230/Lynli/main/app/sampledata/sampleavatar/avatar_" + randomAvatar + ".png";
                urls.add(url);
                Log.i("HomeActivity", String.valueOf(randomAvatar));
            }
            Item item = new Item(title, urls);
            items.add(item);
        }

        return items;
    }



}