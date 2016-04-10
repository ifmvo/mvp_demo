package com.ifmvo.yes.vo.info;

/**
 * ifmvo on 2016/4/10.
 */
public class RealTime {

    private Wind wind;

    private String time;

    private Weather weather;

    private String dataUptime;

    private String date;

    private String city_code;

    private String city_name;

    private String week;

    private String moon;

    public RealTime() {
    }

    @Override
    public String toString() {
        return "RealTime{" +
                "wind=" + wind +
                ", time='" + time + '\'' +
                ", weather=" + weather +
                ", dataUptime='" + dataUptime + '\'' +
                ", date='" + date + '\'' +
                ", city_code='" + city_code + '\'' +
                ", city_name='" + city_name + '\'' +
                ", week='" + week + '\'' +
                ", moon='" + moon + '\'' +
                '}';
    }

    public RealTime(Wind wind, String time, Weather weather, String dataUptime, String date, String city_code, String city_name, String week, String moon) {
        this.wind = wind;
        this.time = time;
        this.weather = weather;
        this.dataUptime = dataUptime;
        this.date = date;
        this.city_code = city_code;
        this.city_name = city_name;
        this.week = week;
        this.moon = moon;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getDataUptime() {
        return dataUptime;
    }

    public void setDataUptime(String dataUptime) {
        this.dataUptime = dataUptime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMoon() {
        return moon;
    }

    public void setMoon(String moon) {
        this.moon = moon;
    }
}
