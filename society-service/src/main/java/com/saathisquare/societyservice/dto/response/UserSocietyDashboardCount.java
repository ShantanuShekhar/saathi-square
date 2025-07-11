package com.saathisquare.societyservice.dto.response;

import java.util.UUID;

public interface UserSocietyDashboardCount {
    UUID getUserId();
    Long getSocietyCount();
    Long getTowerCount();
    Long getFloorCount();
    Long getFlatCount();
}

