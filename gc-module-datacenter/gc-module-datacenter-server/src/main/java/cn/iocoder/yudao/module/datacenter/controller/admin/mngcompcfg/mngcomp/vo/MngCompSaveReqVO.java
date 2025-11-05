package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.mngcomp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理部件统计新增/修改 Request VO")
@Data
public class MngCompSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11661")
    private Long id;

    @Schema(description = "统计ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "6273")
    @NotEmpty(message = "统计ID，唯一编码，UUID生成不能为空")
    private String statMngCompId;

    @Schema(description = "统计周期，如年/季/月，格式:YYYY/YYYYQn/YYYYMM", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期，如年/季/月，格式:YYYY/YYYYQn/YYYYMM不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年9月”", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "统计周期名称，如“2025年9月”不能为空")
    private String statCycleName;

    @Schema(description = "行政区划代码，符合GB/T 2260，关联行政区划表(sys_area)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码，符合GB/T 2260，关联行政区划表(sys_area)不能为空")
    private String areaCode;

    @Schema(description = "行政区划名称，与代码同步，关联行政区划表(sys_area)", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "行政区划名称，与代码同步，关联行政区划表(sys_area)不能为空")
    private String areaName;

    @Schema(description = "部件大类ID，关联管理部件大类ID，关联管理部件大类表(biz_mng_comp_major)", requiredMode = Schema.RequiredMode.REQUIRED, example = "29481")
    @NotEmpty(message = "部件大类ID，关联管理部件大类ID，关联管理部件大类表(biz_mng_comp_major)不能为空")
    private String compMajorId;

    @Schema(description = "部件大类名称，与大类ID同步，关联管理部件大类表(biz_mng_comp_major)", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "部件大类名称，与大类ID同步，关联管理部件大类表(biz_mng_comp_major)不能为空")
    private String compMajorName;

    @Schema(description = "部件小类ID，关联管理部件小类ID（钻取时必填），关联管理部件小类表(biz_mng_comp_minor)", example = "26995")
    private String compMinorId;

    @Schema(description = "部件小类名称，与小类ID同步，关联管理部件小类表(biz_mng_comp_minor)", example = "张三")
    private String compMinorName;

    @Schema(description = "部件总数，该维度下部件总数，关联管理部件表(biz_mng_comp)", example = "22103")
    private Integer totalCompCount;

    @Schema(description = "完好部件数，状态为“完好”的数量，关联管理部件表(biz_mng_comp)", example = "28318")
    private Integer normalCompCount;

    @Schema(description = "破损部件数，状态为“破损”的数量，关联管理部件表(biz_mng_comp)", example = "9007")
    private Integer damagedCompCount;

    @Schema(description = "丢失部件数，状态为“丢失”的数量，关联管理部件表(biz_mng_comp)", example = "22188")
    private Integer lostCompCount;

    @Schema(description = "废弃部件数，状态为“废弃”的数量，关联管理部件表(biz_mng_comp)", example = "18438")
    private Integer discardedCompCount;

    @Schema(description = "新增部件数，统计周期内新增数量，关联管理部件表(biz_mng_comp)", example = "26024")
    private Integer newCompCount;

    @Schema(description = "更新部件数，统计周期内更新数量，关联管理部件表(biz_mng_comp)", example = "26997")
    private Integer updateCompCount;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}