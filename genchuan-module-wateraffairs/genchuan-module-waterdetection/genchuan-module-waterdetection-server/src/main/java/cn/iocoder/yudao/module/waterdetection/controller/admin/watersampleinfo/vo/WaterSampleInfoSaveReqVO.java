package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampleinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampleinfo.WaterSampleResultDO;

@Schema(description = "管理后台 - 水质检测信息新增/修改 Request VO")
@Data
public class WaterSampleInfoSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "样品编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "样品编号不能为空")
    private String sampleNo;

    @Schema(description = "样品类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "样品类型不能为空")
    private String sampleType;

    @Schema(description = "样品名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "样品名称不能为空")
    private String sampleName;

    @Schema(description = "检测性质", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "检测性质不能为空")
    private String sampleNature;

    @Schema(description = "样品状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "样品状态不能为空")
    private String sampleStatus;

    @Schema(description = "采样方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "采样方式不能为空")
    private String deliveryMethod;

    @Schema(description = "采样时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "采样时间不能为空")
    private LocalDateTime samplingDate;

    @Schema(description = "采样地点", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "采样地点不能为空")
    private String samplingLocation;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "检测开始日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "检测开始日期不能为空")
    private LocalDateTime startDate;

    @Schema(description = "检测结束日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "检测结束日期不能为空")
    private LocalDateTime endDate;

    @Schema(description = "检测依据")
    private String standard;

    @Schema(description = "结果报告")
    private String sampleResult;

    @Schema(description = "检测结论")
    private String conclusion;

}