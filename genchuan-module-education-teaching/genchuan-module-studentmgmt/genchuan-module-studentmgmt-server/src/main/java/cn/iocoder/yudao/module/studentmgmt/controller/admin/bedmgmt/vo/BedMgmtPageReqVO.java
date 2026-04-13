package cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 床位管理分页 Request VO")
@Data
public class BedMgmtPageReqVO extends PageParam {

    @Schema(description = "楼栋")
    private String building;

    @Schema(description = "楼层")
    private Integer floor;

    @Schema(description = "房间号")
    private String roomNum;

    @Schema(description = "床位号")
    private String bedNum;

    @Schema(description = "学生 ID", example = "28082")
    private Long studentId;

    @Schema(description = "分配时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] assignTime;

    @Schema(description = "调整时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] adjustTime;

    @Schema(description = "状态：未分配/已分配", example = "2")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}