package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.urbancomp;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.urbancomp.vo.UrbanCompQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.urbancomp.vo.UrbanCompRespVO;

/**
 * 市政设施专题-市政设施管理部件 Service 接口
 *
 */
public interface UrbanCompService {

    /**
     * 查询市政设施专题-市政设施管理部件
     *
     * @param urbanCompQueryReqVO 查询条件
     * @return 查询结果
     */
    UrbanCompRespVO getUrbanComp(UrbanCompQueryReqVO urbanCompQueryReqVO);
}
