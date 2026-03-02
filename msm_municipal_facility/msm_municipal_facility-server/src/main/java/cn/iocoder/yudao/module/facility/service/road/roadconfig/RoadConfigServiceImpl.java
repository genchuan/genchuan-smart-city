package cn.iocoder.yudao.module.facility.service.road.roadconfig;

import cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig.vo.RoadConfigPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig.vo.RoadConfigSaveReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig.vo.RoadConfigUpdateReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadconfig.RoadConfigDO;
import cn.iocoder.yudao.module.facility.dal.mysql.road.roadconfig.RoadConfigMapper;
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
 * 道路监测配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RoadConfigServiceImpl implements RoadConfigService {

    @Resource
    private RoadConfigMapper roadConfigMapper;

    @Override
    public Long createRoadConfig(RoadConfigSaveReqVO createReqVO) {
        //自动生成配置编码
        String configCode=UUID.randomUUID().toString().replace("-","");

        createReqVO.setConfigCode(configCode);

        // 插入
        RoadConfigDO roadConfig = BeanUtils.toBean(createReqVO, RoadConfigDO.class);
        roadConfigMapper.insert(roadConfig);
        // 返回
        return roadConfig.getId();
    }

    @Override
    public void updateRoadConfig(RoadConfigUpdateReqVO updateReqVO) {
        // 校验存在
        validateRoadConfigExists(updateReqVO.getId());
        // 更新
        RoadConfigDO updateObj = BeanUtils.toBean(updateReqVO, RoadConfigDO.class);
        roadConfigMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoadConfig(Long id) {
        // 校验存在
        validateRoadConfigExists(id);
        // 删除
        roadConfigMapper.deleteById(id);
    }

    private void validateRoadConfigExists(Long id) {
        if (roadConfigMapper.selectById(id) == null) {
            throw exception(ROAD_CONFIG_NOT_EXISTS);
        }
    }

    @Override
    public RoadConfigDO getRoadConfig(Long id) {
        return roadConfigMapper.selectById(id);
    }

    @Override
    public PageResult<RoadConfigDO> getRoadConfigPage(RoadConfigPageReqVO pageReqVO) {
        return roadConfigMapper.selectPage(pageReqVO);
    }

}
