package cn.iocoder.yudao.module.smartcampus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 状态
 */
public enum ArchiveProcessStatusEnum {

    // 流程状态：待审核 / 正常 / 已归档，关联芋道字典表：student_archive_process_status
    ARCHIVE_PROCESS_STATUS_0("0", "待审核"),
    ARCHIVE_PROCESS_STATUS_1("1", "正常"),
    ARCHIVE_PROCESS_STATUS_2("2", "已归档");

    public static final String DICT_TYPE = "archive_process_status";
    /**
     * 状态
     * <p>
     */
    private final String processStatus;

    /**
     * 名字
     */
    private final String name;

    // 根据key获取名称
    public static String getNameByKey(String key) {
        for (ArchiveProcessStatusEnum value : values()) {
            if (value.processStatus.equals(key)) {
                return value.name;
            }
        }
        return null;
    }


}
