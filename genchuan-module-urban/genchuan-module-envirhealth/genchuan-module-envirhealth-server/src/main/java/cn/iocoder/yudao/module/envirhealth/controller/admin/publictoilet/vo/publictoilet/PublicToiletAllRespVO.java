package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet;

import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "公厕看板统计（卡片+环状图+柱状图）返回")
@Data
public class PublicToiletAllRespVO {

    @Schema(description = "卡片统计")
    private PublicToiletCardAllVO card;

    @Schema(description = "环状图统计")
    private PublicToiletPieChartsRespVO pie;

    @Schema(description = "柱状图：不同区域保洁达标率对比")
    private List<BarItemVO> cleaningQualifiedRateByArea;

}