// ParkResDistriRespVO.java  
package cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.resourcedistribution.vo;

import lombok.*;
import java.util.*;
import com.alibaba.excel.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 停车资源分布 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkResDistriRespVO {

    @Schema(description = "主键ID", example = "1024")
    private Long id;

    @Schema(description = "区域编码", example = "110101")
    private String regionCode;

    @Schema(description = "区域名称", example = "朝阳区")
    private String regionName;

    @Schema(description = "统计周期", example = "day")
    private String statCycle;

    @Schema(description = "统计数据时间")
    private Date statTime;

    @Schema(description = "公共停车场数量", example = "50")
    private Integer pubParkCount;

    @Schema(description = "路侧停车场数量", example = "30")
    private Integer roadParkCount;

    @Schema(description = "公共泊位总数", example = "5000")
    private Integer pubTotalSpace;

    @Schema(description = "路侧泊位总数", example = "2000")
    private Integer roadTotalSpace;

    @Schema(description = "资源占比", example = "25.50")
    private Double resRatio;

}