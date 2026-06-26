package com.anjing.model.errorcode;

/**
 * Project delivery collaboration agent error codes.
 */
public enum ProjectCopilotErrorCode implements ErrorCode {

    PROJECT_SPACE_NOT_FOUND("2400", "项目空间不存在"),
    RISK_ITEM_NOT_FOUND("2401", "风险项不存在"),
    ACTION_ITEM_NOT_FOUND("2402", "行动项不存在"),
    INVALID_COLLABORATION_STATE("2403", "协作状态不允许该操作");

    private final String code;
    private final String message;

    ProjectCopilotErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
