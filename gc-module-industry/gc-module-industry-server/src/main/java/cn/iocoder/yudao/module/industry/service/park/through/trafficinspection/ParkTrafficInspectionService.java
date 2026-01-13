package cn.iocoder.yudao.module.industry.service.park.through.trafficinspection;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.trafficinspection.vo.ParkTrafficInspectionPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.trafficinspection.vo.ParkTrafficInspectionSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.trafficinspection.ParkTrafficInspectionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 通行稽查 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkTrafficInspectionService {

    /**
     * 创建通行稽查
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkTrafficInspection(@Valid ParkTrafficInspectionSaveReqVO createReqVO);

    /**
     * 更新通行稽查
     *
     * @param updateReqVO 更新信息
     */
    void updateParkTrafficInspection(@Valid ParkTrafficInspectionSaveReqVO updateReqVO);

    /**
     * 删除通行稽查
     *
     * @param id 编号
     */
    void deleteParkTrafficInspection(Long id);

    /**
     * 获得通行稽查
     *
     * @param id 编号
     * @return 通行稽查
     */
    ParkTrafficInspectionDO getParkTrafficInspection(Long id);

    /**
     * 获得通行稽查分页
     *
     * @param pageReqVO 分页查询
     * @return 通行稽查分页
     */
    PageResult<ParkTrafficInspectionDO> getParkTrafficInspectionPage(ParkTrafficInspectionPageReqVO pageReqVO);

}