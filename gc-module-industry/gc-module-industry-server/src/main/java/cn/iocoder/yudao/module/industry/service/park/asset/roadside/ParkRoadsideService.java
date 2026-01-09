package cn.iocoder.yudao.module.industry.service.park.asset.roadside;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.roadside.vo.ParkRoadsidePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.roadside.vo.ParkRoadsideSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.roadside.ParkRoadsideDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 路侧泊位 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkRoadsideService {

    /**
     * 创建路侧泊位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkRoadside(@Valid ParkRoadsideSaveReqVO createReqVO);

    /**
     * 更新路侧泊位
     *
     * @param updateReqVO 更新信息
     */
    void updateParkRoadside(@Valid ParkRoadsideSaveReqVO updateReqVO);

    /**
     * 删除路侧泊位
     *
     * @param id 编号
     */
    void deleteParkRoadside(Long id);

    /**
     * 获得路侧泊位
     *
     * @param id 编号
     * @return 路侧泊位
     */
    ParkRoadsideDO getParkRoadside(Long id);

    /**
     * 获得路侧泊位分页
     *
     * @param pageReqVO 分页查询
     * @return 路侧泊位分页
     */
    PageResult<ParkRoadsideDO> getParkRoadsidePage(ParkRoadsidePageReqVO pageReqVO);

}