package cn.iocoder.yudao.module.vehiclecharging.service.ratesetting;

import java.util.*;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.ratesetting.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.ratesetting.RateSettingDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 费率设置 Service 接口
 *
 * @author 亘川智城
 */
public interface RateSettingService {

    /**
     * 创建费率设置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRateSetting(@Valid RateSettingSaveReqVO createReqVO);

    /**
     * 更新费率设置
     *
     * @param updateReqVO 更新信息
     */
    void updateRateSetting(@Valid RateSettingSaveReqVO updateReqVO);

    /**
     * 删除费率设置
     *
     * @param id 编号
     */
    void deleteRateSetting(Long id);

    /**
    * 批量删除费率设置
    *
    * @param ids 编号
    */
    void deleteRateSettingListByIds(List<Long> ids);

    /**
     * 获得费率设置
     *
     * @param id 编号
     * @return 费率设置
     */
    RateSettingDO getRateSetting(Long id);

    /**
     * 获得费率设置分页
     *
     * @param pageReqVO 分页查询
     * @return 费率设置分页
     */
    PageResult<RateSettingRespVO> getRateSettingPage(RateSettingPageReqVO pageReqVO);



    void disableRateSetting(RateSettingDisableReqVO reqVO);

    void enableRateSetting(RateSettingEnableReqVO reqVO);

    void copyRateSetting(RateSettingCopyReqVO reqVO);
}
