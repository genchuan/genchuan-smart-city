package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 片区信息新增/修改 Request VO")
@Data
public class AreaInfoSaveReqVO {

    @Schema(description = "[主键ID] 主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16304")
    private Long id;

    @Schema(description = "[片区编号] 唯一标识片区编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[片区编号] 唯一标识片区编号不能为空")
    private String areaNo;

    @Schema(description = "[片区名称] 片区名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "[片区名称] 片区名称不能为空")
    private String name;

    @Schema(description = "[上级片区ID] 上级片区ID", example = "6415")
    private Long parentId;

    @Schema(description = "[省份] 省份", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[省份] 省份不能为空")
    private String province;

    @Schema(description = "[城市] 城市", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[城市] 城市不能为空")
    private String city;

    @Schema(description = "[区县] 区县", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[区县] 区县不能为空")
    private String district;

    @Schema(description = "[详细地址] 详细地址")
    private String address;

    @Schema(description = "[负责人ID] 关联芋道用户表system_user", example = "18361")
    private Long leaderId;

    @Schema(description = "[联系电话] 联系电话")
    private String phone;

    @Schema(description = "[关联场站数] 关联场站数", example = "3066")
    private Integer stationCount;

    @Schema(description = "[状态] 如:未生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[状态] 如:未生效/已生效/已禁用不能为空")
    private String status;

    @Schema(description = "[备注] 备注", example = "你说的对")
    private String remark;

    @Schema(description = "[备用字段1] 备用字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用字段2")
    private String reserve2;

}
