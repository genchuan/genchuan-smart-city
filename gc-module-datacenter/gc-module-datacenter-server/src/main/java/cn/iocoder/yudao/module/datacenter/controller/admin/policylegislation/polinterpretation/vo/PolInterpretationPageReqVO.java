package cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.polinterpretation.vo;

import lombok.*;



import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 政策解读信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PolInterpretationPageReqVO extends PageParam {

    @Schema(description = "解读ID，唯一编码，UUID生成", example = "105")
    private String polInterpretationId;

    @Schema(description = "关联政策类型，取值为国家政策/地方法规，标识关联政策类型", example = "1")
    private String relatedPolType;

    @Schema(description = "关联政策ID，关联国家政策/地方法规ID，国家政策信息表（biz_national_pol）/地方法规信息表（biz_local_regulation）", example = "31843")
    private String relatedPolId;

    @Schema(description = "关联政策标题，与关联政策ID同步，国家政策信息表（biz_national_pol）/地方法规信息表（biz_local_regulation）")
    private String relatedPolTitle;

    @Schema(description = "解读标题，如《“十四五”智慧城市发展规划解读》")
    private String interpretationTitle;

    @Schema(description = "解读详细内容（支持富文本）")
    private String interpretationContent;

    @Schema(description = "解读人ID，解读人账号，用户信息表（sys_user）", example = "4289")
    private String interpreterId;

    @Schema(description = "解读人姓名，与解读人ID同步，用户信息表（sys_user）", example = "赵六")
    private String interpreterName;

    @Schema(description = "解读时间，格式：YYYYMMDD")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] interpretationTime;

    @Schema(description = "解读状态：已发布/未发布，标识解读是否公开", example = "2")
    private String interpretationStatus;

    @Schema(description = "附件ID，关联解读附件ID，文件存储表（sys_file_storage）", example = "13739")
    private String attachId;

    @Schema(description = "附件路径，解读附件存储路径，文件存储表（sys_file_storage）")
    private String attachPath;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“解读类型：官方/专家”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“解读范围：全国/地方”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“解读来源：政府官网”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“关联业务：设备部署”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}