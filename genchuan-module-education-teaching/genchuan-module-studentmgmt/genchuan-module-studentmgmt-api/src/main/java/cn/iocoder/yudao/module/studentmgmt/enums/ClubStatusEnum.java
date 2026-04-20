package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum ClubStatusEnum {

    // 状态（待审核 / 已通过 / 已建档），关联芋道字典表：club_mgmt_status
    Club_STATUS_0("0", "待审核"),
    Club_STATUS_1("1", "已通过"),
    Club_STATUS_2("2", "已建档");

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
        for (ClubStatusEnum value : values()) {
            if (value.status.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
