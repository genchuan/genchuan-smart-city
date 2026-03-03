package cn.iocoder.yudao.module.evaluate.service.indexitem;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexitem.vo.IndexItemSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.indexitem.IndexItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 指标项 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class IndexItemServiceImpl implements IndexItemService {

    @Resource
    private IndexItemMapper indexItemMapper;

    @Override
    public Long createIndexItem(IndexItemSaveReqVO createReqVO) {
        // 插入
        IndexItemDO indexItem = BeanUtils.toBean(createReqVO, IndexItemDO.class);
        indexItemMapper.insert(indexItem);
        // 返回
        return indexItem.getId();
    }

    @Override
    public void updateIndexItem(IndexItemSaveReqVO updateReqVO) {
        // 校验存在
        validateIndexItemExists(updateReqVO.getId());
        // 更新
        IndexItemDO updateObj = BeanUtils.toBean(updateReqVO, IndexItemDO.class);
        indexItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteIndexItem(Long id) {
        // 校验存在
        validateIndexItemExists(id);
        // 删除
        indexItemMapper.deleteById(id);
    }

    private void validateIndexItemExists(Long id) {
        if (indexItemMapper.selectById(id) == null) {
            throw exception(INDEX_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public IndexItemDO getIndexItem(Long id) {
        return indexItemMapper.selectById(id);
    }

    @Override
    public PageResult<IndexItemDO> getIndexItemPage(IndexItemPageReqVO pageReqVO) {
        return indexItemMapper.selectPage(pageReqVO);
    }

}