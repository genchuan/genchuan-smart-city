package cn.iocoder.yudao.module.park.service.park.basicAssociation.itemtype;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.itemtype.vo.ItemTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.itemtype.vo.ItemTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.itemtype.ItemTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.itemtype.ItemTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 管理事项类别 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ItemTypeServiceImpl implements ItemTypeService {

    @Resource
    private ItemTypeMapper itemTypeMapper;

    @Override
    public Long createItemType(ItemTypeSaveReqVO createReqVO) {
        // 插入
        ItemTypeDO itemType = BeanUtils.toBean(createReqVO, ItemTypeDO.class);
        itemTypeMapper.insert(itemType);
        // 返回
        return itemType.getId();
    }

    @Override
    public void updateItemType(ItemTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateItemTypeExists(updateReqVO.getId());
        // 更新
        ItemTypeDO updateObj = BeanUtils.toBean(updateReqVO, ItemTypeDO.class);
        itemTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteItemType(Long id) {
        // 校验存在
        validateItemTypeExists(id);
        // 删除
        itemTypeMapper.deleteById(id);
    }

    private void validateItemTypeExists(Long id) {
        if (itemTypeMapper.selectById(id) == null) {
            throw exception(ITEM_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public ItemTypeDO getItemType(Long id) {
        return itemTypeMapper.selectById(id);
    }

    @Override
    public PageResult<ItemTypeDO> getItemTypePage(ItemTypePageReqVO pageReqVO) {
        return itemTypeMapper.selectPage(pageReqVO);
    }

}