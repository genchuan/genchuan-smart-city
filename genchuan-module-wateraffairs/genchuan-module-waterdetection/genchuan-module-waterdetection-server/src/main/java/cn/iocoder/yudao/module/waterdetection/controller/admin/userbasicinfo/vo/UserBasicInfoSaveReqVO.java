package cn.iocoder.yudao.module.waterdetection.controller.admin.userbasicinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 用户基础信息登记新增/修改 Request VO")
@Data
public class UserBasicInfoSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "用户编号不能为空")
    private String userCode;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "姓名不能为空")
    private String userName;

    @Schema(description = "身份证号")
    private String idCardNo;

    @Schema(description = "家庭住址")
    private String address;

    @Schema(description = "联系电话")
    private String phone;

    @Schema(description = "开户日期")
    private LocalDateTime openDate;

}