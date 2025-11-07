package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.managedmattermajor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 大小类 Request VO")
@Data
public class ManagedMatterMajorListReqVO {
    @Schema(description = "事件类型名称，模糊匹配", example = "市政")
    private String name;

    @Schema(description = "状态，参见 CommonStatusEnum 枚举类", example = "1")
    private String enableStatus;
}
