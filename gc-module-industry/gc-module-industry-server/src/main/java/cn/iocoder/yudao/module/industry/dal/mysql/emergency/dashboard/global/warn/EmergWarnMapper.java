package cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.warn;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.warn.vo.EmergWarnQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.warn.vo.EmergWarnRespVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 应急安全近期预警 Mapper
 *
 * @author lxs
 */
@Mapper
public interface EmergWarnMapper {

        /**
         * 查询应急安全近期预警
         *
         * @param emergWarnQueryReqVO 查询参数
         * @return EmergWarnRespVO 结果
         */
        List<EmergWarnRespVO> getEmergWarn(EmergWarnQueryReqVO emergWarnQueryReqVO);

}
