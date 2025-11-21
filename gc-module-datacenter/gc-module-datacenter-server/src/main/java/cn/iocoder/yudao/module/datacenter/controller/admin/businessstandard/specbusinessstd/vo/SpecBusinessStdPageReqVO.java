package cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.specbusinessstd.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 专项业务标准分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SpecBusinessStdPageReqVO extends PageParam {

    @Schema(description = "标准ID，唯一编码，UUID生成", example = "28175")
    private String specBusinessStdId;

    @Schema(description = "标准名称，如《智慧城管事件处置标准》", example = "张三")
    private String stdName;

    @Schema(description = "业务领域代码，取值如01=城管，02=交通，03=环保，业务领域字典表（sys_dict_business_field）")
    private String businessFieldCode;

    @Schema(description = "业务领域名称，与业务领域代码同步，业务领域字典表（sys_dict_business_field）", example = "李四")
    private String businessFieldName;

    @Schema(description = "标准版本，如“V1.0”“V2.1”")
    private String stdVersion;

    @Schema(description = "生效时间，格式：YYYYMMDD")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveTime;

    @Schema(description = "标准核心条款（支持富文本，如“事件处置需在24小时内完成”）")
    private String stdClause;

    @Schema(description = "执行要求，执行该标准的具体要求")
    private String execRequirement;

    @Schema(description = "验收指标，如“处置合格率≥95%”")
    private String acceptIdx;

    @Schema(description = "标准文件ID，关联标准文件ID，文件存储表（sys_file_storage）", example = "13727")
    private String stdFileId;

    @Schema(description = "标准文件路径，标准文件存储路径，文件存储表（sys_file_storage）")
    private String stdFilePath;

    @Schema(description = "标准状态：现行/废止，标识标准当前状态", example = "1")
    private String stdStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“标准等级：市级/区级”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“适用对象：工作人员/设备厂商”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“制定依据：XX政策”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“修订记录：2024年修订”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}