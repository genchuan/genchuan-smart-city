package cn.iocoder.yudao.module.chargepark.carservice.service.carguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.ChargeParkMapDO;

import java.util.List;

/**
 * 充停地图 Service 接口
 *
 * @author carservice
 */
public interface ChargeParkMapService {

    Long createChargeParkMap(ChargeParkMapSaveReqVO createReqVO);

    void updateChargeParkMap(ChargeParkMapSaveReqVO updateReqVO);

    void deleteChargeParkMap(Long id);

    void deleteChargeParkMapListByIds(List<Long> ids);

    ChargeParkMapDO getChargeParkMap(Long id);

    PageResult<ChargeParkMapDO> getChargeParkMapPage(ChargeParkMapPageReqVO pageReqVO);

}
