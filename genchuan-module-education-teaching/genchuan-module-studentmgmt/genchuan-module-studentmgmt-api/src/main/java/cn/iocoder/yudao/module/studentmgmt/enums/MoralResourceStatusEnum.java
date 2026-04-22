package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 德育资源状态
 */
public enum MoralResourceStatusEnum {

    OFFLINE("online","'未上架'"),
    ONLINE("online","'已上架'");

    public static final String DICT_TYPE = "moral_activity_activity_type";
    /**
     * 状态
     * <p>
     */
    private final String status;

    /**
     * 名字
     */
    private final String name;


    // 根据key获取名称
    public static String getNameByKey(String key) {
        for (MoralResourceStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }
}
