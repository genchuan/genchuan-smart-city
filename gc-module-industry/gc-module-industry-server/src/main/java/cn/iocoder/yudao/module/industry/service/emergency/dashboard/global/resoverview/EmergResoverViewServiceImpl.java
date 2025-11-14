package cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.resoverview;


import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo.EmergResoverViewRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.resoverview.EmergResoverViewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 应急资源总览 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class EmergResoverViewServiceImpl implements EmergResoverViewService {

    @Resource
    private EmergResoverViewMapper emergResoverViewMapper;

    @Override
    public List<EmergResoverViewRespVO> listEmergResoverView(EmergResoverViewQueryReqVO emergResoverViewQueryReqVO) {
            return emergResoverViewMapper.listEmergResoverView(emergResoverViewQueryReqVO);
    }
}
