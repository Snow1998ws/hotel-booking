package com.example.demo.controller;

public class Order_info
{
    private String typename;
    private String hotel_name;
    private Integer price;
    private Integer order_id;
    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }



    public Integer getOrder_id() {
        return order_id;
    }

    public Order_info() {
    }

    private Integer discount;

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getTypename() {
        return typename;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }
}
