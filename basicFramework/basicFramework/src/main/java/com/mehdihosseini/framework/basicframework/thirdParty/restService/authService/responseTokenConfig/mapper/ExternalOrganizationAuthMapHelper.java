package com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.responseTokenConfig.mapper;

import com.mehdihosseini.framework.basicframework.utils.TimeUnitType;
import org.mapstruct.Named;


public class ExternalOrganizationAuthMapHelper {

    @Named("stringToTimeUnitType")
    public static TimeUnitType stringToTimeUnitType(String value) {
        return value != null ? TimeUnitType.fromString(value) : null;
    }

    @Named("timeUnitTypeToString")
    public static String timeUnitTypeToString(TimeUnitType value) {
        return value == null ? null : value.name();
    }


}
