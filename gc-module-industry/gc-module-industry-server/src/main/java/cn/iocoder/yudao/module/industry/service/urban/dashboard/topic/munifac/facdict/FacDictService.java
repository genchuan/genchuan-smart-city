package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.facdict;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.facdict.vo.FacDictQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.facdict.vo.FacDictRespVO;

/**
 * 市政设施专题-设施类型字典 Service 接口
 *
 */
public interface FacDictService {

    /**
     * 查询市政设施专题-设施类型字典
     *
     * @param facDictQueryReqVO 查询条件
     * @return 查询结果
     */
    FacDictRespVO getFacDict(FacDictQueryReqVO facDictQueryReqVO);
}
