package cn.iocoder.yudao.module.data.controller.admin.code.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Schema(description = "管理后台 - 地理编码树节点 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class GeoCodeTreeRespVO extends GeoCodeRespVO {

    @Schema(description = "子节点列表")
    private List<GeoCodeTreeRespVO> children;

}