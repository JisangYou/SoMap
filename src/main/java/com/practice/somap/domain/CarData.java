package com.practice.somap.domain;

/**
 * Created by JisangYou on 2017-11-22.
 */

public class CarData {
    private String id;

    private String driving_fee;

    private String zone_name;

    private String zone_id;

    private String coupon_discount;

    private String car_option;

    private String member_discount;

    private String car_name;

    private String oil_type;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDriving_fee ()
    {
        return driving_fee;
    }

    public void setDriving_fee (String driving_fee)
    {
        this.driving_fee = driving_fee;
    }

    public String getZone_name ()
    {
        return zone_name;
    }

    public void setZone_name (String zone_name)
    {
        this.zone_name = zone_name;
    }

    public String getZone_id ()
    {
        return zone_id;
    }

    public void setZone_id (String zone_id)
    {
        this.zone_id = zone_id;
    }

    public String getCoupon_discount ()
    {
        return coupon_discount;
    }

    public void setCoupon_discount (String coupon_discount)
    {
        this.coupon_discount = coupon_discount;
    }

    public String getCar_option ()
    {
        return car_option;
    }

    public void setCar_option (String car_option)
    {
        this.car_option = car_option;
    }

    public String getMember_discount ()
    {
        return member_discount;
    }

    public void setMember_discount (String member_discount)
    {
        this.member_discount = member_discount;
    }

    public String getCar_name ()
    {
        return car_name;
    }

    public void setCar_name (String car_name)
    {
        this.car_name = car_name;
    }

    public String getOil_type ()
    {
        return oil_type;
    }

    public void setOil_type (String oil_type)
    {
        this.oil_type = oil_type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", driving_fee = "+driving_fee+", zone_name = "+zone_name+", zone_id = "+zone_id+", coupon_discount = "+coupon_discount+", car_option = "+car_option+", member_discount = "+member_discount+", car_name = "+car_name+", oil_type = "+oil_type+"]";
    }
}