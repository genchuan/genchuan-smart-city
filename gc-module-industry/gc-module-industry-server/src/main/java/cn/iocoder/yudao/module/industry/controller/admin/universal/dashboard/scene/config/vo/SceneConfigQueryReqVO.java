package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.config.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 获取场景配置接口 查询 Request VO")
@Data
public class SceneConfigQueryReqVO {

//    @Schema(description = "查询场景码", example = "0101")
    @Schema(hidden = true)
    private String sceneKey;    //对应sceneCode


}
