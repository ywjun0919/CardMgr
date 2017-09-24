package com.magic.cardmgr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListView = null;
    CardListAdapter cardListAdapter = null;
    List<CardInfo> mCardInfos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCardInfos();
        mListView = (ListView) this.findViewById(R.id.card_list);
        cardListAdapter = new CardListAdapter(MainActivity.this,R.layout.card_item,mCardInfos);
        mListView.setAdapter(cardListAdapter);

    }

    void initCardInfos(){
//        for (int i = 0; i < 10; i++) {
//            CardInfo cardInfo = new CardInfo("Test"+i,"Path1"+i);
//            mCardInfos.add(cardInfo);
//        }
        mCardInfos = CardInfoMgr.readCardInfos();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cardListAdapter.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onStop() {
        super.onStop();
       // CardInfoMgr.saveCards(mCardInfos);
    }
}
