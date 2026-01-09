package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.faulthdl;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo.FaultHdlQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo.FaultHdlRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo.FaultHdlUpdateReqVO;

/**
 * 市政设施专题-标记故障处置完成 Service 接口
 *
 */
public interface FaultHdlService {

    /**
     * 查询市政设施专题-标记故障处置完成
     *
     * @param faultHdlQueryReqVO 查询条件
     * @return 查询结果
     */
    FaultHdlRespVO getFaultHdl(FaultHdlQueryReqVO faultHdlQueryReqVO);

    void markFaultHandled(FaultHdlUpdateReqVO faultHdlUpdateReqVO);
}
