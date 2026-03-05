package cn.iocoder.yudao.module.facility.service.road.roadfacility;

import java.util.*;

import cn.iocoder.yudao.module.facility.controller.admin.road.roadfacility.vo.RoadFacilityPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadfacility.vo.RoadFacilitySaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadfacility.RoadFacilityDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 道路设施 Service 接口
 *
 * @author 亘川智城
 */
public interface RoadFacilityService {

    /**
     * 创建道路设施
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRoadFacility(@Valid RoadFacilitySaveReqVO createReqVO);

    /**
     * 更新道路设施
     *
     * @param updateReqVO 更新信息
     */
    void updateRoadFacility(@Valid RoadFacilitySaveReqVO updateReqVO);

    /**
     * 删除道路设施
     *
     * @param id 编号
     */
    void deleteRoadFacility(Long id);

    /**
     * 获得道路设施
     *
     * @param id 编号
     * @return 道路设施
     */
    RoadFacilityDO getRoadFacility(Long id);

    /**
     * 获得道路设施分页
     *
     * @param pageReqVO 分页查询
     * @return 道路设施分页
     */
    PageResult<RoadFacilityDO> getRoadFacilityPage(RoadFacilityPageReqVO pageReqVO);

}
