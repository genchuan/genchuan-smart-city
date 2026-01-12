package cn.iocoder.yudao.module.industry.service.park.user.parkblackwhitelist;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkblackwhitelist.vo.ParkBlackWhiteListPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkblackwhitelist.vo.ParkBlackWhiteListSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkblackwhitelist.ParkBlackWhiteListDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 黑白名单 Service 接口
 *
 * @author lxs
 */
public interface ParkBlackWhiteListService {

    /**
     * 创建黑白名单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkBlackWhiteList(@Valid ParkBlackWhiteListSaveReqVO createReqVO);

    /**
     * 更新黑白名单
     *
     * @param updateReqVO 更新信息
     */
    void updateParkBlackWhiteList(@Valid ParkBlackWhiteListSaveReqVO updateReqVO);

    /**
     * 删除黑白名单
     *
     * @param id 编号
     */
    void deleteParkBlackWhiteList(Long id);

    /**
     * 获得黑白名单
     *
     * @param id 编号
     * @return 黑白名单
     */
    ParkBlackWhiteListDO getParkBlackWhiteList(Long id);

    /**
     * 获得黑白名单分页
     *
     * @param pageReqVO 分页查询
     * @return 黑白名单分页
     */
    PageResult<ParkBlackWhiteListDO> getParkBlackWhiteListPage(ParkBlackWhiteListPageReqVO pageReqVO);

}
