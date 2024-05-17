package com.example.comicdex;

public class Comic {
    int item_id;
    String Item_name;
    int price;
    int pub_id;
    int pageCount;
    public int getItem_id() {
        return item_id;
    }
    public void setPageCount(int val){
        this.pageCount = val;
    }

    public int getPageCount(){
        return this.pageCount;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String item_name) {
        Item_name = item_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPub_id() {
        return pub_id;
    }

    public void setPub_id(int pub_id) {
        this.pub_id = pub_id;
    }
}
