package cn.iocoder.yudao.module.smartcity.service.roadcollapsesafety;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.roadcollapsesafety.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.roadcollapsesafety.RoadCollapseSafetyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.roadcollapsesafety.RoadCollapseSafetyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 路面塌陷安全 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class RoadCollapseSafetyServiceImpl implements RoadCollapseSafetyService {

    @Resource
    private RoadCollapseSafetyMapper roadCollapseSafetyMapper;

    @Override
    public Long createRoadCollapseSafety(RoadCollapseSafetySaveReqVO createReqVO) {
        // 插入
        RoadCollapseSafetyDO roadCollapseSafety = BeanUtils.toBean(createReqVO, RoadCollapseSafetyDO.class);
        roadCollapseSafetyMapper.insert(roadCollapseSafety);
        // 返回
        return roadCollapseSafety.getId();
    }

    @Override
    public void updateRoadCollapseSafety(RoadCollapseSafetySaveReqVO updateReqVO) {
        // 校验存在
        validateRoadCollapseSafetyExists(updateReqVO.getId());
        // 更新
        RoadCollapseSafetyDO updateObj = BeanUtils.toBean(updateReqVO, RoadCollapseSafetyDO.class);
        roadCollapseSafetyMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoadCollapseSafety(Long id) {
        // 校验存在
        validateRoadCollapseSafetyExists(id);
        // 删除
        roadCollapseSafetyMapper.deleteById(id);
    }

    private void validateRoadCollapseSafetyExists(Long id) {
        if (roadCollapseSafetyMapper.selectById(id) == null) {
            throw exception(ROAD_COLLAPSE_SAFETY_NOT_EXISTS);
        }
    }

    @Override
    public RoadCollapseSafetyDO getRoadCollapseSafety(Long id) {
        return roadCollapseSafetyMapper.selectById(id);
    }

    @Override
    public PageResult<RoadCollapseSafetyDO> getRoadCollapseSafetyPage(RoadCollapseSafetyPageReqVO pageReqVO) {
        return roadCollapseSafetyMapper.selectPage(pageReqVO);
    }

}