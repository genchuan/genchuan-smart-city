package cn.iocoder.yudao.module.chargepark.carservice.service.carguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.NearStationDO;

import java.util.List;

/**
 * 周边场站 Service 接口
 *
 * @author carservice
 */
public interface NearStationService {

    Long createNearStation(NearStationSaveReqVO createReqVO);

    void updateNearStation(NearStationSaveReqVO updateReqVO);

    void deleteNearStation(Long id);

    void deleteNearStationListByIds(List<Long> ids);

    NearStationDO getNearStation(Long id);

    PageResult<NearStationDO> getNearStationPage(NearStationPageReqVO pageReqVO);

}
