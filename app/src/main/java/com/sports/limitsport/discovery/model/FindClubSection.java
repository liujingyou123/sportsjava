package com.sports.limitsport.discovery.model;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by liuworkmac on 17/7/11.
 */

public class FindClubSection extends SectionEntity<Club> {
    public FindClubSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public FindClubSection(Club club) {
        super(club);
    }
}
