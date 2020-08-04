package com.example.materialtest;

import android.content.Intent;

import android.view.MenuItem;

import android.view.View;

import android.widget.ImageView;

import android.widget.TextView;

import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

import com.google.android.material.appbar.CollapsingToolbarLayout;
public class PirateActivity extends AppCompatActivity {
    public static final String PIRATE_NAME = "pirate_name";
    public static final String PIRATE_IMAGE_ID = "pirate_image_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirate);
        Intent intent = getIntent();
        String pirateName = intent.getStringExtra(PIRATE_NAME);
        int pirateImageId = intent.getIntExtra(PIRATE_IMAGE_ID, 0);
        Toolbar toolBar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView = findViewById(R.id.pirate_image_view);
        TextView fruitContentText = findViewById(R.id.pirate_content_text);
        //将ToolBar作为ActionBar显示
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(pirateName);
        Glide.with(this).load(pirateImageId).into(fruitImageView);
        String fruitContent = generateFruitContent(pirateName);
        fruitContentText.setText(fruitContent);
    }

    private String generateFruitContent(String pirateName) {
        StringBuilder pirateContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            pirateContent.append(pirateName);
        }
        return pirateContent.toString();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}