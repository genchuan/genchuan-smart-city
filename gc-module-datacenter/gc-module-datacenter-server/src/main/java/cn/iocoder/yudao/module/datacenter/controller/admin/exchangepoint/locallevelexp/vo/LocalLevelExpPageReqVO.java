package cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.locallevelexp.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 本级经验分享分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LocalLevelExpPageReqVO extends PageParam {

    @Schema(description = "经验ID，唯一编码，UUID生成", example = "19671")
    private String localLevelExpId;

    @Schema(description = "经验标题，如“XX市东城区网格划分经验”")
    private String expTitle;

    @Schema(description = "行政区划代码，本级行政区划代码，如330106=杭州市西湖区，行政区划表（sys_area）")
    private String regionCode;

    @Schema(description = "行政区划名称，与行政区划代码同步，行政区划表（sys_area）", example = "芋艿")
    private String regionName;

    @Schema(description = "关联部门代码，经验来源部门代码，部门信息表（sys_org）")
    private String deptCode;

    @Schema(description = "关联部门名称，与关联部门代码同步，部门信息表（sys_org）", example = "赵六")
    private String deptName;

    @Schema(description = "经验详细内容，支持富文本，如“网格划分原则：按人口密度、地理边界划分”")
    private String expContent;

    @Schema(description = "经验实施步骤，如“1.调研区域人口；2.绘制地理边界；3.划分单元网格”")
    private String implementationSteps;

    @Schema(description = "实施成果，如“划分网格200个，事件处置效率提升30%”")
    private String achievementData;

    @Schema(description = "附件ID，关联经验附件ID（如划分方案、成果报表），文件存储表（sys_file_storage）", example = "26643")
    private String attachId;

    @Schema(description = "附件存储路径，文件存储表（sys_file_storage）")
    private String attachPath;

    @Schema(description = "发布状态：已发布/草稿，标识经验是否公开", example = "2")
    private String publishStatus;

    @Schema(description = "分享次数，用户分享经验的次数，默认0", example = "14923")
    private Integer shareCount;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

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