package com.practice.somap.domain;

import java.util.List;

/**
 * Created by JisangYou on 2017-11-22.
 */

public class Car {
    private String count;

    private List<CarData> carData;

    private String code;

    private String msg;

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public List<CarData> getCarData ()
    {
        return carData;
    }

    public void setCarData (List<CarData> carData)
    {
        this.carData = carData;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    public boolean isSuccess(){
        return code.equals("200");
    }

    @Override
    public String toString()
    {
        return "ClassPojo [count = "+count+", carData = "+carData+", code = "+code+", msg = "+msg+"]";
    }
}
