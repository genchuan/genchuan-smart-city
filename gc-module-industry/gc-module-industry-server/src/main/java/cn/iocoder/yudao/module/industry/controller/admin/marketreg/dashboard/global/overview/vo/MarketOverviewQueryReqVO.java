package cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.overview.vo;

import cn.iocoder.yudao.module.industry.framework.util.lxs.RegionUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 市监全域数据地图 查询 Request VO")
@Data
public class MarketOverviewQueryReqVO {

        @Schema(description = "查询地区-12位fullCode码，传递值为区县/街道",example = "110101000000")
        @Pattern(regexp = "\\d{6}|\\d{12}", message = "地区码格式必须是6位或12位数字")
        private String regionFullCode;

        // 地区码等级：1-省，2-市,3-县,4-街道,5-社区
        @Schema(hidden = true)
        private Integer regionLevel;
        //地区码有效位数，比如110000，有效2位，110100，有效4位
        @Schema(hidden = true)
        private Integer regionEffectiveLength;

        // 获得regionCode值时会自动计算 地区码等级 和 地区码有效位数
        public void setRegionFullCode(String regionFullCode) {
                this.regionFullCode = regionFullCode;
                this.regionLevel = RegionUtils.getRegionLevel(regionFullCode);
                if (this.regionLevel == null) {
                        throw new IllegalArgumentException("地区码不合法，无法识别级别");
                }
                this.regionEffectiveLength = RegionUtils.getEffectiveLength(regionFullCode);
        }

        @Schema(description = "筛选监管对象类型，可多选：0=企业,1=个体户,2=特种设备", example = "[0,1,2]")
        private List<Integer> supvObjTypeIds;

        @Schema(description = "筛选案件类型，可多选：0=食药案件,1=质量案件", example = "[0,1]")
        private List<Integer> caseTypeIds;

        @Schema(description = "筛选预警类型，可多选：0-食药安全/1-特种设备/2-市场秩序", example = "[0,1]")
        private List<Integer> warnTypeIds;


}
