package com.testprogram.response;

import java.util.List;

public class SessionInfos {
    private Integer building_id;

    private List<Purchases> purchases;

    public Integer getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Integer building_id) {
        this.building_id = building_id;
    }

    public List<Purchases> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchases> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "ClassPojo [building_id = " + building_id + ", purchases = " + purchases + "]";
    }
}