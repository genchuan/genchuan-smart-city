package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet;

import cn.iocoder.yudao.module.envirhealth.util.vo.PieItemVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "公厕圆环图统计返回")
@Data
public class PublicToiletPieChartsRespVO {

    @Schema(description = "运营状态占比")
    private List<PieItemVO> operationStatus;

    @Schema(description = "区域分布占比")
    private List<PieItemVO> areaDistribution;
}