package com.magic.cardmgr;

/**
 * Created by Administrator on 2017/9/23 0023.
 */

public class CardInfo {

    private String mCardName;
    private String mCardPath;

    public CardInfo(String cardName,String cardPath){
        mCardName = cardName;
        mCardPath = cardPath;
    }
    public String getCardName(){
        return mCardName;
    }

    public String getmCardPath(){
        return mCardPath;
    }

    public void setCardName(String cardName){mCardName = cardName;}
}
