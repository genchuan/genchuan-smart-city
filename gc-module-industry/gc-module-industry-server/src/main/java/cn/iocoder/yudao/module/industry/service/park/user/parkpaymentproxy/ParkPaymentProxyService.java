package cn.iocoder.yudao.module.industry.service.park.user.parkpaymentproxy;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkpaymentproxy.vo.ParkPaymentProxyPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkpaymentproxy.vo.ParkPaymentProxySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkpaymentproxy.ParkPaymentProxyDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 代付规则 Service 接口
 *
 * @author lxs
 */
public interface ParkPaymentProxyService {

    /**
     * 创建代付规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkPaymentProxy(@Valid ParkPaymentProxySaveReqVO createReqVO);

    /**
     * 更新代付规则
     *
     * @param updateReqVO 更新信息
     */
    void updateParkPaymentProxy(@Valid ParkPaymentProxySaveReqVO updateReqVO);

    /**
     * 删除代付规则
     *
     * @param id 编号
     */
    void deleteParkPaymentProxy(Long id);

    /**
     * 获得代付规则
     *
     * @param id 编号
     * @return 代付规则
     */
    ParkPaymentProxyDO getParkPaymentProxy(Long id);

    /**
     * 获得代付规则分页
     *
     * @param pageReqVO 分页查询
     * @return 代付规则分页
     */
    PageResult<ParkPaymentProxyDO> getParkPaymentProxyPage(ParkPaymentProxyPageReqVO pageReqVO);

}
