package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum ClubVenueApplyStatusEnum {

    VENUE_APPLY_STATUS_0("0", "无"),
    VENUE_APPLY_STATUS_1("1", "待申请"),
    VENUE_APPLY_STATUS_2("2", "已通过");

    public static final String DICT_TYPE = "club_mgmt_venue_apply_status";

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
        for (ClubVenueApplyStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
