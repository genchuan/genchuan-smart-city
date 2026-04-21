package cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "巡查巡检 - 巡检人员新增/修改 Request VO")
@Data
public class InspectUserSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @Schema(description = "所属片区")
    private String area;

    @Schema(description = "绑定设备ID")
    private Long deviceId;

    @Schema(description = "人员状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "人员状态不能为空")
    private String status;

    @Schema(description = "在线状态")
    private String onlineStatus;

    @Schema(description = "最后登录时间")
    private LocalDateTime lastLoginTime;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}