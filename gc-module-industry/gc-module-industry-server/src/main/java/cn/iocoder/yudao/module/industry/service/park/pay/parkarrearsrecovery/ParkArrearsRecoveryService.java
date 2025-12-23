package cn.iocoder.yudao.module.industry.service.park.pay.parkarrearsrecovery;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkarrearsrecovery.vo.ParkArrearsRecoveryPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkarrearsrecovery.vo.ParkArrearsRecoverySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkarrearsrecovery.ParkArrearsRecoveryDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 欠费追缴 Service 接口
 *
 * @author lxs
 */
public interface ParkArrearsRecoveryService {

    /**
     * 创建欠费追缴
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkArrearsRecovery(@Valid ParkArrearsRecoverySaveReqVO createReqVO);

    /**
     * 更新欠费追缴
     *
     * @param updateReqVO 更新信息
     */
    void updateParkArrearsRecovery(@Valid ParkArrearsRecoverySaveReqVO updateReqVO);

    /**
     * 删除欠费追缴
     *
     * @param id 编号
     */
    void deleteParkArrearsRecovery(Long id);

    /**
     * 获得欠费追缴
     *
     * @param id 编号
     * @return 欠费追缴
     */
    ParkArrearsRecoveryDO getParkArrearsRecovery(Long id);

    /**
     * 获得欠费追缴分页
     *
     * @param pageReqVO 分页查询
     * @return 欠费追缴分页
     */
    PageResult<ParkArrearsRecoveryDO> getParkArrearsRecoveryPage(ParkArrearsRecoveryPageReqVO pageReqVO);

}
