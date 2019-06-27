package com.example.demo.domain;

public class Room {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room.room_id
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    private Integer roomId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room.r_hotel_id
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    private Integer rHotelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room.r_roomtype_id
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    private Integer rRoomtypeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room.r_price
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    private Integer rPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room.r_isempty
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    private String rIsempty;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    public Room(Integer roomId, Integer rHotelId, Integer rRoomtypeId, Integer rPrice, String rIsempty) {
        this.roomId = roomId;
        this.rHotelId = rHotelId;
        this.rRoomtypeId = rRoomtypeId;
        this.rPrice = rPrice;
        this.rIsempty = rIsempty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    public Room() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room.room_id
     *
     * @return the value of room.room_id
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room.room_id
     *
     * @param roomId the value for room.room_id
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room.r_hotel_id
     *
     * @return the value of room.r_hotel_id
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    public Integer getrHotelId() {
        return rHotelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room.r_hotel_id
     *
     * @param rHotelId the value for room.r_hotel_id
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    public void setrHotelId(Integer rHotelId) {
        this.rHotelId = rHotelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room.r_roomtype_id
     *
     * @return the value of room.r_roomtype_id
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    public Integer getrRoomtypeId() {
        return rRoomtypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room.r_roomtype_id
     *
     * @param rRoomtypeId the value for room.r_roomtype_id
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    public void setrRoomtypeId(Integer rRoomtypeId) {
        this.rRoomtypeId = rRoomtypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room.r_price
     *
     * @return the value of room.r_price
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    public Integer getrPrice() {
        return rPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room.r_price
     *
     * @param rPrice the value for room.r_price
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    public void setrPrice(Integer rPrice) {
        this.rPrice = rPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room.r_isempty
     *
     * @return the value of room.r_isempty
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    public String getrIsempty() {
        return rIsempty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room.r_isempty
     *
     * @param rIsempty the value for room.r_isempty
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    public void setrIsempty(String rIsempty) {
        this.rIsempty = rIsempty == null ? null : rIsempty.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Room other = (Room) that;
        return (this.getRoomId() == null ? other.getRoomId() == null : this.getRoomId().equals(other.getRoomId()))
            && (this.getrHotelId() == null ? other.getrHotelId() == null : this.getrHotelId().equals(other.getrHotelId()))
            && (this.getrRoomtypeId() == null ? other.getrRoomtypeId() == null : this.getrRoomtypeId().equals(other.getrRoomtypeId()))
            && (this.getrPrice() == null ? other.getrPrice() == null : this.getrPrice().equals(other.getrPrice()))
            && (this.getrIsempty() == null ? other.getrIsempty() == null : this.getrIsempty().equals(other.getrIsempty()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room
     *
     * @mbg.generated Wed Jun 26 19:59:20 CST 2019
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoomId() == null) ? 0 : getRoomId().hashCode());
        result = prime * result + ((getrHotelId() == null) ? 0 : getrHotelId().hashCode());
        result = prime * result + ((getrRoomtypeId() == null) ? 0 : getrRoomtypeId().hashCode());
        result = prime * result + ((getrPrice() == null) ? 0 : getrPrice().hashCode());
        result = prime * result + ((getrIsempty() == null) ? 0 : getrIsempty().hashCode());
        return result;
    }
}