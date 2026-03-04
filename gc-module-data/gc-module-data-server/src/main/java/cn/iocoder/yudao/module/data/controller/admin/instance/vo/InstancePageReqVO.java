package cn.iocoder.yudao.module.data.controller.admin.instance.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 管理部件实例分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InstancePageReqVO extends PageParam {

    @Schema(description = "部件名称", example = "王五")
    private String partName; // 修改：与 DO 的 partName 对齐

    @Schema(description = "16位标识码")
    private String uniqueCode;

    @Schema(description = "关联分类ID", example = "16262")
    private String parentCategoryId; // 修改：与 DO 的 parentCategoryId 对齐

    @Schema(description = "关联网格ID", example = "17369")
    private String gridId;

    @Schema(description = "所在网格")
    private String gridName; // 新增：对应 DO 的 gridName

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "坐标校验标识")
    private Boolean coordVerifyFlag;

    @Schema(description = "坐标信息")
    private String coordinate; // 新增：对应 DO 的 coordinate

    @Schema(description = "关联运行状态ID", example = "19277")
    private String runStatus; // 修改：与 DO 的 runStatus 对齐

    @Schema(description = "主管部门")
    private String deptName; // 修改：与 DO 的 deptName 对齐

    @Schema(description = "关联行政区划代码")
    private String areaCode;

    @Schema(description = "行政区划归属")
    private String areaName; // 新增：对应 DO 的 areaName

    @Schema(description = "关联监测部件ID列表")
    private String monitorIds;

    @Schema(description = "关联监测部件数", example = "4262")
    private Integer monitorCount;

    @Schema(description = "关联事件数", example = "20441")
    private Integer eventCount;

    @Schema(description = "备注", example = "你说的对")
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

    // 新增树形查询参数
    @Schema(description = "树形查询的父节点ID（点击树节点时传入，会查询该节点及其所有子节点下的实例）", example = "1")
    private String treeParentId;

    @Schema(description = "是否包含父节点自身（当treeParentId不为空时有效，默认true）", example = "true")
    private Boolean includeSelf = true;

}