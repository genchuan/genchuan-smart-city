package cn.iocoder.yudao.module.industry.controller.admin.park.asset.space.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 车位信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkSpaceRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24853")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25027")
    @ExcelProperty("关联ID")
    private String assetExtendId;

    @Schema(description = "所属车场ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26025")
    @ExcelProperty("所属车场ID")
    private String lotId;

    @Schema(description = "所属车库ID", example = "12268")
    @ExcelProperty("所属车库ID")
    private String garageId;

    @Schema(description = "唯一车位编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("唯一车位编号")
    private String spaceNumber;

    @Schema(description = "车位类型：普通/新能源/残疾人专用/子母位", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("车位类型：普通/新能源/残疾人专用/子母位")
    private String spaceType;

    @Schema(description = "绑定车牌列表")
    @ExcelProperty("绑定车牌列表")
    private String bindCarList;

    @Schema(description = "是否可预约：0-否/1-是", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否可预约：0-否/1-是")
    private Boolean isReservable;

    @Schema(description = "状态：空闲/占用/预约/故障/禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态：空闲/占用/预约/故障/禁用")
    private String status;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime spaceCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime spaceUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    @ExcelProperty("业务备注")
    private String spaceRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}