package cn.iocoder.yudao.module.park.service.park.user.blackwhitelist;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.blackwhitelist.vo.BlackWhiteListPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.blackwhitelist.vo.BlackWhiteListSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.blackwhitelist.BlackWhiteListDO;
import jakarta.validation.Valid;

/**
 * 黑白名单 Service 接口
 *
 * @author 亘川智城
 */
public interface BlackWhiteListService {

    /**
     * 通过车牌校验是否为白名单
     * @param carNumber
     * @return 是否为白名单
     */
    boolean verifyWhitelistByCarNumber(String carNumber);
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
