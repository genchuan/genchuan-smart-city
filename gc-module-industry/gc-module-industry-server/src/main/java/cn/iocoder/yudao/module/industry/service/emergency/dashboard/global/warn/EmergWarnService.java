package cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.warn;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.warn.vo.EmergWarnQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.warn.vo.EmergWarnRespVO;

import java.util.List;

/**
 * 应急安全近期预警 Service 接口
 *
 * @author lxs
 */
public interface EmergWarnService {

        /**
         * 查询应急安全近期预警
         *
         * @param emergWarnQueryReqVO 查询条件
         * @return 查询结果
         */
        List<EmergWarnRespVO> getEmergWarn(EmergWarnQueryReqVO emergWarnQueryReqVO);
}
