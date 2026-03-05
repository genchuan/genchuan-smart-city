package cn.iocoder.yudao.module.industry.service.park.asset.garage;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.garage.vo.ParkGaragePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.garage.vo.ParkGarageSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.garage.ParkGarageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 车库信息 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkGarageService {

    /**
     * 创建车库信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkGarage(@Valid ParkGarageSaveReqVO createReqVO);

    /**
     * 更新车库信息
     *
     * @param updateReqVO 更新信息
     */
    void updateParkGarage(@Valid ParkGarageSaveReqVO updateReqVO);

    /**
     * 删除车库信息
     *
     * @param id 编号
     */
    void deleteParkGarage(Long id);

    /**
     * 获得车库信息
     *
     * @param id 编号
     * @return 车库信息
     */
    ParkGarageDO getParkGarage(Long id);

    /**
     * 获得车库信息分页
     *
     * @param pageReqVO 分页查询
     * @return 车库信息分页
     */
    PageResult<ParkGarageDO> getParkGaragePage(ParkGaragePageReqVO pageReqVO);

}