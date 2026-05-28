package cn.iocoder.yudao.module.smartcampus.enums;

/**
 * 智慧校园管理 操作日志枚举
 * 目的：统一管理，也减少 Service 里各种“复杂”字符串
 *
 * @author 亘川智城
 */
public interface LogRecordConstants {

    // ======================= STUDENT_ARCHIVE 档案管理 =======================

    String STUDENT_ARCHIVE_TYPE = "STUDENT 学籍档案管理";
    String STUDENT_ARCHIVE_CREATE_SUB_TYPE = "创建学籍档案";
    String STUDENT_ARCHIVE_CREATE_SUCCESS = "创建了学籍档案";
    String STUDENT_ARCHIVE_UPDATE_SUB_TYPE = "更新学籍档案";
    String STUDENT_ARCHIVE_UPDATE_SUCCESS = "更新了学籍档案";
    String STUDENT_ARCHIVE_DELETE_SUB_TYPE = "删除学籍档案";
    String STUDENT_ARCHIVE_DELETE_SUCCESS = "删除了学籍档案";
    String STUDENT_ARCHIVE_EXPORT_SUB_TYPE = "导出学籍档案";
    String STUDENT_ARCHIVE_EXPORT_SUCCESS = "导出了学籍档案";

    String STUDENT_ARCHIVE_UPDATE_AUDIT_STATUS_SUB_TYPE = "更新学籍档案审核状态";
    String STUDENT_ARCHIVE_UPDATE_AUDIT_STATUS_SUCCESS = "更新了学籍档案的审核状态为【{{#status}}】";

    String STUDENT_ARCHIVE_MAINTAIN_SUB_TYPE = "维护学籍状态";
    String STUDENT_ARCHIVE_MAINTAIN_SUCCESS = "维护了学籍状态为【{{#status}}】，异动原因【{{#changeReason}}】";

}
