package cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.localregulation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 地方法规信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class LocalRegulationRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17238")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "法规ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "18250")
    @ExcelProperty("法规ID，唯一编码，UUID生成")
    private String localRegulationId;

    @Schema(description = "法规标题，法规完整标题，如《XX市一网统管实施细则》", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("法规标题，法规完整标题，如《XX市一网统管实施细则》")
    private String regulationTitle;

    @Schema(description = "法规文号，地方发布文号（如“XX政发〔2024〕5号”）")
    @ExcelProperty("法规文号，地方发布文号（如“XX政发〔2024〕5号”）")
    private String regulationDocNo;

    @Schema(description = "行政区划代码，符合GB/T 2260，如330100=杭州市，行政区划表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行政区划代码，符合GB/T 2260，如330100=杭州市，行政区划表（sys_area）")
    private String regionCode;

    @Schema(description = "行政区划名称，与行政区划代码同步，行政区划表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("行政区划名称，与行政区划代码同步，行政区划表（sys_area）")
    private String regionName;

    @Schema(description = "发布部门代码，发布部门统一社会信用代码，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发布部门代码，发布部门统一社会信用代码，部门信息表（sys_org）")
    private String issueDeptCode;

    @Schema(description = "发布部门名称，与发布部门代码同步，部门信息表（sys_org）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("发布部门名称，与发布部门代码同步，部门信息表（sys_org）")
    private String issueDeptName;

    @Schema(description = "发布时间，格式：YYYYMMDD", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发布时间，格式：YYYYMMDD")
    private LocalDateTime issueTime;

    @Schema(description = "实施时间，格式：YYYYMMDD，可为空")
    @ExcelProperty("实施时间，格式：YYYYMMDD，可为空")
    private LocalDateTime implementTime;

    @Schema(description = "法规文件ID，关联文件存储表的文件ID，文件存储表（sys_file_storage）", requiredMode = Schema.RequiredMode.REQUIRED, example = "10560")
    @ExcelProperty("法规文件ID，关联文件存储表的文件ID，文件存储表（sys_file_storage）")
    private String regulationFileId;

    @Schema(description = "法规文件路径，法规文件存储路径，文件存储表（sys_file_storage）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("法规文件路径，法规文件存储路径，文件存储表（sys_file_storage）")
    private String regulationFilePath;

    @Schema(description = "法规状态：有效/失效，标识法规当前状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("法规状态：有效/失效，标识法规当前状态")
    private String regulationStatus;

    @Schema(description = "法规摘要，法规核心内容摘要")
    @ExcelProperty("法规摘要，法规核心内容摘要")
    private String regulationSummary;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    @ExcelProperty("更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“法规类型：管理办法/实施细则”")
    @ExcelProperty("分类扩展字段1，预留，如“法规类型：管理办法/实施细则”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“适用层级：市级/区级”")
    @ExcelProperty("分类扩展字段2，预留，如“适用层级：市级/区级”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“地方政策链接”")
    @ExcelProperty("通用扩展字段1，预留，如“地方政策链接”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“修订记录：2024年第一次修订”")
    @ExcelProperty("通用扩展字段2，预留，如“修订记录：2024年第一次修订”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}