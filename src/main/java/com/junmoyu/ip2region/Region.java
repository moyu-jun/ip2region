package com.junmoyu.ip2region;

import java.io.Serializable;

/**
 * The geographic region object associated with the IP address.
 *
 * @author 莫语
 * @since 1.0.0
 */
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 国家
     */
    private String country;

    /**
     * 区域
     */
    private String region;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * ISP 网络供应商
     */
    private String isp;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    @Override
    public String toString() {
        return "Region{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", isp='" + isp + '\'' +
                '}';
    }
}
