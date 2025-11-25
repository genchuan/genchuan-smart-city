package cn.iocoder.yudao.module.datacenter.controller.admin.policylegislation.industrystd.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 行业规范信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class IndustryStdRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27608")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "规范ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "10546")
    @ExcelProperty("规范ID，唯一编码，UUID生成")
    private String industryStdId;

    @Schema(description = "规范标题，规范完整标题，如《城市物联网设备通用规范》", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("规范标题，规范完整标题，如《城市物联网设备通用规范》")
    private String stdTitle;

    @Schema(description = "行业分类代码，取值如01=物联网，02=城管，03=交通，行业分类字典表（sys_dict_industry_type）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行业分类代码，取值如01=物联网，02=城管，03=交通，行业分类字典表（sys_dict_industry_type）")
    private String industryTypeCode;

    @Schema(description = "行业分类名称，与行业分类代码同步，行业分类字典表（sys_dict_industry_type）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("行业分类名称，与行业分类代码同步，行业分类字典表（sys_dict_industry_type）")
    private String industryTypeName;

    @Schema(description = "规范编号，行业标准编号（如“GB/T 39409-2020”）")
    @ExcelProperty("规范编号，行业标准编号（如“GB/T 39409-2020”）")
    private String stdNo;

    @Schema(description = "发布机构，规范发布机构（如“中国城市科学研究会”）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发布机构，规范发布机构（如“中国城市科学研究会”）")
    private String issueOrg;

    @Schema(description = "发布时间，格式：YYYYMMDD", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发布时间，格式：YYYYMMDD")
    private LocalDateTime issueTime;

    @Schema(description = "规范文件ID，关联文件存储表的文件ID，文件存储表（sys_file_storage）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1567")
    @ExcelProperty("规范文件ID，关联文件存储表的文件ID，文件存储表（sys_file_storage）")
    private String stdFileId;

    @Schema(description = "规范文件路径，规范文件存储路径，文件存储表（sys_file_storage）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("规范文件路径，规范文件存储路径，文件存储表（sys_file_storage）")
    private String stdFilePath;

    @Schema(description = "规范状态：现行/废止，标识规范当前状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("规范状态：现行/废止，标识规范当前状态")
    private String stdStatus;

    @Schema(description = "核心条款，规范核心条款摘要（≤1000字）")
    @ExcelProperty("核心条款，规范核心条款摘要（≤1000字）")
    private String coreClause;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    @ExcelProperty("更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“规范等级：国家/行业”")
    @ExcelProperty("分类扩展字段1，预留，如“规范等级：国家/行业”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“适用场景：设备采购/数据录入”")
    @ExcelProperty("分类扩展字段2，预留，如“适用场景：设备采购/数据录入”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“规范下载链接”")
    @ExcelProperty("通用扩展字段1，预留，如“规范下载链接”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“替代旧规范编号”")
    @ExcelProperty("通用扩展字段2，预留，如“替代旧规范编号”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}