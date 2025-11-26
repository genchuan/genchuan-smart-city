package cn.iocoder.yudao.module.industry.service.businessservices.dpzl.resourcedistribution;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo.ResourceDistributionQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo.ResourceDistributionRespVO;

public interface ResourceDistributionService {
    ResourceDistributionRespVO getDistribution(ResourceDistributionQueryReqVO req);
}