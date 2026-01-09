package cn.iocoder.yudao.module.industry.service.businessservices.dpzl.resourcedistribution;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo.*;
import cn.iocoder.yudao.module.industry.dal.mysql.businessservices.dpzl.resourcedistribution.BusiResDistriMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BusiResDistriServiceImpl implements BusiResDistriService {

    @Resource
    private BusiResDistriMapper mapper;

    @Override
    public BusiResDistriRespVO getDistribution(BusiResDistriQueryReqVO req) {
        BusiResDistriRespVO resp = new BusiResDistriRespVO();

        List<BusiResDistriRespVO.RegionEntCount> regionEntList = mapper.selectRegionEntCount(req);
        List<BusiResDistriRespVO.IndustryEntCount> industryEntList = mapper.selectIndustryEntCount(req);
        List<BusiResDistriRespVO.KeyEnt> keyEntList = mapper.selectKeyEntList(req);
        List<BusiResDistriRespVO.ScaleEntCount> scaleEntList = mapper.selectScaleEntCount(req);

        resp.setRegionEntList(regionEntList);
        resp.setIndustryEntList(industryEntList);
        resp.setKeyEntList(keyEntList);
        resp.setScaleEntList(scaleEntList);

        return resp;
    }
}