package cn.iocoder.yudao.module.smartcampus.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 学生管理所有的字典类型
 */
public enum SmartCampusDictTypeEnum {

    // ==================== 学生档案 ====================
    /**
     * 流程状态（待审核 / 正常 / 已归档）
     */
    ARCHIVE_PROCESS_STATUS("student_archive_process_status", "流程状态"),
    /**
     * 学籍状态（在籍 / 休学 / 退学 / 异动）
     */
    ARCHIVE_STATUS("student_archive_status", "学籍状态"),



    ;
    // ==================== 枚举属性 ====================
    /**
     * 字典类型
     */
    private final String type;

    /**
     * 字典名称
     */
    private final String name;

    }
