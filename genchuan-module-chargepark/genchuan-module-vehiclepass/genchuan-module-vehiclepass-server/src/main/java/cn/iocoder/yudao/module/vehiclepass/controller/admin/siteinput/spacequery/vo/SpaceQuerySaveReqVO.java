package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 泊位查询新增/修改 Request VO")
@Data
public class SpaceQuerySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21604")
    private Long id;

    @Schema(description = "泊位编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "泊位编号不能为空")
    private String spaceNo;

    @Schema(description = "查询时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "查询时间不能为空")
    private LocalDateTime queryTime;

    @Schema(description = "查询人ID，关联芋道用户表system_user", requiredMode = Schema.RequiredMode.REQUIRED, example = "17114")
    @NotNull(message = "查询人ID，关联芋道用户表system_user不能为空")
    private Long queryUserId;

    @Schema(description = "片区ID，关联片区表", requiredMode = Schema.RequiredMode.REQUIRED, example = "10828")
    @NotNull(message = "片区ID，关联片区表不能为空")
    private Long areaId;

    @Schema(description = "泊位状态：空闲/占用，关联字典space_query_space_status", example = "1")
    private String spaceStatus;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}