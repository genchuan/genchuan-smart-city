package cn.iocoder.yudao.module.industry.service.health.dashboard.global.medinst;


import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo.MedInstQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo.MedInstRespVO;

import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo.TreatTrendVO;
import cn.iocoder.yudao.module.industry.dal.mysql.health.dashboard.global.medinst.MedInstMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 医疗机构分布视图 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class MedInstServiceImpl implements MedInstService {

    @Resource
    private MedInstMapper medInstMapper;

    @Override
    public MedInstRespVO getMedInst(MedInstQueryReqVO reqVO) {

        MedInstRespVO resp = new MedInstRespVO();

        // 1. 分类数量统计
        resp.setInstSortCountVO(medInstMapper.selectInstSortCount(reqVO));

        // 2. 地图机构明细
        resp.setInstList(medInstMapper.selectInstList(reqVO));

        // 3. TOP5
        resp.setInstTop5List(medInstMapper.selectInstTop5(reqVO));

        return resp;
    }

    @Override
    public List<TreatTrendVO> getInstTrend(Long orgId) {
        return medInstMapper.selectInstTreatTrend(orgId);
    }
}

