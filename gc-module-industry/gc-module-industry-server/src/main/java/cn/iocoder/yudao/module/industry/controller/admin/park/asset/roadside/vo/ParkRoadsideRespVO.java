package cn.iocoder.yudao.module.industry.controller.admin.park.asset.roadside.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 路侧泊位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkRoadsideRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13879")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7766")
    @ExcelProperty("关联ID")
    private String assetExtendId;

    @Schema(description = "道路名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("道路名称")
    private String roadName;

    @Schema(description = "唯一泊位编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("唯一泊位编号")
    private String berthNumber;

    @Schema(description = "泊位类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("泊位类型")
    private String berthType;

    @Schema(description = "所属计费桩", example = "11757")
    @ExcelProperty("所属计费桩")
    private String feePileId;

    @Schema(description = "占用状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("占用状态")
    private String status;

    @Schema(description = "上次占用时间")
    @ExcelProperty("上次占用时间")
    private LocalDateTime lastOccupyTime;

    @Schema(description = "上次释放时间")
    @ExcelProperty("上次释放时间")
    private LocalDateTime lastReleaseTime;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime roadsideCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime roadsideUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    @ExcelProperty("业务备注")
    private String roadsideRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}