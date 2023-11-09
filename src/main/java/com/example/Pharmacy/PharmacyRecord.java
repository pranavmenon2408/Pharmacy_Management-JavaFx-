package com.example.Pharmacy;

public class PharmacyRecord {
    private int stock;
    private String name;
    private String mDateofExpiry;
    private int price;
    public PharmacyRecord(){}
    public int getStock(){
        return stock;
    }
    public void setStock(int stock){
        this.stock=stock;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price=price;
    }
    public String getDateofExpiry(){
        return mDateofExpiry;
    }
    public void setDateofExpiry(String doe){
        mDateofExpiry=doe;
    }
    @Override
    public String toString(){
        return "\nStock: "+stock+"\nDate of Expiry: "+mDateofExpiry+"\nPrice: "+price;
    }

}
