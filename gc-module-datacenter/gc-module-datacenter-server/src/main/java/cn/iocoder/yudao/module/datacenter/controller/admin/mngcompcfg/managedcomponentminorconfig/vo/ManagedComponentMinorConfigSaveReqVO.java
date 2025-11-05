package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.managedcomponentminorconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理部件小类配置新增/修改 Request VO")
@Data
public class ManagedComponentMinorConfigSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7266")
    private Long id;

    @Schema(description = "小类ID，唯一编码，采用UUID", example = "23206")
    private String minorId;

    @Schema(description = "关联的大类ID", example = "10001")
    private String majorId;

    @Schema(description = "小类代码，001-999")
    private String minorCode;

    @Schema(description = "小类名称，如上水井盖/污水井盖等", example = "赵六")
    private String minorName;

    @Schema(description = "小类说明，描述具体定义及特征")
    private String minorDesc;

    @Schema(description = "主管部门统一社会信用代码")
    private String deptCode;

    @Schema(description = "主管部门全称", example = "李四")
    private String deptName;

    @Schema(description = "是否扩展类：0-标准类/1-扩展类")
    private String isExtend;

    @Schema(description = "创建人ID")
    private String createUser;

    @Schema(description = "更新人ID")
    private String updateUser;

    @Schema(description = "扩展字段1")
    private String extField1;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}