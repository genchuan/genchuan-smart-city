package cn.iocoder.yudao.module.industry.service.businessservices.dpzl.resourcedistribution;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo.BusiResDistriQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.resourcedistribution.vo.BusiResDistriRespVO;

public interface BusiResDistriService {
    BusiResDistriRespVO getDistribution(BusiResDistriQueryReqVO req);
}