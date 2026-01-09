package cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.warn;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.warn.vo.EmergWarnQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.warn.vo.EmergWarnRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.warn.EmergWarnMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 应急安全近期预警 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class EmergWarnServiceImpl implements EmergWarnService {

    @Resource
    private EmergWarnMapper emergWarnMapper;

    @Override
    public List<EmergWarnRespVO> getEmergWarn(EmergWarnQueryReqVO emergWarnQueryReqVO) {
            return emergWarnMapper.getEmergWarn(emergWarnQueryReqVO);
    }
}
