package com.testprogram.response;

public class ItemResponse {
    private String codename;

    private String model;

    private UsageStatistics usage_statistics;

    private String market_name;

    private String manufacturer;

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public UsageStatistics getUsageStatistics() {
        return usage_statistics;
    }

    public void setUsageStatistics(UsageStatistics usage_statistics) {
        this.usage_statistics = usage_statistics;
    }

    public String getMarketName() {
        return market_name;
    }

    public void setMarketName(String market_name) {
        this.market_name = market_name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return getManufacturer();
    }
}