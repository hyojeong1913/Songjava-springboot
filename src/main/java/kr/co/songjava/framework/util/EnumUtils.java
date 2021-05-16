package kr.co.songjava.framework.util;

import kr.co.songjava.mvc.domain.BaseCodeLabelEnum;
import org.apache.commons.lang3.ObjectUtils;

public class EnumUtils {

    /**
     * @param values parameter 로 넘어온 선택된 값들
     * @param codeLabelEnum 현재 출력하고 있는 code
     * @return
     */
    public static boolean isSelected(BaseCodeLabelEnum[] values, BaseCodeLabelEnum codeLabelEnum) {

        if (ObjectUtils.isEmpty(values)) {
            return false;
        }

        for (BaseCodeLabelEnum value : values) {

            // 동일한 경우
            if (value.code().equals(codeLabelEnum.code())) {
                return true;
            }
        }

        return false;
    }
}
