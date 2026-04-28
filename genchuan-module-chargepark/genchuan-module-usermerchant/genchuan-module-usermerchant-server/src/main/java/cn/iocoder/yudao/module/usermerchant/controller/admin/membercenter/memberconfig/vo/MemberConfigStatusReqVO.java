package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 会员配置状态 Request VO")
@Data
public class MemberConfigStatusReqVO extends PageParam {

    @Schema(description = "用户ID数组")
    private List<Long> ids;

}