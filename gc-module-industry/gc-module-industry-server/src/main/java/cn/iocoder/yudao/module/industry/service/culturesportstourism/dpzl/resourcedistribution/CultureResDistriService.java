// ResourceDistrService.java
package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.resourcedistribution;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistribution.vo.CultureResDistriQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistribution.vo.CultureResDistriRespVO;

public interface CultureResDistriService {

    /**
     * 获取文旅资源分布数据
     */
    CultureResDistriRespVO getResourceDistribution(CultureResDistriQueryReqVO queryVO);
}