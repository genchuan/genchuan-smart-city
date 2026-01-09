package cn.iocoder.yudao.module.industry.controller.admin.park.asset.garage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 车库信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkGarageRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29018")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14707")
    @ExcelProperty("关联ID")
    private String assetExtendId;

    @Schema(description = "关联车场ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22467")
    @ExcelProperty("关联车场ID")
    private String lotId;

    @Schema(description = "楼层数", requiredMode = Schema.RequiredMode.REQUIRED, example = "25104")
    @ExcelProperty("楼层数")
    private Integer floorCount;

    @Schema(description = "总车位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("总车位数")
    private Integer totalSpace;

    @Schema(description = "当前可用车位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("当前可用车位数")
    private Integer availableSpace;

    @Schema(description = "门禁类型：车牌识别/刷卡/人脸识别", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("门禁类型：车牌识别/刷卡/人脸识别")
    private String accessControlType;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime garageCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime garageUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    @ExcelProperty("业务备注")
    private String garageRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}