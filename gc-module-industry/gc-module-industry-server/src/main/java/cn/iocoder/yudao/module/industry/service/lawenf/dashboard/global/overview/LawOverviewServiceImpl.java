package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.overview;


import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo.LawOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo.LawOverviewRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.overview.LawOverviewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 执法全域数据概览 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class LawOverviewServiceImpl implements LawOverviewService {

    @Resource
    private LawOverviewMapper lawOverviewMapper;

    @Override
    public LawOverviewRespVO getLawOverview(LawOverviewQueryReqVO lawOverviewQueryReqVO) {
            return lawOverviewMapper.getLawOverview(lawOverviewQueryReqVO);
    }
}
