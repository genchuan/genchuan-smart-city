// ResourceDistrService.java
package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.resourcedistr;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistr.vo.ResourceDistrQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistr.vo.ResourceDistrRespVO;

public interface ResourceDistrService {

    /**
     * 获取文旅资源分布数据
     */
    ResourceDistrRespVO getResourceDistribution(ResourceDistrQueryReqVO queryVO);
}