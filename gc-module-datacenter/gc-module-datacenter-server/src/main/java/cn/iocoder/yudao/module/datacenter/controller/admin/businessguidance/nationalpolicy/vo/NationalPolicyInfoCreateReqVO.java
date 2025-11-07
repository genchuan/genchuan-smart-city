package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.nationalpolicy.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "管理后台 - 国家政策信息创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class NationalPolicyInfoCreateReqVO extends NationalPolicyInfoBaseVO {
}