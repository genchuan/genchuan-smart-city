package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.chargeparklink;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 充停联动 Service 接口
 *
 * @author 亘川智城
 */
public interface ChargeParkLinkService {

    /**
     * 创建充停联动
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createChargeParkLink(@Valid ChargeParkLinkSaveReqVO createReqVO);

    /**
     * 更新充停联动
     *
     * @param updateReqVO 更新信息
     */
    void updateChargeParkLink(@Valid ChargeParkLinkSaveReqVO updateReqVO);

    /**
     * 删除充停联动
     *
     * @param id 编号
     */
    void deleteChargeParkLink(Long id);

    /**
    * 批量删除充停联动
    *
    * @param ids 编号
    */
    void deleteChargeParkLinkListByIds(List<Long> ids);

    /**
     * 获得充停联动
     *
     * @param id 编号
     * @return 充停联动
     */
    ChargeParkLinkDO getChargeParkLink(Long id);

    /**
     * 获得充停联动分页
     *
     * @param pageReqVO 分页查询
     * @return 充停联动分页
     */
    PageResult<ChargeParkLinkDO> getChargeParkLinkPage(ChargeParkLinkPageReqVO pageReqVO);

}
