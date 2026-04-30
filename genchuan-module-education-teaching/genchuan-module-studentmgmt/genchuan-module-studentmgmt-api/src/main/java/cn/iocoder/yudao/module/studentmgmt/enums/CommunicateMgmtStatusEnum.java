package cn.iocoder.yudao.module.studentmgmt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum CommunicateMgmtStatusEnum {

    // 状态（未发布 / 已发布），关联芋道字典表：communicate_mgmt_status
    unpublished("unpublished", "未发布"),
    published("published", "已发布");

    public static final String DICT_TYPE = "communicate_mgmt_status";
    /**
     * 状态
     * <p>
     */
    private final String status;

    /**
     * 名字
     */
    private final String name;


}
