package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.pending;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.module.envirhealth.util.garbagecollection.vo.NameValueVO;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/26 14:40
 */
@Data
@Schema(description = "待执行收运计划统计卡片VO")
public class GarbageCollectionCardPendingVO {

    @Schema(description = "待执行计划总数", example = "10")
    private Long totalPendingCount;

    @Schema(description = "按区域统计的待执行数（name：区域名称，value：数量）")
    private List<NameValueVO> areaPendingCountMap;

    @Schema(description = "按品类统计的待执行数（name：品类名称，value：数量）")
    private List<NameValueVO> garbageTypePendingCountMap;
}