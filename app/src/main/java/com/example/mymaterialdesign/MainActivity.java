package com.example.mymaterialdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> listItem = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change that layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        for (int i = 0; i < 100; i++) {
            listItem.add("Item" + Integer.toString(i));
        }
        mAdapter = new MyAdapter(listItem);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addItem(View view) {
        listItem.add(0, "Added Item" + System.currentTimeMillis());
        // 変化した最初のインデックスと変化した個数を与えることで、アニメーションが行われる
        mAdapter.notifyItemRangeChanged(0, listItem.size());
    }

    public void transition(View view) {
        // set an exit transition
        // このMainActivityから出る遷移の時に、explodeのアニメーションが発生するよう設定
        getWindow().setExitTransition(new Explode());

        startActivity(new Intent(this, SubActivity.class),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}