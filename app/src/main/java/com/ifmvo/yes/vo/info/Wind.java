package com.ifmvo.yes.vo.info;

/**
 * ifmvo on 2016/4/10.
 */
public class Wind {
    private String windspeed;

    private String direct;

    private String power;

    public Wind() {
    }

    @Override
    public String toString() {
        return "Wind{" +
                "windspeed='" + windspeed + '\'' +
                ", direct='" + direct + '\'' +
                ", power='" + power + '\'' +
                '}';
    }

    public Wind(String windspeed, String direct, String power) {
        this.windspeed = windspeed;
        this.direct = direct;
        this.power = power;
    }
}
