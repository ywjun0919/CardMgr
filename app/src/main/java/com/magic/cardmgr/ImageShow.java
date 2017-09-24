package com.magic.cardmgr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageShow extends AppCompatActivity {

    ImageView imageView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        imageView = (ImageView) this.findViewById(R.id.imgshowview);

        Bundle bundle = this.getIntent().getExtras();
        String filePath = bundle.getString("path");
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        imageView.setImageBitmap(bitmap);
    }
}
