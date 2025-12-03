package cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.keypop.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Schema(description = "管理后台 - 重点人群健康视图 查询 Request VO")
@Data
public class KeyPopQueryReqVO {

        @Schema(description = "查询地区-省市县街道社区五级fullCode码，12位",example = "110101001001")
        private String regionFullCode;

        @Schema(description = "查询-重点人群的类型",example = "慢性病患者")
        private String peopleType;

}
