package cn.iocoder.yudao.module.park.controller.admin.park.user.participatingunit.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 参与单位新增/修改 Request VO")
@Data
public class ParticipatingUnitSaveReqVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "1095")
    private Long id;

    @Schema(description = "[单位名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "[单位名称]不能为空")
    private String unitName;

    @Schema(description = "[单位类型] 如:技术支持/运维服务/合作商户", example = "2")
    private String unitType;

    @Schema(description = "[联系人]")
    private String contactPerson;

    @Schema(description = "[联系电话]")
    private String contactPhone;

    @Schema(description = "[合作内容]")
    private String cooperationContent;

    @Schema(description = "[合作开始时间]")
    private LocalDateTime startTime;

    @Schema(description = "[合作结束时间] 长期合作为NULL")
    private LocalDateTime endTime;

    @Schema(description = "[合作状态] 如:进行中/已终止/待开始", example = "1")
    private String status;

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
