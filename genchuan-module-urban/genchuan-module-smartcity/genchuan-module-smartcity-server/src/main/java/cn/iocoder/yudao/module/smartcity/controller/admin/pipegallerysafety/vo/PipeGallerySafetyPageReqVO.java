package cn.iocoder.yudao.module.smartcity.controller.admin.pipegallerysafety.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管廊安全分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PipeGallerySafetyPageReqVO extends PageParam {

    @Schema(description = "管廊名称", example = "赵六")
    private String pipeGalleryName;

    @Schema(description = "管廊位置")
    private String locationGallery;

    @Schema(description = "管廊编号")
    private String pipeGalleryNumber;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}