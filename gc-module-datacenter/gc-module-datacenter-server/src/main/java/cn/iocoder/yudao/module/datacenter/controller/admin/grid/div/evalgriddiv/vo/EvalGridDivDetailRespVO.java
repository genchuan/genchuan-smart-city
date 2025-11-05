package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.evalgriddiv.vo;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo.MngGridSimpleRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 评价网格划分详情 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class EvalGridDivDetailRespVO extends EvalGridDivRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "556")
    private Long id;

    @Schema(description = "评价网格ID", example = "15263")
    private String evalGridId;

    @Schema(description = "评价网格名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    private String evalGridName;

    @Schema(description = "所属乡镇ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10750")
    private String townStreetId;

    @Schema(description = "评价网格类型(A/B/C)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private String gridType;

    @Schema(description = "所含管理网格IDs（字符串形式）", requiredMode = Schema.RequiredMode.REQUIRED)
    private String includedMgIds;

    @Schema(description = "所含管理网格ID列表")
    private List<String> includedMgIdList;

    @Schema(description = "面积(m²)")
    private Integer area;

    @Schema(description = "划分时间")
    private LocalDateTime divTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "所含管理网格列表")
    private List<MngGridSimpleRespVO> mngGridList;

}