package cn.iocoder.yudao.module.industry.service.park.asset.lot;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.lot.vo.ParkLotPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.lot.vo.ParkLotSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.lot.ParkLotDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 车场信息 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkLotService {

    /**
     * 创建车场信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkLot(@Valid ParkLotSaveReqVO createReqVO);

    /**
     * 更新车场信息
     *
     * @param updateReqVO 更新信息
     */
    void updateParkLot(@Valid ParkLotSaveReqVO updateReqVO);

    /**
     * 删除车场信息
     *
     * @param id 编号
     */
    void deleteParkLot(Long id);

    /**
     * 获得车场信息
     *
     * @param id 编号
     * @return 车场信息
     */
    ParkLotDO getParkLot(Long id);

    /**
     * 获得车场信息分页
     *
     * @param pageReqVO 分页查询
     * @return 车场信息分页
     */
    PageResult<ParkLotDO> getParkLotPage(ParkLotPageReqVO pageReqVO);

}