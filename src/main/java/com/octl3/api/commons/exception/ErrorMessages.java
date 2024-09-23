package com.octl3.api.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages implements ErrorMessage {
    SUCCESS(200, "Success"),

    BAD_REQUEST(400, "Bad request"),
    INVALID_VALUE(400_001, "Invalid value"),
    SAVE_DATABASE_ERROR(400_002, "Save database error"),

    INTERNAL_SERVER_ERROR(500_001, "Internal Server Error!"),
    UPDATE_ERROR(500_002, "Update error!"),

    UNAUTHENTICATED(401, "Unauthenticated"),

    FORBIDDEN(403, "Unauthorization!"),

    NOT_FOUND(404, "Resource not found!"),
    NOT_FOUND_USER(404_001, "User not found!"),
    NOT_FOUND_DISTRICT(404_002, "District not found!"),
    NOT_FOUND_SUBDISTRICT(404_003, "Subdistrict not found!"),
    NOT_FOUND_FULFILMENT(404_004, "Fulfilment not found!"),
    NOT_FOUND_LASTMILE(404_005, "Fulfilment not found!"),
    NOT_FOUND_WAREHOUSE(404_006, "Warehouse not found!")
    ;

    private final int code;
    private final String message;
}
