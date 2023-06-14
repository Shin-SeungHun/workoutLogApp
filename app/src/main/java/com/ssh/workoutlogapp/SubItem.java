package com.ssh.workoutlogapp;



// 자식 아이템
public class SubItem {
    private int subItemImage;
    private String subItemTitle;
    private String subItemDesc;
    //private boolean isButtonEnabled;

    public SubItem(){}



//    public SubItem(String subItemTitle, String subItemDesc) {
//        this.subItemTitle = subItemTitle;
//        this.subItemDesc = subItemDesc;
//    }

    public SubItem(int subItemImage, String subItemTitle, String subItemDesc) {
        this.subItemImage = subItemImage;
        this.subItemTitle = subItemTitle;
        this.subItemDesc = subItemDesc;
    }




    public int getSubItemImage() {
        return subItemImage;
    }

    public void setSubItemImage(int subItemImage) {
        this.subItemImage = subItemImage;
    }

    public String getSubItemTitle() {
        return subItemTitle;
    }

    public void setSubItemTitle(String subItemTitle) {
        this.subItemTitle = subItemTitle;
    }

    public String getSubItemDesc() {
        return subItemDesc;
    }

    public void setSubItemDesc(String subItemDesc) {
        this.subItemDesc = subItemDesc;
    }

    @Override
    public String toString() {
        return "SubItem{" +
                "subItemImage=" + subItemImage +
                ", subItemTitle='" + subItemTitle + '\'' +
                ", subItemDesc='" + subItemDesc + '\'' +
                '}';
    }

}


