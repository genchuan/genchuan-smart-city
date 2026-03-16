package cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 公共机构 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PublicInstitutionRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13737")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "机构主键（UUID）", example = "28678")
    @ExcelProperty("机构主键")
    private String institutionId;

    @Schema(description = "机构名称", example = "李四")
    @ExcelProperty("机构名称")
    private String name;

    @Schema(description = "关联sys_institution_type.id", example = "21137")
    @ExcelProperty("设施类型编码")
    private String institutionTypeId;

    @Schema(description = "机构地址")
    @ExcelProperty("机构地址")
    private String address;

    @Schema(description = "关联sys_area.area_code")
    @ExcelProperty("区域编码")
    private String areaCode;

    @Schema(description = "关联sys_user.id", example = "16838")
    @ExcelProperty("负责人员")
    private String managerId;

    @Schema(description = "关联sys_operation_status.id", example = "11750")
    @ExcelProperty("运行状态编码")
    private String operationStatusId;

    @Schema(description = "保洁达标率")
    @ExcelProperty("保洁达标率")
    private BigDecimal cleaningRate;

    @Schema(description = "问题办结率")
    @ExcelProperty("问题办结率")
    private BigDecimal problemRate;

    @Schema(description = "垃圾清运量（单位：吨）")
    @ExcelProperty("垃圾清运量")
    private BigDecimal wasteVolume;

    @Schema(description = "核查通过率")
    @ExcelProperty("核查通过率")
    private BigDecimal inspectionPassRate;

    @Schema(description = "保洁标准")
    @ExcelProperty("保洁标准")
    private String cleaningStandard;

    @Schema(description = "保洁频次")
    @ExcelProperty("保洁频次")
    private String cleaningFrequency;

    @Schema(description = "保洁时段")
    @ExcelProperty("保洁时段")
    private String cleaningTime;

    @Schema(description = "保洁人员IDs，JSON")
    @ExcelProperty("保洁人员")
    private String cleanerIds;

    @Schema(description = "责任区域")
    @ExcelProperty("责任区域")
    private String responsibilityArea;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}