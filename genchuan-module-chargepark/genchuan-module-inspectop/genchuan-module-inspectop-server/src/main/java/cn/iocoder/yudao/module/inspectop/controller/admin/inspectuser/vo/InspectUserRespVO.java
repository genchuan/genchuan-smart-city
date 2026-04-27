package cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "巡查巡检 - 巡检人员 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectUserRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("姓名")
    private String name;

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("手机号")
    private String phone;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("场站ID")
    private Long stationId;

    @Schema(description = "所属片区")
    @ExcelProperty("所属片区")
    private String area;

    @Schema(description = "绑定设备ID")
    @ExcelProperty("绑定设备ID")
    private Long deviceId;

    @Schema(description = "人员状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("人员状态")
    private String status;

    @Schema(description = "在线状态")
    @ExcelProperty("在线状态")
    private String onlineStatus;

    @Schema(description = "最后登录时间")
    @ExcelProperty("最后登录时间")
    private LocalDateTime lastLoginTime;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}