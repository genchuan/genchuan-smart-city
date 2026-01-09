package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac;


import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.FacOverviewReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.FacOverviewRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.MuniFacQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.MuniFacRespVO;

/**
 * 市政设施专题 Service 接口
 *
 * @author lxs
 */
public interface MuniFacService {

        /**
         * 查询市政设施专题
         *
         * @param muniFacQueryReqVO 查询条件
         * @return 查询结果
         */
        MuniFacRespVO getMuniFac(MuniFacQueryReqVO muniFacQueryReqVO);

        /**
         * 获得市政设施概览数据
         * @param facOverviewReqVO
         * @return
         */
        FacOverviewRespVO getMuniFacOverview(FacOverviewReqVO facOverviewReqVO);
}
