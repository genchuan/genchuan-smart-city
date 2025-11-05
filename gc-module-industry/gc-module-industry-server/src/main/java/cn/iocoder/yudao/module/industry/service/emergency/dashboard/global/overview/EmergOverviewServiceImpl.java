package cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.overview;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.overview.vo.EmergOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.overview.vo.EmergOverviewRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.emergency.dashboard.global.overview.EmergOverviewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 应急全域数据概览  Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class EmergOverviewServiceImpl implements EmergOverviewService{
    @Resource
    private EmergOverviewMapper emergOverviewMapper;


    @Override
    public EmergOverviewRespVO getEmergOverview(EmergOverviewQueryReqVO emergOverviewQueryReqVO) {
        return emergOverviewMapper.getEmergOverview(emergOverviewQueryReqVO);
    }
}
