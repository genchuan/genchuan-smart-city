package cn.iocoder.yudao.module.envirhealth.service.park.park;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.park.ParkDashboardVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.park.ParkPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.park.ParkSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.ParkDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.detail.ParkDetailDO;
import jakarta.validation.Valid;

/**
 * 公园 Service 接口
 *
 * @author 芋道源码
 */
public interface ParkService {

    /**
     * 创建公园
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPark(@Valid ParkSaveReqVO createReqVO);

    /**
     * 更新公园
     *
     * @param updateReqVO 更新信息
     */
    void updatePark(@Valid ParkSaveReqVO updateReqVO);

    /**
     * 删除公园
     *
     * @param id 编号
     */
    void deletePark(Long id);

    /**
     * 获得公园
     *
     * @param id 编号
     * @return 公园
     */
    ParkDO getPark(Long id);

    /**
     * 获得公园分页
     *
     * @param pageReqVO 分页查询
     * @return 公园分页
     */
    PageResult<ParkDO> getParkPage(ParkPageReqVO pageReqVO);

    /**
     * 获得公园分页
     *
     * @param pageReqVO 分页查询
     * @return 公园分页
     */
    PageResult<ParkDetailDO> getParkDetailPage(ParkPageReqVO pageReqVO);

    /**
     * 获取公园看板统计数据
     * @return 看板统计VO
     */
    ParkDashboardVO getParkDashboardDashboard();
}