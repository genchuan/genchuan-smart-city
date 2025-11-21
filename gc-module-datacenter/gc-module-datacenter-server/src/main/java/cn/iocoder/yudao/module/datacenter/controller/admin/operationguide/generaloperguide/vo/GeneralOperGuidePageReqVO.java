package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.generaloperguide.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 通用操作指南分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GeneralOperGuidePageReqVO extends PageParam {

    @Schema(description = "指南ID，唯一编码，UUID生成", example = "30631")
    private String generalOperGuideId;

    @Schema(description = "指南标题，如《系统登录与权限切换指南》")
    private String guideTitle;

    @Schema(description = "操作场景，取值如登录/权限/数据操作，操作场景字典表（sys_dict_oper_scenario）")
    private String operScenario;

    @Schema(description = "操作步骤（支持富文本，含截图插入）")
    private String operSteps;

    @Schema(description = "适用角色，如“所有用户”“管理员”，角色表（sys_role）")
    private String applicableRole;

    @Schema(description = "版本号，如“V1.0”")
    private String version;

    @Schema(description = "生效时间，格式：YYYYMMDD")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveTime;

    @Schema(description = "指南附件ID，关联附件ID（如操作视频），文件存储表（sys_file_storage）", example = "28872")
    private String guideAttachId;

    @Schema(description = "指南附件路径，附件存储路径，文件存储表（sys_file_storage）")
    private String guideAttachPath;

    @Schema(description = "指南状态：已发布/草稿，标识指南是否公开", example = "1")
    private String guideStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“指南类型：文字/视频”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“难度等级：简单/中等”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“关联功能：数据导入”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“查看次数”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}