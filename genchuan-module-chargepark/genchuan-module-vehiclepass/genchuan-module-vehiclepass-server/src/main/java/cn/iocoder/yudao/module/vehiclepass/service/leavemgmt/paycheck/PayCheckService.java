package cn.iocoder.yudao.module.vehiclepass.service.leavemgmt.paycheck;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.paycheck.PayCheckDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 缴费核验 Service 接口
 *
 * @author 亘川智城
 */
public interface PayCheckService {

    /**
     * 创建缴费核验
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCheck(@Valid PayCheckSaveReqVO createReqVO);

    /**
     * 更新缴费核验
     *
     * @param updateReqVO 更新信息
     */
    void updateCheck(@Valid PayCheckSaveReqVO updateReqVO);

    /**
     * 删除缴费核验
     *
     * @param id 编号
     */
    void deleteCheck(Long id);

    /**
     * 批量删除缴费核验
     *
     * @param ids 编号
     */
    void deleteCheckListByIds(List<Long> ids);

    /**
     * 获得缴费核验
     *
     * @param id 编号
     * @return 缴费核验
     */
    PayCheckDO getCheck(Long id);

    /**
     * 获得缴费核验分页
     *
     * @param pageReqVO 分页查询
     * @return 缴费核验分页
     */
    PageResult<PayCheckDO> getCheckPage(PayCheckPageReqVO pageReqVO);

}