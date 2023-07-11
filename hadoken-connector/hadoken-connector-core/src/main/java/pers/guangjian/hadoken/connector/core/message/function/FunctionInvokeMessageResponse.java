package pers.guangjian.hadoken.connector.core.message.function;

import cn.hutool.json.JSONObject;
import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessageResponse;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:31
 */
@Getter
@Setter
public class FunctionInvokeMessageResponse extends CommonDeviceMessageResponse<FunctionInvokeMessageResponse> {

    private String functionId;

    private Object output;

    public FunctionInvokeMessageResponse() {
    }

    public MessageType getMessageType() {
        return MessageType.INVOKE_ABILITY_RESPONSE;
    }

    public static FunctionInvokeMessageResponse create() {
        FunctionInvokeMessageResponse response = new FunctionInvokeMessageResponse();
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }

    public FunctionInvokeMessageResponse success() {
        this.setSuccess(true);
        return this;
    }

    public FunctionInvokeMessageResponse success(Object output) {
        return success()
                .output(output);
    }

    public FunctionInvokeMessageResponse output(Object output) {
        this.setOutput(output);
        return this;
    }

    @Override
    public void fromJson(JSONObject jsonObject) {
        super.fromJson(jsonObject);
        this.functionId = jsonObject.getStr("functionId");
        this.output = jsonObject.get("output");
    }

    public static FunctionInvokeMessageResponse success(String deviceId,
                                                        String functionId,
                                                        String messageId,
                                                        Object output) {
        FunctionInvokeMessageResponse response = new FunctionInvokeMessageResponse();

        response.setFunctionId(functionId);
        response.setOutput(output);
        response.success();
        response.setDeviceId(deviceId);
        response.setMessageId(messageId);

        return response;
    }

    public static FunctionInvokeMessageResponse error(String deviceId,
                                                      String functionId,
                                                      String messageId,
                                                      String message) {
        FunctionInvokeMessageResponse response = new FunctionInvokeMessageResponse();

        response.setFunctionId(functionId);
        response.setMessage(message);
        response.setSuccess(false);
        response.setDeviceId(deviceId);
        response.setMessageId(messageId);

        return response;
    }
}
