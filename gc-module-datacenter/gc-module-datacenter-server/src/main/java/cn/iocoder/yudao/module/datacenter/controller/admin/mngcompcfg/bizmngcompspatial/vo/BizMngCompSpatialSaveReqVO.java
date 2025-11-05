package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompspatial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理部件空间数据新增/修改 Request VO")
@Data
public class BizMngCompSpatialSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17097")
    private Long id;

    @Schema(description = "空间数据ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "13035")
    @NotEmpty(message = "空间数据ID，唯一编码，UUID生成不能为空")
    private String mngCompSpatialId;

    @Schema(description = "关联管理部件ID，一对一，关联管理部件表(gc_biz_mng_comp)", requiredMode = Schema.RequiredMode.REQUIRED, example = "5147")
    @NotEmpty(message = "关联管理部件ID，一对一，关联管理部件表(gc_biz_mng_comp)不能为空")
    private String mngCompId;

    @Schema(description = "关联部件名称，与部件ID同步，不可改，关联管理部件表(gc_biz_mng_comp)", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "关联部件名称，与部件ID同步，不可改，关联管理部件表(gc_biz_mng_comp)不能为空")
    private String compName;

    @Schema(description = "坐标系类型，固定，符合国标，2000国家大地坐标系", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "坐标系类型，固定，符合国标，2000国家大地坐标系不能为空")
    private String coordSystem;

    @Schema(description = "坐标X，经度，保留2位小数，范围 - 180.00至180.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "坐标X，经度，保留2位小数，范围 - 180.00至180.00不能为空")
    private BigDecimal coordX;

    @Schema(description = "坐标Y，纬度，保留2位小数，范围 - 90.00至90.00", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "坐标Y，纬度，保留2位小数，范围 - 90.00至90.00不能为空")
    private BigDecimal coordY;

    @Schema(description = "高程，可选，米，1985国家高程基准，保留3位小数")
    private BigDecimal elevation;

    @Schema(description = "定位精度，米，±0.5/±1.0/±10.0，定位精度等级对应的误差", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "定位精度，米，±0.5/±1.0/±10.0，定位精度等级对应的误差不能为空")
    private BigDecimal accuracy;

    @Schema(description = "定位精度等级，A/B/C，A类±0.5m、B类±1.0m、C类±10.0m", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "定位精度等级，A/B/C，A类±0.5m、B类±1.0m、C类±10.0m不能为空")
    private String accuracyLevel;

    @Schema(description = "测绘单位，测绘实施单位")
    private String surveyUnit;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}