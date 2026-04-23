package cn.iocoder.yudao.module.chargepark.carservice.service.findcar;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.SpaceLocationDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.findcar.SpaceLocationMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.SPACE_LOCATION_NOT_EXISTS;

/**
 * 车位定位 Service 实现类
 *
 * @author carservice
 */
@Service
@Validated
public class SpaceLocationServiceImpl implements SpaceLocationService {

    @Resource
    private SpaceLocationMapper spaceLocationMapper;

    @Override
    public Long createSpaceLocation(SpaceLocationSaveReqVO createReqVO) {
        SpaceLocationDO spaceLocation = BeanUtils.toBean(createReqVO, SpaceLocationDO.class);
        if (spaceLocation.getQueryTime() == null) {
            spaceLocation.setQueryTime(LocalDateTime.now());
        }
        spaceLocationMapper.insert(spaceLocation);
        return spaceLocation.getId();
    }

    @Override
    public void updateSpaceLocation(SpaceLocationSaveReqVO updateReqVO) {
        validateSpaceLocationExists(updateReqVO.getId());
        SpaceLocationDO updateObj = BeanUtils.toBean(updateReqVO, SpaceLocationDO.class);
        spaceLocationMapper.updateById(updateObj);
    }

    @Override
    public void deleteSpaceLocation(Long id) {
        validateSpaceLocationExists(id);
        spaceLocationMapper.deleteById(id);
    }

    @Override
    public void deleteSpaceLocationListByIds(List<Long> ids) {
        spaceLocationMapper.deleteByIds(ids);
    }

    private void validateSpaceLocationExists(Long id) {
        if (spaceLocationMapper.selectById(id) == null) {
            throw exception(SPACE_LOCATION_NOT_EXISTS);
        }
    }

    @Override
    public SpaceLocationDO getSpaceLocation(Long id) {
        return spaceLocationMapper.selectById(id);
    }

    @Override
    public PageResult<SpaceLocationDO> getSpaceLocationPage(SpaceLocationPageReqVO pageReqVO) {
        return spaceLocationMapper.selectPage(pageReqVO);
    }

}
