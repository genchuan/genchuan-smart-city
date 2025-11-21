package cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.nationalpol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 国家政策信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class NationalPolRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17385")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "政策ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "9146")
    @ExcelProperty("政策ID，唯一编码，UUID生成")
    private String nationalPolId;

    @Schema(description = "政策标题，政策完整标题，如《“十四五”智慧城市发展规划》", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("政策标题，政策完整标题，如《“十四五”智慧城市发展规划》")
    private String polTitle;

    @Schema(description = "政策文号，政府发布文号（如“发改高技〔2021〕1602号”）")
    @ExcelProperty("政策文号，政府发布文号（如“发改高技〔2021〕1602号”）")
    private String polDocNo;

    @Schema(description = "发布部门代码，发布部门统一社会信用代码，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发布部门代码，发布部门统一社会信用代码，部门信息表（sys_org）")
    private String issueDeptCode;

    @Schema(description = "发布部门名称，与发布部门代码同步，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("发布部门名称，与发布部门代码同步，部门信息表（sys_org）")
    private String issueDeptName;

    @Schema(description = "发布时间")
    @ExcelProperty("发布时间，格式：YYYYMMDD，政策发布日期")
    private LocalDateTime issueTime;

    @Schema(description = "实施时间，格式：YYYYMMDD，政策实施日期（未明确则为空）")
    @ExcelProperty("实施时间，格式：YYYYMMDD，政策实施日期（未明确则为空）")
    private LocalDateTime implementTime;

    @Schema(description = "政策文件ID，关联文件存储表的文件ID，文件存储表（sys_file_storage）", requiredMode = Schema.RequiredMode.REQUIRED, example = "7780")
    @ExcelProperty("政策文件ID，关联文件存储表的文件ID，文件存储表（sys_file_storage）")
    private String polFileId;

    @Schema(description = "政策文件路径，政策文件服务器存储路径，文件存储表（sys_file_storage）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("政策文件路径，政策文件服务器存储路径，文件存储表（sys_file_storage）")
    private String polFilePath;

    @Schema(description = "政策状态：有效/失效，标识政策当前状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("政策状态：有效/失效，标识政策当前状态")
    private String polStatus;

    @Schema(description = "政策摘要，政策核心内容摘要（≤500字）")
    @ExcelProperty("政策摘要，政策核心内容摘要（≤500字）")
    private String polSummary;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    @ExcelProperty("更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“政策分类：发展规划/技术标准”")
    @ExcelProperty("分类扩展字段1，预留，如“政策分类：发展规划/技术标准”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“适用领域：城管/交通/环保”")
    @ExcelProperty("分类扩展字段2，预留，如“适用领域：城管/交通/环保”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“政策来源链接：https://www.gov.cn/...”")
    @ExcelProperty("通用扩展字段1，预留，如“政策来源链接：https://www.gov.cn/...”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“版本号：V1.0”")
    @ExcelProperty("通用扩展字段2，预留，如“版本号：V1.0”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}