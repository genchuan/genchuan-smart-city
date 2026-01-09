package cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Schema(description = "管理后台 - 医疗机构分布视图 查询 Request VO")
@Data
public class MedInstQueryReqVO {

//        @Schema(description = "查询地区-省市县三级shortCode码，6位",example = "110000")
//        private String regionShortCode;
//
//        @Schema(description = "统计开始时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-01-01 09:00:00")
//        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//        private LocalDateTime startTime;
//
//        @Schema(description = "统计结束时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-11-01 23:59:59")
//        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//        private LocalDateTime endTime;

        @Schema(description = "查询医疗机构类型-机构类型：0-综合医院 / 1-专科医院 / 2-社区卫生中心",example = "0")
        private Integer instType;

        @Schema(description = "查询接诊量大于该值的医疗机构",example = "100")
        private Integer treatVolumeMin;

}
