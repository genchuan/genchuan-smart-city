package cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.supvview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 市监对象分布视图 查询 Request VO")
@Data
public class MarketSupvViewQueryReqVO {

        @Schema(description = "查询地区-五级fullCode码，12位", example = "110101001001")
        private String regionFullCode;

        @Schema(description = "统计开始时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-01-01 09:00:00")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;

        @Schema(description = "统计结束时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-11-01 23:59:59")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;

        @Schema(description = "监管对象类型列表，多选：0-企业, 1-个体户, 2-特种设备", example = "[0,1]")
        private List<Integer> typeIds;

        @Schema(description = "监管状态筛选，下拉：0-未监管, 1-已监管, 2-异常", example = "0")
        private Integer status;


}
