package com.testprogram.response;

import java.util.List;

public class UsageStatistics {
    private List<SessionInfos> session_infos;

    public List<SessionInfos> getSessionInfos() {
        return session_infos;
    }

    public void setSessionInfos(List<SessionInfos> session_infos) {
        this.session_infos = session_infos;
    }

    @Override
    public String toString() {
        return "ClassPojo [session_infos = " + session_infos + "]";
    }
}