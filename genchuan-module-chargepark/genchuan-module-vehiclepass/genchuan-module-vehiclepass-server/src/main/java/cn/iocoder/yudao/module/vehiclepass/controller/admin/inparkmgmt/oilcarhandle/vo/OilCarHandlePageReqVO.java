package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 油车占位处置分页 Request VO")
@Data
public class OilCarHandlePageReqVO extends PageParam {

    @Schema(description = "车牌")
    private String plateNo;

    @Schema(description = "车位ID，关联车位表", example = "27831")
    private Long spaceId;

    @Schema(description = "识别时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] identifyTime;

    @Schema(description = "占位类型：燃油车占位 / 其他，关联字典oil_car_handle_occupy_type", example = "1")
    private String occupyType;

    @Schema(description = "处置状态：未处理 / 处理中 / 已关闭，关联字典oil_car_handle_status", example = "1")
    private String status;

    @Schema(description = "场站ID，关联场站表", example = "8546")
    private Long stationId;

    @Schema(description = "处置人ID，关联system_user用户表", example = "15483")
    private Long handleUserId;

    @Schema(description = "处置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] handleTime;

    @Schema(description = "处置方式")
    private String handleMethod;

    @Schema(description = "忽略理由", example = "不喜欢")
    private String ignoreReason;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}