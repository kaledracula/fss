package com.cony.projects.fss.basic.entity;

/**
 * Created by wangk-p on 2017/12/5.
 */
public class SalesmanDto {

    private Long id;
    private String name;
    private Long mobilePhone;
    private String marketGroupName;
    private String distributionLocationLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Long mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMarketGroupName() {
        return marketGroupName;
    }

    public void setMarketGroupName(String marketGroupName) {
        this.marketGroupName = marketGroupName;
    }

    public String getDistributionLocationLocation() {
        return distributionLocationLocation;
    }

    public void setDistributionLocationLocation(String distributionLocationLocation) {
        this.distributionLocationLocation = distributionLocationLocation;
    }
}
