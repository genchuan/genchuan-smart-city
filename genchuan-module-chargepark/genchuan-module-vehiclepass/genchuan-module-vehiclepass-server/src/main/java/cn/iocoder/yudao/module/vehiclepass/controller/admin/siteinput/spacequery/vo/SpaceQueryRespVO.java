package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 泊位查询 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SpaceQueryRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21604")
    @ExcelProperty("编号")
    private Long id;

    @Schema(description = "泊位编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("泊位编号")
    private String spaceNo;

    @Schema(description = "查询时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("查询时间")
    private LocalDateTime queryTime;

    @Schema(description = "查询人ID，关联芋道用户表system_user", requiredMode = Schema.RequiredMode.REQUIRED, example = "17114")
    @ExcelProperty("查询人ID")
    private Long queryUserId;

    @Schema(description = "查询人名称")
    @ExcelProperty("查询人名称")
    private String queryUserName;

    @Schema(description = "片区ID，关联片区表", requiredMode = Schema.RequiredMode.REQUIRED, example = "10828")
    @ExcelProperty("片区ID")
    private Long areaId;

    @Schema(description = "片区名称")
    @ExcelProperty("片区名称")
    private String areaName;

    @Schema(description = "泊位状态：空闲/占用，关联字典space_query_space_status", example = "1")
    @ExcelProperty("泊位状态")
    private String spaceStatus;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者，创建人账号/姓名")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者，更新人账号/姓名")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}