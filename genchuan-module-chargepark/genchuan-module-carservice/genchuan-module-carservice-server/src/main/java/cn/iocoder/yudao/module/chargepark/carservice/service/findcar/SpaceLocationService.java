package cn.iocoder.yudao.module.chargepark.carservice.service.findcar;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.SpaceLocationDO;

import java.util.List;

/**
 * 车位定位 Service 接口
 *
 * @author carservice
 */
public interface SpaceLocationService {

    Long createSpaceLocation(SpaceLocationSaveReqVO createReqVO);

    void updateSpaceLocation(SpaceLocationSaveReqVO updateReqVO);

    void deleteSpaceLocation(Long id);

    void deleteSpaceLocationListByIds(List<Long> ids);

    SpaceLocationDO getSpaceLocation(Long id);

    PageResult<SpaceLocationDO> getSpaceLocationPage(SpaceLocationPageReqVO pageReqVO);

}
