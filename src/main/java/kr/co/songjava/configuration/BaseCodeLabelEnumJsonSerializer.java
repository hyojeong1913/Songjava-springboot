package kr.co.songjava.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import kr.co.songjava.mvc.domain.BaseCodeLabelEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON 변환 시 BaseCodeLabelEnum 클래스에 대한 변환을 동일하게 처리
 */
public class BaseCodeLabelEnumJsonSerializer extends JsonSerializer<BaseCodeLabelEnum> {

    @Override
    public void serialize(BaseCodeLabelEnum value, JsonGenerator jsonGenerator, SerializerProvider provider)
        throws IOException, JsonProcessingException {
        Map<String, Object> map = new HashMap<>();

        map.put("code", value.code());
        map.put("label", value.label());

        jsonGenerator.writeObject(map);
    }
}
