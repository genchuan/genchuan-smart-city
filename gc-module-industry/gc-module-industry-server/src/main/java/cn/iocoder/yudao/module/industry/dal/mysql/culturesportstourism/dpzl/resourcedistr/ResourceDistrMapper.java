// ResourceDistrMapper.java
package cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.resourcedistr;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistr.vo.ResourceDistrQueryReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.resourcedistr.vo.ResourceDistrRespVO;
import java.util.List;

@Mapper
public interface ResourceDistrMapper {

    /**
     * 获取资源类型分布
     */
    List<ResourceDistrRespVO.ResourceTypeDistribution> getTypeDistributions(@Param("query") ResourceDistrQueryReqVO query);

    /**
     * 获取资源密度热力图数据
     */
    List<ResourceDistrRespVO.ResourceDensity> getDensityData(@Param("query") ResourceDistrQueryReqVO query);

    /**
     * 获取资源状态占比
     */
    ResourceDistrRespVO.ResourceStatusRatio getStatusRatio(@Param("query") ResourceDistrQueryReqVO query);

    /**
     * 获取重点资源TOP5
     */
    List<ResourceDistrRespVO.TopResource> getTopResources(@Param("query") ResourceDistrQueryReqVO query);

    /**
     * 获取资源类型列表(用于筛选下拉框)
     */
    List<ResourceDistrRespVO.ResourceType> getResourceTypes();
}