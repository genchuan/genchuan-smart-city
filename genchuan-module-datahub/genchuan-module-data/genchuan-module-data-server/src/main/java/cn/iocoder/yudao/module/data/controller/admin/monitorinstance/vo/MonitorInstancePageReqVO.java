package cn.iocoder.yudao.module.data.controller.admin.monitorinstance.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 监测部件实例分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MonitorInstancePageReqVO extends PageParam {

    @Schema(description = "部件名称")
    private String name;

    @Schema(description = "18位标识码")
    private String uniqueCode;

    @Schema(description = "关联分类ID")
    private String categoryId;

    @Schema(description = "所属分类")
    private String categoryName;

    @Schema(description = "关联网格ID")
    private String gridId;

    @Schema(description = "所在网格")
    private String gridName;

    @Schema(description = "坐标信息")
    private String coordinate;

    @Schema(description = "运行状态")
    private String runStatus;

    @Schema(description = "协议类型")
    private String protocol;

    @Schema(description = "安装时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] installTime;

    @Schema(description = "校准周期（天）")
    private Integer calibrateCycle;

    @Schema(description = "下次校准时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] nextCalibrateTime;

    @Schema(description = "最后校准时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastCalibrateTime;

    @Schema(description = "最后数据质量校验时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastCheckTime;

    @Schema(description = "行政区划编码")
    private String areaCode;

    @Schema(description = "行政区划归属")
    private String areaName;

    @Schema(description = "关联管理部件ID")
    private String relatedPartId;

    @Schema(description = "关联管理部件")
    private String relatedPartName;

    @Schema(description = "校准日志")
    private String calibrateLog;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "树形查询的父节点ID（点击树节点时传入，会查询该节点及其所有子节点）", example = "1")
    private String treeParentId;

    @Schema(description = "是否包含父节点自身（当treeParentId不为空时有效，默认true）", example = "true")
    private Boolean includeSelf = true;

}