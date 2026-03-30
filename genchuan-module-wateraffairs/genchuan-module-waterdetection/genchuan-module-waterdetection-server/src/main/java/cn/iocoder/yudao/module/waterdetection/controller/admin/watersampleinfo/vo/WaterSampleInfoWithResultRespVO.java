package cn.iocoder.yudao.module.waterdetection.controller.admin.watersampleinfo.vo;

import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampleinfo.WaterSampleResultDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "水质检测信息和指标值导出")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WaterSampleInfoWithResultRespVO extends WaterSampleInfoRespVO {

    // 子表信息列表
    private List<WaterSampleResultDO> resultList;

    // getter和setter
    public List<WaterSampleResultDO> getResultList() {
        return resultList;
    }

    public void setResultList(List<WaterSampleResultDO> resultList) {
        this.resultList = resultList;
    }


}