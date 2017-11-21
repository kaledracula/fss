package com.cony.web.common.result;

/**
 * Created by wangk-p on 2017/4/21.
 */
public enum ResponseMessage {

    Success("0", "success"),
    Server_Exception("1", ""),
    Business_Exception("2", ""),
    Data_Exception("3", ""),
    Model_Validate_Exception("4", ""),
    No_Such_MeThod_Exception("5000001", "no such method called:"),
    Illegal_Access_Exception("5000002", "illegal_access_exception:"),
    Missing_Servlet_Request_Parameter_Exception("5000003", "required_parameter_is_not_present:"),
    Http_Message_Not_Readable_Exception("5000004", "could_not_read_json:"),
    Method_Argument_Not_Valid_Exception("5000005", ""),
    Bind_Exception("5000006", ""),
    Constraint_Violation_Exception("5000007", "constraint_violation_exception:"),
    Validation_Exception("5000008", ""),
    Http_Request_Method_Not_Supported_Exception("5000009", "request_method_not_supported:"),
    Http_Media_Type_Not_Supported_Exception("5000010", "content_type_not_supported:");

    private String code;
    private String message;

    ResponseMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
