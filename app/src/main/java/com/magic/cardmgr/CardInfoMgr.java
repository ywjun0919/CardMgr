package com.magic.cardmgr;

import android.app.Activity;
import android.os.Environment;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/24 0024.
 */

public class CardInfoMgr {

    private static String fileName = "/cardinfo.txt";

    //保存文件到SDcard中
    public static void saveFile(String str){
        FileOutputStream fos=null;
        String state= Environment.getExternalStorageState();
        //获取SD卡状态
        if(!state.equals(Environment.MEDIA_MOUNTED)){
            //判断SD卡是否就绪
           // Toast.makeText(getBaseContext(),"请检查SD卡",Toast.LENGTH_SHORT).show();
            return;
        }
        File file=Environment.getExternalStorageDirectory();
        //取得SD卡根目录
        try {
            Log.d("===SD卡根目录：",file.getCanonicalPath()+fileName);
            fos=new FileOutputStream(file.getCanonicalPath()+fileName,false);
            //String str=content.getText().toString();
            fos.write(str.getBytes());
            //Toast.makeText(getBaseContext(),"保存成功",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String readFile(){
        BufferedReader reader =null;
        FileInputStream fis=null;
        //从SD卡读取文件
        StringBuilder sdb=new StringBuilder();
        String state=Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED)){
           // Toast.makeText(getBaseContext(),"SD卡未就绪",Toast.LENGTH_SHORT).show();
            return "";
        }
        File root=Environment.getExternalStorageDirectory();
        try {
            fis=new FileInputStream(root+fileName);
            if(null !=fis){
                reader=new BufferedReader(new InputStreamReader(fis));
                String row="";
                while ((row=reader.readLine())!=null){
                    sdb.append(row);
                }
            }

        } catch (FileNotFoundException e) {
            //Toast.makeText(this,"文件不存在",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sdb.toString();
    }

    public static void saveCards(List<CardInfo> cardInfos){
        StringBuilder sdb=new StringBuilder();
        for (int i = 0; i < cardInfos.size(); i++) {
            CardInfo cardInfo = cardInfos.get(i);
            sdb.append(cardInfo.getCardName());
            sdb.append(",");
        }
        saveFile(sdb.toString());
    }

    public static List<CardInfo> readCardInfos(){
        String str = readFile();
        List<CardInfo> cardInfos = new ArrayList<>();
        if(str.length()>0){
            String[] strs = str.split(",");
            for (int i = 0; i < strs.length; i++) {
                CardInfo cardInfo = new CardInfo(strs[i],strs[i]);
                cardInfos.add(cardInfo);
            }
        }else{
            for (int i = 0; i < 10; i++) {
                CardInfo cardInfo = new CardInfo("newone","newone");
                cardInfos.add(cardInfo);
            }
        }
        return cardInfos;
    }
}
