package cn.iocoder.yudao.module.evaluate.service.standarditem;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standarditem.vo.StandardItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standarditem.vo.StandardItemSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.standarditem.StandardItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.standarditem.StandardItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 标准项 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class StandardItemServiceImpl implements StandardItemService {

    @Resource
    private StandardItemMapper standardItemMapper;

    @Override
    public Long createStandardItem(StandardItemSaveReqVO createReqVO) {
        // 插入
        StandardItemDO standardItem = BeanUtils.toBean(createReqVO, StandardItemDO.class);
        standardItemMapper.insert(standardItem);
        // 返回
        return standardItem.getId();
    }

    @Override
    public void updateStandardItem(StandardItemSaveReqVO updateReqVO) {
        // 校验存在
        validateStandardItemExists(updateReqVO.getId());
        // 更新
        StandardItemDO updateObj = BeanUtils.toBean(updateReqVO, StandardItemDO.class);
        standardItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteStandardItem(Long id) {
        // 校验存在
        validateStandardItemExists(id);
        // 删除
        standardItemMapper.deleteById(id);
    }

    private void validateStandardItemExists(Long id) {
        if (standardItemMapper.selectById(id) == null) {
            throw exception(STANDARD_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public StandardItemDO getStandardItem(Long id) {
        return standardItemMapper.selectById(id);
    }

    @Override
    public PageResult<StandardItemDO> getStandardItemPage(StandardItemPageReqVO pageReqVO) {
        return standardItemMapper.selectPage(pageReqVO);
    }

}