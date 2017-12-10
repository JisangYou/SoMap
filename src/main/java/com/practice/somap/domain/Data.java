package com.practice.somap.domain;

/**
 * Created by JisangYou on 2017-11-21.
 */

public class Data {
    private String zone_id;

    private String visit_link;

    private String link;

    private String zone_attr;

    private String state;

    private String able_num;

    private String notice_oneway;

    private String visit;

    private String lng;

    private String zone_addr;

    private String id;

    private String zone_alias;

    private String total_num;

    private String zone_name;

    private String zone_props;

    private String oper_time;

    private String notice;

    private String lat;

    private String oper_way;

    public String getZone_id ()
    {
        return zone_id;
    }

    public void setZone_id (String zone_id)
    {
        this.zone_id = zone_id;
    }

    public String getVisit_link ()
    {
        return visit_link;
    }

    public void setVisit_link (String visit_link)
    {
        this.visit_link = visit_link;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getZone_attr ()
    {
        return zone_attr;
    }

    public void setZone_attr (String zone_attr)
    {
        this.zone_attr = zone_attr;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getAlbe_num ()
    {
        return able_num;
    }

    public void setAlbe_num (String albe_num)
    {
        this.able_num = albe_num;
    }

    public String getNotice_oneway ()
    {
        return notice_oneway;
    }

    public void setNotice_oneway (String notice_oneway)
    {
        this.notice_oneway = notice_oneway;
    }

    public String getVisit ()
    {
        return visit;
    }

    public void setVisit (String visit)
    {
        this.visit = visit;
    }

    public double getLng ()
    {
        return (lng!=null && !"".equals(lng))? Double.parseDouble(lng):0;
    }

    public void setLng (String lng)
    {
        this.lng = lng;
    }

    public String getZone_addr ()
    {
        return zone_addr;
    }

    public void setZone_addr (String zone_addr)
    {
        this.zone_addr = zone_addr;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getZone_alias ()
    {
        return zone_alias;
    }

    public void setZone_alias (String zone_alias)
    {
        this.zone_alias = zone_alias;
    }

    public String getTotal_num ()
    {
        return total_num;
    }

    public void setTotal_num (String total_num)
    {
        this.total_num = total_num;
    }

    public String getZone_name ()
    {
        return zone_name;
    }

    public void setZone_name (String zone_name)
    {
        this.zone_name = zone_name;
    }

    public String getZone_props ()
    {
        return zone_props;
    }

    public void setZone_props (String zone_props)
    {
        this.zone_props = zone_props;
    }

    public String getOper_time ()
    {
        return oper_time;
    }

    public void setOper_time (String oper_time)
    {
        this.oper_time = oper_time;
    }

    public String getNotice ()
    {
        return notice;
    }

    public void setNotice (String notice)
    {
        this.notice = notice;
    }

    public double getLat (){
        return (lat!=null && !"".equals(lat))? Double.parseDouble(lat):0;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    public String getOper_way ()
    {
        return oper_way;
    }

    public void setOper_way (String oper_way)
    {
        this.oper_way = oper_way;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [zone_id = "+zone_id+", visit_link = "+visit_link+", link = "+link+", zone_attr = "+zone_attr+", state = "+state+", able_num = "+able_num+", notice_oneway = "+notice_oneway+", visit = "+visit+", lng = "+lng+", zone_addr = "+zone_addr+", id = "+id+", zone_alias = "+zone_alias+", total_num = "+total_num+", zone_name = "+zone_name+", zone_props = "+zone_props+", oper_time = "+oper_time+", notice = "+notice+", lat = "+lat+", oper_way = "+oper_way+"]";
    }
}