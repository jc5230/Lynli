package com.innojane.lynli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity{

    private AvatarAdapter avatarAdapter;
    private RecyclerView avatarRecyclerView;
    private View briefView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        briefView = findViewById(R.id.brief_view);
        avatarRecyclerView = briefView.findViewById(R.id.recyclerview_avatar);

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