package com.sampleFlinkProject.util;

import java.io.Serializable;

public class VehicleInstantData implements Serializable
{
    private String _id;
    private String day_hour;
    private String geohash;
    private String latitude;
    private String longitude;
    private String vehicle_type;
    private String speed;
    private String day_year;
    private String day_mounth;
    private String day_day;


    public VehicleInstantData() {
    }

    public static VehicleInstantData createFromSplittedArray(String[] splittedArray)
    {
        VehicleInstantData newData = new VehicleInstantData(
                splittedArray[0], splittedArray[1], splittedArray[2],
                splittedArray[3], splittedArray[4], splittedArray[5],
                splittedArray[6], splittedArray[7], splittedArray[8],
                splittedArray[9]);

        return newData;
    }

    private VehicleInstantData(String _id, String day_hour, String geohash,
                               String latitude, String longitude,
                               String vehicle_type, String speed,
                               String day_year, String day_mounth, String day_day) {
        this._id = _id;
        this.day_hour = day_hour;
        this.geohash = geohash;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vehicle_type = vehicle_type;
        this.speed = speed;
        this.day_year = day_year;
        this.day_mounth = day_mounth;
        this.day_day = day_day;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDay_hour() {
        return day_hour;
    }

    public void setDay_hour(String day_hour) {
        this.day_hour = day_hour;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDay_year() {
        return day_year;
    }

    public void setDay_year(String day_year) {
        this.day_year = day_year;
    }

    public String getDay_mounth() {
        return day_mounth;
    }

    public void setDay_mounth(String day_mounth) {
        this.day_mounth = day_mounth;
    }

    public String getDay_day() {
        return day_day;
    }

    public void setDay_day(String day_day) {
        this.day_day = day_day;
    }

    @Override
    public String toString() {
        return "VehicleInstantData{" +
                "_id='" + _id + '\'' +
                ", day_hour='" + day_hour + '\'' +
                ", geohash='" + geohash + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", vehicle_type='" + vehicle_type + '\'' +
                ", speed='" + speed + '\'' +
                ", day_year='" + day_year + '\'' +
                ", day_mounth='" + day_mounth + '\'' +
                ", day_day='" + day_day + '\'' +
                '}';
    }
}
