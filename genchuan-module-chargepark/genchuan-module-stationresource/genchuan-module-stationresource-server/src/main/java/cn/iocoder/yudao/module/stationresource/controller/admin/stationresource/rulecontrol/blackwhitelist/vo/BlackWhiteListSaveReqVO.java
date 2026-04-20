package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 黑白名单新增/修改 Request VO")
@Data
public class BlackWhiteListSaveReqVO {

    @Schema(description = "[主键ID] 主键，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "13688")
    private Long id;

    @Schema(description = "[车牌号码] 唯一车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[车牌号码] 唯一车牌号不能为空")
    private String plateNo;

    @Schema(description = "[名单类型] 如：白名单/黑名单", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[名单类型] 如：白名单/黑名单不能为空")
    private String type;

    @Schema(description = "[细分类型] 如：公务车/业主车/残疾人车/欠费车/逃费车", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[细分类型] 如：公务车/业主车/残疾人车/欠费车/逃费车不能为空")
    private String subType;

    @Schema(description = "[生效时间] 名单生效开始时间")
    private LocalDateTime startTime;

    @Schema(description = "[失效时间] 名单失效截止时间")
    private LocalDateTime endTime;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[状态] 如：待生效/已生效/已禁用不能为空")
    private String status;

    @Schema(description = "[审核时间] 审核通过的时间")
    private LocalDateTime auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user", example = "7329")
    private Long auditUserId;

    @Schema(description = "[拦截次数] 黑名单车辆被拦截次数", example = "3994")
    private Integer interceptCount;

    @Schema(description = "[证件信息] 相关证件信息描述")
    private String certInfo;

    @Schema(description = "[备注] 扩展说明", example = "你说的对")
    private String remark;

    @Schema(description = "[备用字段1] 预留扩展")
    private String reserve1;

    @Schema(description = "[备用字段2] 预留扩展")
    private String reserve2;

}
