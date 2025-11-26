package cn.iocoder.yudao.module.industry.service.businessservices.dpzl.resourcedistribution;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo.*;
import cn.iocoder.yudao.module.industry.dal.mysql.businessservices.dpzl.resourcedistribution.ResourceDistributionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResourceDistributionServiceImpl implements ResourceDistributionService {

    @Resource
    private ResourceDistributionMapper mapper;

    @Override
    public ResourceDistributionRespVO getDistribution(ResourceDistributionQueryReqVO req) {
        ResourceDistributionRespVO resp = new ResourceDistributionRespVO();

        List<ResourceDistributionRespVO.RegionEntCount> regionEntList = mapper.selectRegionEntCount(req);
        List<ResourceDistributionRespVO.IndustryEntCount> industryEntList = mapper.selectIndustryEntCount(req);
        List<ResourceDistributionRespVO.KeyEnt> keyEntList = mapper.selectKeyEntList(req);
        List<ResourceDistributionRespVO.ScaleEntCount> scaleEntList = mapper.selectScaleEntCount(req);

        resp.setRegionEntList(regionEntList);
        resp.setIndustryEntList(industryEntList);
        resp.setKeyEntList(keyEntList);
        resp.setScaleEntList(scaleEntList);

        return resp;
    }
}