package cn.iocoder.yudao.module.park.service.park.basicAssociation.itemtype;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.itemtype.vo.ItemTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.itemtype.vo.ItemTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.itemtype.ItemTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理事项类别 Service 接口
 *
 * @author zhucongquan
 */
public interface ItemTypeService {

    /**
     * 创建管理事项类别
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createItemType(@Valid ItemTypeSaveReqVO createReqVO);

    /**
     * 更新管理事项类别
     *
     * @param updateReqVO 更新信息
     */
    void updateItemType(@Valid ItemTypeSaveReqVO updateReqVO);

    /**
     * 删除管理事项类别
     *
     * @param id 编号
     */
    void deleteItemType(Long id);

    /**
     * 获得管理事项类别
     *
     * @param id 编号
     * @return 管理事项类别
     */
    ItemTypeDO getItemType(Long id);

    /**
     * 获得管理事项类别分页
     *
     * @param pageReqVO 分页查询
     * @return 管理事项类别分页
     */
    PageResult<ItemTypeDO> getItemTypePage(ItemTypePageReqVO pageReqVO);

}