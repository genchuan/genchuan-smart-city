package cn.iocoder.yudao.module.industry.service.park.pay.parkberthlock;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkberthlock.vo.ParkBerthLockPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkberthlock.vo.ParkBerthLockSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkberthlock.ParkBerthLockDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 泊位锁定记录 Service 接口
 *
 * @author lxs
 */
public interface ParkBerthLockService {

    /**
     * 创建泊位锁定记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkBerthLock(@Valid ParkBerthLockSaveReqVO createReqVO);

    /**
     * 更新泊位锁定记录
     *
     * @param updateReqVO 更新信息
     */
    void updateParkBerthLock(@Valid ParkBerthLockSaveReqVO updateReqVO);

    /**
     * 删除泊位锁定记录
     *
     * @param id 编号
     */
    void deleteParkBerthLock(Long id);

    /**
     * 获得泊位锁定记录
     *
     * @param id 编号
     * @return 泊位锁定记录
     */
    ParkBerthLockDO getParkBerthLock(Long id);

    /**
     * 获得泊位锁定记录分页
     *
     * @param pageReqVO 分页查询
     * @return 泊位锁定记录分页
     */
    PageResult<ParkBerthLockDO> getParkBerthLockPage(ParkBerthLockPageReqVO pageReqVO);

}
