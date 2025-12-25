package cn.iocoder.yudao.module.industry.service.park.pay.statparkpay;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo.StatParkPayGenReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo.StatParkPayPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo.StatParkPaySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.statparkpay.StatParkPayDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 缴费统计 Service 接口
 *
 * @author lxs
 */
public interface StatParkPayService {

    /**
     * 创建缴费统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStatParkPay(@Valid StatParkPayGenReqVO createReqVO);

    /**
     * 更新缴费统计
     *
     * @param updateReqVO 更新信息
     */
    void updateStatParkPay(@Valid StatParkPaySaveReqVO updateReqVO);

    /**
     * 删除缴费统计
     *
     * @param id 编号
     */
    void deleteStatParkPay(Long id);

    /**
     * 获得缴费统计
     *
     * @param id 编号
     * @return 缴费统计
     */
    StatParkPayDO getStatParkPay(Long id);

    /**
     * 获得缴费统计分页
     *
     * @param pageReqVO 分页查询
     * @return 缴费统计分页
     */
    PageResult<StatParkPayDO> getStatParkPayPage(StatParkPayPageReqVO pageReqVO);

}
