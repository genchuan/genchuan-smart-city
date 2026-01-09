package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.mngcomp;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.mngcomp.vo.MngCompQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.mngcomp.vo.MngCompRespVO;

/**
 * 市政设施专题-管理部件 Service 接口
 *
 */
public interface MngCompService {

    /**
     * 查询市政设施专题-管理部件
     *
     * @param mngCompQueryReqVO 查询条件
     * @return 查询结果
     */
    MngCompRespVO getMngComp(MngCompQueryReqVO mngCompQueryReqVO);
}
