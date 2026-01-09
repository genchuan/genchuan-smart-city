package cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.faulthdl;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo.FaultHdlQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo.FaultHdlRespVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 市政设施专题-标记故障处置完成 Mapper
 *
 */
@Mapper
public interface FaultHdlMapper {

    /**
     * 查询市政设施专题-标记故障处置完成
     *
     * @param faultHdlQueryReqVO 查询参数
     * @return FaultHdlRespVO 结果
     */
    FaultHdlRespVO getFaultHdl(FaultHdlQueryReqVO faultHdlQueryReqVO);

}
