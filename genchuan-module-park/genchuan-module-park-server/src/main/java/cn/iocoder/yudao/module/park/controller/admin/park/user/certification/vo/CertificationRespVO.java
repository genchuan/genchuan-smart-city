package cn.iocoder.yudao.module.park.controller.admin.park.user.certification.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 认证记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CertificationRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "22764")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[用户ID] 关联park_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11827")
    @ExcelProperty("[用户ID] 关联park_user.id")
    private Long userId;

    @Schema(description = "[用户类型] 如:个人/企业", example = "2")
    @ExcelProperty("[用户类型] 如:个人/企业")
    private String userType;

    @Schema(description = "[认证类型] 如:身份认证/企业认证", example = "2")
    @ExcelProperty("[认证类型] 如:身份认证/企业认证")
    private String certType;

    @Schema(description = "[认证材料] JSON格式varchar")
    @ExcelProperty("[认证材料] JSON格式varchar")
    private String certFiles;

    @Schema(description = "[身份证号] 个人认证")
    @ExcelProperty("[身份证号] 个人认证")
    private String idCard;

    @Schema(description = "[统一社会信用代码] 企业认证")
    @ExcelProperty("[统一社会信用代码] 企业认证")
    private String creditCode;

    @Schema(description = "[申请时间]")
    @ExcelProperty("[申请时间]")
    private LocalDateTime applyTime;

    @Schema(description = "[审核人ID] 关联park_user.id")
    @ExcelProperty("[审核人ID] 关联park_user.id")
    private Long auditBy;

    @Schema(description = "[审核时间]")
    @ExcelProperty("[审核时间]")
    private LocalDateTime auditTime;

    @Schema(description = "[审核结果] 如:通过/驳回")
    @ExcelProperty("[审核结果] 如:通过/驳回")
    private String auditResult;

    @Schema(description = "[审核意见] 可为NULL")
    @ExcelProperty("[审核意见] 可为NULL")
    private String auditOpinion;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "你说的对")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
