package com.example.demo.controller;

import java.util.Date;

public class Order_info
{
    private String typename;
    private String hotel_name;
    private Integer price;
    private Integer order_id;
    private Date date;
    private Integer Score;
    private Date paytime;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Integer getScore() {
        return Score;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    private String pic;

    public String getPic() {
        return pic;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    public Order_info(Object ob)
    {
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
