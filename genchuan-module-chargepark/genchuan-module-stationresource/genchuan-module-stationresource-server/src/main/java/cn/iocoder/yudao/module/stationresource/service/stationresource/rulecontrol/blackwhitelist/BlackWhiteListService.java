package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.blackwhitelist;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 黑白名单 Service 接口
 *
 * @author 亘川智城
 */
public interface BlackWhiteListService {

    /**
     * 创建黑白名单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBlackWhiteList(@Valid BlackWhiteListSaveReqVO createReqVO);

    /**
     * 更新黑白名单
     *
     * @param updateReqVO 更新信息
     */
    void updateBlackWhiteList(@Valid BlackWhiteListSaveReqVO updateReqVO);

    /**
     * 删除黑白名单
     *
     * @param id 编号
     */
    void deleteBlackWhiteList(Long id);

    /**
    * 批量删除黑白名单
    *
    * @param ids 编号
    */
    void deleteBlackWhiteListListByIds(List<Long> ids);

    /**
     * 获得黑白名单
     *
     * @param id 编号
     * @return 黑白名单
     */
    BlackWhiteListDO getBlackWhiteList(Long id);

    /**
     * 获得黑白名单分页
     *
     * @param pageReqVO 分页查询
     * @return 黑白名单分页
     */
    PageResult<BlackWhiteListDO> getBlackWhiteListPage(BlackWhiteListPageReqVO pageReqVO);

}
