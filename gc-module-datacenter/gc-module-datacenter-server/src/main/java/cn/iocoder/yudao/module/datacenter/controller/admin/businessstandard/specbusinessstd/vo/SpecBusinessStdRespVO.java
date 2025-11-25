package cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.specbusinessstd.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 专项业务标准 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SpecBusinessStdRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15464")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "标准ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "28175")
    @ExcelProperty("标准ID，唯一编码，UUID生成")
    private String specBusinessStdId;

    @Schema(description = "标准名称，如《智慧城管事件处置标准》", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("标准名称，如《智慧城管事件处置标准》")
    private String stdName;

    @Schema(description = "业务领域代码，取值如01=城管，02=交通，03=环保，业务领域字典表（sys_dict_business_field）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("业务领域代码，取值如01=城管，02=交通，03=环保，业务领域字典表（sys_dict_business_field）")
    private String businessFieldCode;

    @Schema(description = "业务领域名称，与业务领域代码同步，业务领域字典表（sys_dict_business_field）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("业务领域名称，与业务领域代码同步，业务领域字典表（sys_dict_business_field）")
    private String businessFieldName;

    @Schema(description = "标准版本，如“V1.0”“V2.1”", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("标准版本，如“V1.0”“V2.1”")
    private String stdVersion;

    @Schema(description = "生效时间，格式：YYYYMMDD", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生效时间，格式：YYYYMMDD")
    private LocalDateTime effectiveTime;

    @Schema(description = "标准核心条款（支持富文本，如“事件处置需在24小时内完成”）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("标准核心条款（支持富文本，如“事件处置需在24小时内完成”）")
    private String stdClause;

    @Schema(description = "执行要求，执行该标准的具体要求")
    @ExcelProperty("执行要求，执行该标准的具体要求")
    private String execRequirement;

    @Schema(description = "验收指标，如“处置合格率≥95%”")
    @ExcelProperty("验收指标，如“处置合格率≥95%”")
    private String acceptIdx;

    @Schema(description = "标准文件ID，关联标准文件ID，文件存储表（sys_file_storage）", example = "13727")
    @ExcelProperty("标准文件ID，关联标准文件ID，文件存储表（sys_file_storage）")
    private String stdFileId;

    @Schema(description = "标准文件路径，标准文件存储路径，文件存储表（sys_file_storage）")
    @ExcelProperty("标准文件路径，标准文件存储路径，文件存储表（sys_file_storage）")
    private String stdFilePath;

    @Schema(description = "标准状态：现行/废止，标识标准当前状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("标准状态：现行/废止，标识标准当前状态")
    private String stdStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    @ExcelProperty("更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“标准等级：市级/区级”")
    @ExcelProperty("分类扩展字段1，预留，如“标准等级：市级/区级”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“适用对象：工作人员/设备厂商”")
    @ExcelProperty("分类扩展字段2，预留，如“适用对象：工作人员/设备厂商”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“制定依据：XX政策”")
    @ExcelProperty("通用扩展字段1，预留，如“制定依据：XX政策”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“修订记录：2024年修订”")
    @ExcelProperty("通用扩展字段2，预留，如“修订记录：2024年修订”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}