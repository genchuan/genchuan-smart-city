package cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.resoverview;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverDispatchReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverViewRespVO;

import java.util.List;

/**
 * 应急资源总览 Service 接口
 *
 * @author lxs
 */
public interface EmergResoverViewService {

        /**
         * 查询应急资源总览
         *
         * @param emergResoverViewQueryReqVO 查询条件
         * @return 查询结果
         */
        List<EmergResoverViewRespVO> listEmergResoverView(EmergResoverViewQueryReqVO emergResoverViewQueryReqVO);

        void dispatchEmergResover(EmergResoverDispatchReqVO emergResoverDispatchReqVO);
}
