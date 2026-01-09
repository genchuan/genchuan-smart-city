package cn.iocoder.yudao.module.smartcity.controller.admin.maintenancepersonnel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 养护人员新增/修改 Request VO")
@Data
public class MaintenancePersonnelSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15874")
    private Long id;

    @Schema(description = "人员编号", example = "7402")
    private String personnelId;

    @Schema(description = "人员姓名", example = "赵六")
    private String personnelName;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "联系方式")
    private String contactInformation;

    @Schema(description = "身份证号")
    private String idNumber;

    @Schema(description = "养护地块")
    private String maintainTheLandParcel;

}