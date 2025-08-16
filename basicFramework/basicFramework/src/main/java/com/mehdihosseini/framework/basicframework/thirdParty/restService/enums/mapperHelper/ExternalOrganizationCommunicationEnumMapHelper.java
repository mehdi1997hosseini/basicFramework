package com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.mapperHelper;

import com.mehdihosseini.framework.basicframework.thirdParty.restService.authService.externalOrganizationAuthService.token.tokenStrategy.AuthType;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.ContentType;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.TokenHeaderName;
import com.mehdihosseini.framework.basicframework.thirdParty.restService.enums.TokenType;
import com.mehdihosseini.framework.basicframework.utils.TimeUnitType;
import org.mapstruct.Named;
import org.springframework.http.HttpMethod;

public class ExternalOrganizationCommunicationEnumMapHelper {

    /**
     * <p>convert enum class to string</p>
     */

    @Named("tokenTypeToString")
    public static String tokenTypeToString(TokenType value) {
        return value == null ? null : value.name();
    }

    @Named("authTypeToString")
    public static String authTypeToString(AuthType value) {
        return value == null ? null : value.name();
    }

    @Named("httpMethodToString")
    public static String httpMethodToString(HttpMethod value) {
        return value == null ? null : value.name();
    }

    @Named("contentTypeToString")
    public static String contentTypeToString(ContentType value) {
        return value == null ? null : value.name();
    }

    @Named("tokenHeaderNameToString")
    public static String tokenHeaderNameToString(TokenHeaderName value) {
        return value == null ? null : value.name();
    }

    @Named("timeUnitTypeToString")
    public static String timeUnitTypeToString(TimeUnitType value) {
        return value == null ? null : value.name();
    }


    /**
     * <p>convert string to enum class</p>
     */

    @Named("stringToTokenType")
    public static TokenType stringToTokenType(String value) {
        return value != null ? TokenType.fromString(value) : null;
    }

    @Named("stringToAuthType")
    public static AuthType stringToAuthType(String value) {
        return value == null ? null : AuthType.fromString(value);
    }

    @Named("stringToHttpMethod")
    public static HttpMethod stringToHttpMethod(String value) {
        return value == null ? null : HttpMethod.valueOf(value);
    }

    @Named("stringToContentType")
    public static ContentType stringToContentType(String value) {
        return value == null ? null : ContentType.fromString(value);
    }

    @Named("stringToTokenHeaderName")
    public static TokenHeaderName stringToTokenHeaderName(String value) {
        return value == null ? null : TokenHeaderName.fromString(value);
    }

    @Named("stringToTimeUnitType")
    public static TimeUnitType stringToTimeUnitType(String value) {
        return value != null ? TimeUnitType.fromString(value) : null;
    }

}
