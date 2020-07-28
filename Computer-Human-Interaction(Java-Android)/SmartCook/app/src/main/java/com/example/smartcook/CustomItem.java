package com.example.smartcook;

public class CustomItem {

    //This class is used for the spinner interface and it contains for each item name and image.

    private String spinnerItemName;
    private int spinnerItemImage;

    public CustomItem(String spinnerItemName, int spinnerItemImage) {
        this.spinnerItemName = spinnerItemName;
        this.spinnerItemImage = spinnerItemImage;
    }//end of const1

    public String getSpinnerItemName() {
        return spinnerItemName;
    }

    public int getSpinnerItemImage() {
        return spinnerItemImage;
    }


}//end of CustomItem
