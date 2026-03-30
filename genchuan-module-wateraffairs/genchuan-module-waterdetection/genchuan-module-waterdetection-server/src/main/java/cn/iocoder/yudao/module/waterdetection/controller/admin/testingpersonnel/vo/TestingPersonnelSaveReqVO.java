package cn.iocoder.yudao.module.waterdetection.controller.admin.testingpersonnel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 检测人员信息管理新增/修改 Request VO")
@Data
public class TestingPersonnelSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "人员编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "人员编号不能为空")
    private String staffNo;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "姓名不能为空")
    private String staffName;

    @Schema(description = "职称")
    private String position;

    @Schema(description = "资格证书编号")
    private String certificateNo;

    @Schema(description = "培训记录")
    private String trainingRecord;

    @Schema(description = "所属机构编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "所属机构编号不能为空")
    private String agencyCode;

}