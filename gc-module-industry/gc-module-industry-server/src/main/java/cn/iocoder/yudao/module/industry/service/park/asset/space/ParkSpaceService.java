package cn.iocoder.yudao.module.industry.service.park.asset.space;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.space.vo.ParkSpacePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.space.vo.ParkSpaceSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.space.ParkSpaceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 车位信息 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkSpaceService {

    /**
     * 创建车位信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkSpace(@Valid ParkSpaceSaveReqVO createReqVO);

    /**
     * 更新车位信息
     *
     * @param updateReqVO 更新信息
     */
    void updateParkSpace(@Valid ParkSpaceSaveReqVO updateReqVO);

    /**
     * 删除车位信息
     *
     * @param id 编号
     */
    void deleteParkSpace(Long id);

    /**
     * 获得车位信息
     *
     * @param id 编号
     * @return 车位信息
     */
    ParkSpaceDO getParkSpace(Long id);

    /**
     * 获得车位信息分页
     *
     * @param pageReqVO 分页查询
     * @return 车位信息分页
     */
    PageResult<ParkSpaceDO> getParkSpacePage(ParkSpacePageReqVO pageReqVO);

}