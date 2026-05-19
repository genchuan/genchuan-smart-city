package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingspace.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import cn.idev.excel.annotation.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车位信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkingSpaceRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "车位编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车位编号")
    private String spaceCode;

    @Schema(description = "停车场名称")
    @ExcelProperty("停车场名称")
    private String parkName;

    @Schema(description = "车位类型（固定/临时）")
    @ExcelProperty("车位类型")
    private String spaceType;

    @Schema(description = "车位状态（空闲/占用/预约中）")
    @ExcelProperty("车位状态")
    private String spaceStatus;

    @Schema(description = "租用信息")
    @ExcelProperty("租用信息")
    private String rentInfo;

    @Schema(description = "预约用户")
    @ExcelProperty("预约用户")
    private String orderUser;

    @Schema(description = "使用时长（分钟）")
    @ExcelProperty("使用时长")
    private Integer useDuration;

    @Schema(description = "操作人账号")
    @ExcelProperty("操作人账号")
    private String handleUser;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
