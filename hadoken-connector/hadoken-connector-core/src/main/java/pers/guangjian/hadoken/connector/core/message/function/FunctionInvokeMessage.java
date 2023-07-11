package pers.guangjian.hadoken.connector.core.message.function;

import cn.hutool.json.JSONObject;
import pers.guangjian.hadoken.connector.core.message.CommonDeviceMessage;
import pers.guangjian.hadoken.connector.core.message.MessageType;
import pers.guangjian.hadoken.connector.core.message.RepayableDeviceMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/7/15 14:31
 */
@Getter
@Setter
public class FunctionInvokeMessage extends CommonDeviceMessage implements RepayableDeviceMessage<FunctionInvokeMessageResponse> {

    private String functionId;

    public FunctionInvokeMessage() {

    }

    public MessageType getMessageType() {
        return MessageType.INVOKE_ABILITY;
    }

    private List<FunctionParameter> inputs = new ArrayList<>();

    public Optional<Object> getInput(String name) {
        return inputs
                .stream()
                .filter(param -> param.getName().equals(name))
                .map(FunctionParameter::getValue)
                .findFirst();
    }

    public Optional<Object> getInput(int index) {
        return inputs.size() > index
                ? Optional.ofNullable(inputs.get(index))
                : Optional.empty();
    }

    public Map<String, Object> inputsToMap() {
        return inputs
                .stream()
                .collect(Collectors.toMap(FunctionParameter::getName, FunctionParameter::getValue, (a, b) -> a));
    }

    public <T> T inputsToBean(Class<T> beanType) {
        return new JSONObject(inputsToMap())
                .toBean(beanType);
    }

    public List<Object> inputsToList() {
        return inputs.stream()
                .map(FunctionParameter::getValue)
                .collect(Collectors.toList());
    }

    public Object[] inputsToArray() {
        return inputs.stream()
                .map(FunctionParameter::getValue)
                .toArray();
    }

    public FunctionInvokeMessage addInput(String name, Object value) {
        return this.addInput(new FunctionParameter(name, value));
    }

    public FunctionInvokeMessage addInput(FunctionParameter parameter) {
        inputs.add(parameter);
        return this;
    }

    @Override
    public FunctionInvokeMessage addHeader(String header, Object value) {
        super.addHeader(header, value);
        return this;
    }

    @Override
    public FunctionInvokeMessage removeHeader(String header) {
        super.removeHeader(header);
        return this;
    }

    @Override
    public void fromJson(JSONObject jsonObject) {
        super.fromJson(jsonObject);
        this.functionId = jsonObject.getStr("functionId");
    }

    @Override
    public FunctionInvokeMessageResponse newResponse() {
        FunctionInvokeMessageResponse response = new FunctionInvokeMessageResponse().from(this);
        response.setFunctionId(this.functionId);
        return response;
    }


}
