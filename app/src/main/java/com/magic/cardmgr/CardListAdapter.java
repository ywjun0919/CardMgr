package com.magic.cardmgr;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;
import java.util.EventListener;
import java.util.List;

/**
 * Created by Administrator on 2017/9/23 0023.
 */

public class CardListAdapter extends ArrayAdapter<CardInfo> implements View.OnClickListener {


    private int resourceId ;

    public CardListAdapter(Context context, int resource, List<CardInfo> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CardInfo cardInfo = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView txt_CardName = (TextView)view.findViewById(R.id.text_card_name);
        txt_CardName.setText(cardInfo.getCardName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txt_CardName = (TextView)v.findViewById(R.id.text_card_name);
                txt_CardName.setText("BeClick");
                Intent intent = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Activity activity =(Activity) getContext();
                activity.startActivityForResult(intent,position );
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Button btn =  (Button)v.findViewById(R.id.button_edit);
                btn.setVisibility(btn.VISIBLE);
                EditText editText =  (EditText)v.findViewById(R.id.editText);
                editText.setVisibility(editText.VISIBLE);
                TextView txt_card = (TextView)v.findViewById(R.id.text_card_name);
                txt_card.setVisibility(txt_card.INVISIBLE);
                return false;
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {

        TextView txt_CardName = (TextView)view.findViewById(R.id.text_card_name);
        txt_CardName.setText("BeClick");
        Intent intent = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Activity activity =(Activity) getContext();
        activity.startActivityForResult(intent,0 );
    }

    private String getRealPathFromURI(Uri contentUri) { //传入图片uri地址
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.d("CardMgr","onActivityResult  "+resultCode);
        if(requestCode >0){
            Uri uri = data.getData();
            CardInfo cardInfo = getItem(requestCode);
            String path = getRealPathFromURI(uri);
            cardInfo.setCardName(path);
            this.notifyDataSetChanged();
            Intent intent = new Intent(this.getContext(),ImageShow.class);
            intent.putExtra("path",path);
            this.getContext().startActivity(intent);
        }

    }
}
