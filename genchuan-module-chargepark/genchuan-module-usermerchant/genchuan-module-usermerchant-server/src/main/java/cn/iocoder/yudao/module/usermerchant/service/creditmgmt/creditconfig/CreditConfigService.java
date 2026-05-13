package cn.iocoder.yudao.module.usermerchant.service.creditmgmt.creditconfig;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.creditconfig.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.creditmgmt.creditconfig.CreditConfigDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 信用配置 Service 接口
 *
 * @author 亘川智城
 */
public interface CreditConfigService {

    /**
     * 创建信用配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCreditConfig(@Valid CreditConfigSaveReqVO createReqVO);

    /**
     * 更新信用配置
     *
     * @param updateReqVO 更新信息
     */
    void updateCreditConfig(@Valid CreditConfigSaveReqVO updateReqVO);

    /**
     * 删除信用配置
     *
     * @param id 编号
     */
    void deleteCreditConfig(Long id);

    /**
    * 批量删除信用配置
    *
    * @param ids 编号
    */
    void deleteCreditConfigListByIds(List<Long> ids);

    /**
     * 获得信用配置
     *
     * @param id 编号
     * @return 信用配置
     */
    CreditConfigDO getCreditConfig(Long id);

    /**
     * 获得信用配置分页
     *
     * @param pageReqVO 分页查询
     * @return 信用配置分页
     */
    PageResult<CreditConfigDO> getCreditConfigPage(CreditConfigPageReqVO pageReqVO);

    /**
     * 生效/失效信用配置
     *
     * @param ids ids
     * @param status 状态
     */
    void updateConfigStatus(List<Long> ids, String status);

    /**
     * 保存信用配置
     *
     * @param saveReqVO 保存信息
     */
    void saveConfig(@Valid CreditConfigSaveReqVO saveReqVO);

    /**
     * 获取信用配置图表数据
     *
     * @param chartReqVO 时间范围
     * @return 图表数据
     */
    CreditConfigChartRespVO getCreditConfigChart(@Valid CreditConfigChartReqVO chartReqVO);
}