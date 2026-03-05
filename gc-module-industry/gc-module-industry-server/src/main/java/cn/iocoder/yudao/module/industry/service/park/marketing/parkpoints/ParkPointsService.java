package cn.iocoder.yudao.module.industry.service.park.marketing.parkpoints;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpoints.vo.ParkPointsPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpoints.vo.ParkPointsSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpoints.ParkPointsDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 用户积分 Service 接口
 *
 * @author lxs
 */
public interface ParkPointsService {

    /**
     * 创建用户积分
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkPoints(@Valid ParkPointsSaveReqVO createReqVO);

    /**
     * 更新用户积分
     *
     * @param updateReqVO 更新信息
     */
    void updateParkPoints(@Valid ParkPointsSaveReqVO updateReqVO);

    /**
     * 删除用户积分
     *
     * @param id 编号
     */
    void deleteParkPoints(Long id);

    /**
     * 获得用户积分
     *
     * @param id 编号
     * @return 用户积分
     */
    ParkPointsDO getParkPoints(Long id);

    /**
     * 获得用户积分分页
     *
     * @param pageReqVO 分页查询
     * @return 用户积分分页
     */
    PageResult<ParkPointsDO> getParkPointsPage(ParkPointsPageReqVO pageReqVO);

}
