package com.nielsmasdorp.speculum.models.ratp;

import java.util.List;

public class RatpLineStatuses {

    private List<RatpLineStatus> statuses;

    public RatpLineStatuses(List<RatpLineStatus> statuses) {
        this.statuses = statuses;
    }

    public List<RatpLineStatus> getStatuses() {
        return statuses;
    }
}
