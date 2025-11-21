package cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.locallevelexp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 本级经验分享新增/修改 Request VO")
@Data
public class LocalLevelExpSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23089")
    private Long id;

    @Schema(description = "经验ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "19671")
    @NotEmpty(message = "经验ID，唯一编码，UUID生成不能为空")
    private String localLevelExpId;

    @Schema(description = "经验标题，如“XX市东城区网格划分经验”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "经验标题，如“XX市东城区网格划分经验”不能为空")
    private String expTitle;

    @Schema(description = "行政区划代码，本级行政区划代码，如330106=杭州市西湖区，行政区划表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码，本级行政区划代码，如330106=杭州市西湖区，行政区划表（sys_area）不能为空")
    private String regionCode;

    @Schema(description = "行政区划名称，与行政区划代码同步，行政区划表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "行政区划名称，与行政区划代码同步，行政区划表（sys_area）不能为空")
    private String regionName;

    @Schema(description = "关联部门代码，经验来源部门代码，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "关联部门代码，经验来源部门代码，部门信息表（sys_org）不能为空")
    private String deptCode;

    @Schema(description = "关联部门名称，与关联部门代码同步，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "关联部门名称，与关联部门代码同步，部门信息表（sys_org）不能为空")
    private String deptName;

    @Schema(description = "经验详细内容，支持富文本，如“网格划分原则：按人口密度、地理边界划分”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "经验详细内容，支持富文本，如“网格划分原则：按人口密度、地理边界划分”不能为空")
    private String expContent;

    @Schema(description = "经验实施步骤，如“1.调研区域人口；2.绘制地理边界；3.划分单元网格”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "经验实施步骤，如“1.调研区域人口；2.绘制地理边界；3.划分单元网格”不能为空")
    private String implementationSteps;

    @Schema(description = "实施成果，如“划分网格200个，事件处置效率提升30%”")
    private String achievementData;

    @Schema(description = "附件ID，关联经验附件ID（如划分方案、成果报表），文件存储表（sys_file_storage）", example = "26643")
    private String attachId;

    @Schema(description = "附件存储路径，文件存储表（sys_file_storage）")
    private String attachPath;

    @Schema(description = "发布状态：已发布/草稿，标识经验是否公开", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "发布状态：已发布/草稿，标识经验是否公开不能为空")
    private String publishStatus;

    @Schema(description = "分享次数，用户分享经验的次数，默认0", requiredMode = Schema.RequiredMode.REQUIRED, example = "14923")
    @NotNull(message = "分享次数，用户分享经验的次数，默认0不能为空")
    private Integer shareCount;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人，创建人ID，用户信息表（sys_user）不能为空")
    private String createUser;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“经验领域：网格管理/事件处置”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“分享范围：本级/下级”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“实施时间：2025年1月”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“最后分享时间：2025-10-10”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}