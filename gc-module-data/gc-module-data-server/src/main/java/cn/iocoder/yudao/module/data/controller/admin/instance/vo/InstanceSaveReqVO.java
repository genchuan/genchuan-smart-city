package cn.iocoder.yudao.module.data.controller.admin.instance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 管理部件实例新增/修改 Request VO")
@Data
public class InstanceSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17931")
    private Long id;

    @Schema(description = "部件名称", example = "王五")
    private String name;

    @Schema(description = "16位标识码")
    private String uniqueCode;

    @Schema(description = "关联分类ID", example = "16262")
    private String categoryId;

    @Schema(description = "关联网格ID", example = "17369")
    private String gridId;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "坐标校验标识")
    private Boolean coordVerifyFlag;

    @Schema(description = "关联运行状态ID", example = "19277")
    private String runStatusId;

    @Schema(description = "关联部门ID", example = "10144")
    private String deptId;

    @Schema(description = "关联行政区划代码")
    private String areaCode;

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

}