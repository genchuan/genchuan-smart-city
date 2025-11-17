package cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicnotice.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 通知公告发布 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PublicNoticeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5546")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "通知ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "333")
    @ExcelProperty("通知ID，唯一编码，UUID生成")
    private String noticeId;

    @Schema(description = "通知标题，通知核心标题，需明确信息类型，如“XX区域停水通知”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("通知标题，通知核心标题，需明确信息类型，如“XX区域停水通知”")
    private String noticeTitle;

    @Schema(description = "通知内容，通知详细内容，支持富文本（图片、表格）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("通知内容，通知详细内容，支持富文本（图片、表格）")
    private String noticeContent;

    @Schema(description = "发布部门编码，发布通知的部门编码，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发布部门编码，发布通知的部门编码，部门信息表（sys_org）")
    private String deptCode;

    @Schema(description = "发布部门名称，与部门编码同步，不可手动修改，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("发布部门名称，与部门编码同步，不可手动修改，部门信息表（sys_org）")
    private String deptName;

    @Schema(description = "发布人ID，发布操作人ID，用户表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18425")
    @ExcelProperty("发布人ID，发布操作人ID，用户表（sys_user）")
    private Long publishUserId;

    @Schema(description = "发布人姓名，与发布人ID同步，用户表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("发布人姓名，与发布人ID同步，用户表（sys_user）")
    private String publishUserName;

    @Schema(description = "发布时间，点击“发布”时系统自动生成，格式yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发布时间，点击“发布”时系统自动生成，格式yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishTime;

    @Schema(description = "通知状态：1=草稿，2=已发布，3=已撤回，控制通知展示状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("通知状态：1=草稿，2=已发布，3=已撤回，控制通知展示状态")
    private Integer noticeStatus;

    @Schema(description = "生效时间，通知开始向公众展示的时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生效时间，通知开始向公众展示的时间")
    private LocalDateTime effectiveTime;

    @Schema(description = "失效时间，通知停止展示的时间，需晚于生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("失效时间，通知停止展示的时间，需晚于生效时间")
    private LocalDateTime expireTime;

    @Schema(description = "附件路径，通知附件（如检修方案）存储路径")
    @ExcelProperty("附件路径，通知附件（如检修方案）存储路径")
    private String attachPath;

    @Schema(description = "备注，补充说明，如“紧急通知，请优先展示”", example = "随便")
    @ExcelProperty("备注，补充说明，如“紧急通知，请优先展示”")
    private String remark;

    @Schema(description = "分类扩展字段1，预留，标识通知类型（停水/停电/活动）")
    @ExcelProperty("分类扩展字段1，预留，标识通知类型（停水/停电/活动）")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，标识通知级别（普通/紧急）")
    @ExcelProperty("分类扩展字段2，预留，标识通知级别（普通/紧急）")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储附件名称")
    @ExcelProperty("通用扩展字段1，预留，存储附件名称")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储发布终端（PC/APP）")
    @ExcelProperty("通用扩展字段2，预留，存储发布终端（PC/APP）")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}