package com.ssh.workoutlogapp;


// 자식 아이템
public class Item {
    private int ItemImage;
    private String ItemTitle;
    //private String subItemDesc;
    //private boolean isButtonEnabled;

    public Item() {
    }


    public Item(int ItemImage, String ItemTitle) {
        this.ItemImage = ItemImage;
        this.ItemTitle = ItemTitle;
    }

    public int getItemImage() {
        return ItemImage;
    }

    public void setItemImage(int itemImage) {
        ItemImage = itemImage;
    }

    public String getItemTitle() {
        return ItemTitle;
    }

    public void setItemTitle(String itemTitle) {
        ItemTitle = itemTitle;
    }

    @Override
    public String toString() {
        return "Item{" +
                "subItemImage=" + ItemImage +
                ", subItemTitle='" + ItemTitle + '\'' +
                '}';
    }
}


