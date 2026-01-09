// ServiceItemsService.java
package cn.iocoder.yudao.module.industry.service.businessservices.dpzl.serviceitems;

import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.serviceitems.vo.BusiServiceItemsRespVO;

public interface BusiServiceItemsService {

    /**
     * 获取服务事项统计概览
     *
     * @return 统计概览数据
     */
    BusiServiceItemsRespVO getServiceItemsOverview();

}