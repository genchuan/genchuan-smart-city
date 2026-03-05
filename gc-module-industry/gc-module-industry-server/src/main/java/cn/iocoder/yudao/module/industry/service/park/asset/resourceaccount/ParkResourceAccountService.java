package cn.iocoder.yudao.module.industry.service.park.asset.resourceaccount;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.resourceaccount.vo.ParkResourceAccountPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.resourceaccount.vo.ParkResourceAccountSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.resourceaccount.ParkResourceAccountDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资源台账 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkResourceAccountService {

    /**
     * 创建资源台账
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkResourceAccount(@Valid ParkResourceAccountSaveReqVO createReqVO);

    /**
     * 更新资源台账
     *
     * @param updateReqVO 更新信息
     */
    void updateParkResourceAccount(@Valid ParkResourceAccountSaveReqVO updateReqVO);

    /**
     * 删除资源台账
     *
     * @param id 编号
     */
    void deleteParkResourceAccount(Long id);

    /**
     * 获得资源台账
     *
     * @param id 编号
     * @return 资源台账
     */
    ParkResourceAccountDO getParkResourceAccount(Long id);

    /**
     * 获得资源台账分页
     *
     * @param pageReqVO 分页查询
     * @return 资源台账分页
     */
    PageResult<ParkResourceAccountDO> getParkResourceAccountPage(ParkResourceAccountPageReqVO pageReqVO);

}