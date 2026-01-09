package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.fltsite;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.fltsite.vo.FltSiteQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.fltsite.vo.FltSiteRespVO;

import java.util.List;

/**
 * 市政设施专题-故障设施空间数据 Service 接口
 *
 */
public interface FltSiteService {

    /**
     * 查询市政设施专题-故障设施空间数据
     *
     * @param fltSiteQueryReqVO 查询条件
     * @return 查询结果
     */
    List<FltSiteRespVO> getFltSite(FltSiteQueryReqVO fltSiteQueryReqVO);
}
