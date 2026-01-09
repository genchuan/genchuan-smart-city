package cn.iocoder.yudao.module.smartcity.controller.admin.patrolpersonnel.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查人员新增/修改 Request VO")
@Data
public class PatrolPersonnelSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "27301")
    private Long id;

    @Schema(description = "编号")
    private String number;

    @Schema(description = "姓名", example = "赵六")
    private String fullName;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "年龄")
    private String age;

    @Schema(description = "联系方式")
    private String contactInformation;

    @Schema(description = "上岗时间证书编号")
    private LocalDateTime certificateTime;

    @Schema(description = "巡查区域备注")
    private String inspectionRemarks;

}