package cn.iocoder.yudao.module.park.controller.admin.park.user.certification.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 认证记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CertificationPageReqVO extends PageParam {

    @Schema(description = "[用户ID] 关联park_user.id", example = "11827")
    private Long userId;

    @Schema(description = "[用户类型] 如:个人/企业", example = "2")
    private String userType;

    @Schema(description = "[认证类型] 如:身份认证/企业认证", example = "2")
    private String certType;

    @Schema(description = "[认证材料] JSON格式varchar")
    private String certFiles;

    @Schema(description = "[身份证号] 个人认证")
    private String idCard;

    @Schema(description = "[统一社会信用代码] 企业认证")
    private String creditCode;

    @Schema(description = "[申请时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] applyTime;

    @Schema(description = "[审核人ID] 关联park_user.id")
    private Long auditBy;

    @Schema(description = "[审核时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "[审核结果] 如:通过/驳回")
    private String auditResult;

    @Schema(description = "[审核意见] 可为NULL")
    private String auditOpinion;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注]", example = "你说的对")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
