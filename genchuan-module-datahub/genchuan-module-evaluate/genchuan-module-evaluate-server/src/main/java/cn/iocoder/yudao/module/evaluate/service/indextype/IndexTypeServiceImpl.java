package cn.iocoder.yudao.module.evaluate.service.indextype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.indextype.vo.IndexTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.indextype.vo.IndexTypeSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.indextype.IndexTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indextype.IndexTypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.INDEX_TYPE_NOT_EXISTS;

/**
 * 指标类型字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class IndexTypeServiceImpl implements IndexTypeService {

    @Resource
    private IndexTypeMapper indexTypeMapper;

    @Override
    public Long createIndexType(IndexTypeSaveReqVO createReqVO) {
        // 插入
        IndexTypeDO indexType = BeanUtils.toBean(createReqVO, IndexTypeDO.class);
        indexTypeMapper.insert(indexType);
        // 返回
        return indexType.getId();
    }

    @Override
    public void updateIndexType(IndexTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateIndexTypeExists(updateReqVO.getId());
        // 更新
        IndexTypeDO updateObj = BeanUtils.toBean(updateReqVO, IndexTypeDO.class);
        indexTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteIndexType(Long id) {
        // 校验存在
        validateIndexTypeExists(id);
        // 删除
        indexTypeMapper.deleteById(id);
    }

    private void validateIndexTypeExists(Long id) {
        if (indexTypeMapper.selectById(id) == null) {
            throw exception(INDEX_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public IndexTypeDO getIndexType(Long id) {
        return indexTypeMapper.selectById(id);
    }

    @Override
    public PageResult<IndexTypeDO> getIndexTypePage(IndexTypePageReqVO pageReqVO) {
        return indexTypeMapper.selectPage(pageReqVO);
    }

}