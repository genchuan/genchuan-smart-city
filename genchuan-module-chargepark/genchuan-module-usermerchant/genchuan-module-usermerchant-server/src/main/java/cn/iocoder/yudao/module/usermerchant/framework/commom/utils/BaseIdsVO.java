package cn.iocoder.yudao.module.usermerchant.framework.commom.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class BaseIdsVO {

    @Schema(description = "用户ID数组")
    private List<Long> ids;

}