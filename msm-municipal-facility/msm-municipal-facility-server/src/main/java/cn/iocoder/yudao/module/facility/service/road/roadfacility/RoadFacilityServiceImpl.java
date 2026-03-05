package cn.iocoder.yudao.module.facility.service.road.roadfacility;

import cn.iocoder.yudao.module.facility.controller.admin.road.roadfacility.vo.RoadFacilityPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadfacility.vo.RoadFacilitySaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadfacility.RoadFacilityDO;
import cn.iocoder.yudao.module.facility.dal.mysql.road.roadfacility.RoadFacilityMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.*;

/**
 * 道路设施 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RoadFacilityServiceImpl implements RoadFacilityService {

    @Resource
    private RoadFacilityMapper roadFacilityMapper;

    @Override
    public Long createRoadFacility(RoadFacilitySaveReqVO createReqVO) {
        // 插入
        RoadFacilityDO roadFacility = BeanUtils.toBean(createReqVO, RoadFacilityDO.class);
        roadFacilityMapper.insert(roadFacility);
        // 返回
        return roadFacility.getId();
    }

    @Override
    public void updateRoadFacility(RoadFacilitySaveReqVO updateReqVO) {
        // 校验存在
        validateRoadFacilityExists(updateReqVO.getId());
        // 更新
        RoadFacilityDO updateObj = BeanUtils.toBean(updateReqVO, RoadFacilityDO.class);
        roadFacilityMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoadFacility(Long id) {
        // 校验存在
        validateRoadFacilityExists(id);
        // 删除
        roadFacilityMapper.deleteById(id);
    }

    private void validateRoadFacilityExists(Long id) {
        if (roadFacilityMapper.selectById(id) == null) {
            throw exception(ROAD_FACILITY_NOT_EXISTS);
        }
    }

    @Override
    public RoadFacilityDO getRoadFacility(Long id) {
        return roadFacilityMapper.selectById(id);
    }

    @Override
    public PageResult<RoadFacilityDO> getRoadFacilityPage(RoadFacilityPageReqVO pageReqVO) {
        return roadFacilityMapper.selectPage(pageReqVO);
    }

}
