package cn.iocoder.yudao.module.envirhealth.service.roadcleaning.road;

import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.road.RoadPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.road.RoadSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.RoadDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.roadcleaning.RoadMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 道路 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RoadServiceImpl implements RoadService {

    @Resource
    private RoadMapper roadMapper;

    @Override
    public Long createRoad(RoadSaveReqVO createReqVO) {
        // 插入
        RoadDO road = BeanUtils.toBean(createReqVO, RoadDO.class);
        roadMapper.insert(road);
        // 返回
        return road.getId();
    }

    @Override
    public void updateRoad(RoadSaveReqVO updateReqVO) {
        // 校验存在
        validateRoadExists(updateReqVO.getId());
        // 更新
        RoadDO updateObj = BeanUtils.toBean(updateReqVO, RoadDO.class);
        roadMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoad(Long id) {
        // 校验存在
        validateRoadExists(id);
        // 删除
        roadMapper.deleteById(id);
    }

    private void validateRoadExists(Long id) {
        if (roadMapper.selectById(id) == null) {
            throw exception(ROAD_NOT_EXISTS);
        }
    }

    @Override
    public RoadDO getRoad(Long id) {
        return roadMapper.selectById(id);
    }

    @Override
    public PageResult<RoadDO> getRoadPage(RoadPageReqVO pageReqVO) {
        return roadMapper.selectPage(pageReqVO);
    }

}