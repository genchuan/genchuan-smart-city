package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evttypecfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 事件类型配置新增/修改 Request VO")
@Data
public class EvtTypeCfgSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5437")
    private Long id;

    @Schema(description = "类型ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1833")
    @NotEmpty(message = "类型ID，唯一编码，UUID不能为空")
    private String evtTypeId;

    @Schema(description = "父类型ID，关联本表“事件类型ID”，大类父ID为“0”", requiredMode = Schema.RequiredMode.REQUIRED, example = "25825")
    @NotEmpty(message = "父类型ID，关联本表“事件类型ID”，大类父ID为“0”不能为空")
    private String parentTypeId;

    @Schema(description = "类型层级，1（大类）/2（小类）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "类型层级，1（大类）/2（小类）不能为空")
    private String typeLevel;

    @Schema(description = "类型编码，001 - 999（大类01 - 99、小类001 - 999，扩展类080 - 999倒排），同一父类型下编码唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "类型编码，001 - 999（大类01 - 99、小类001 - 999，扩展类080 - 999倒排），同一父类型下编码唯一不能为空")
    private String typeCode;

    @Schema(description = "类型名称，标准名称，扩展类加“（自定义）”", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "类型名称，标准名称，扩展类加“（自定义）”不能为空")
    private String typeName;

    @Schema(description = "类型说明，描述类型范围，如“占道经营：商户违规占用道路经营”")
    private String typeDesc;

    @Schema(description = "所属区域代码，关联行政区划表，限定区域适用")
    private String regionCode;

    @Schema(description = "所属区域名称，与区域代码同步", example = "李四")
    private String regionName;

    @Schema(description = "启用状态，1（启用）/0（禁用），默认1", example = "2")
    private String enableStatus;

    @Schema(description = "创建人，创建人账号，关联用户信息表(sys_user)")
    private String createUser;

    @Schema(description = "更新人，更新人账号，关联用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“处置时限”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“处置时限”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储参考标准")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储参考标准")
    private String extCommon2;

}