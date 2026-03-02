package cn.iocoder.yudao.module.evaluate.service.collecttype;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.collecttype.vo.CollectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.collecttype.vo.CollectTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.collecttype.CollectTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.collecttype.CollectTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 采集方式字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CollectTypeServiceImpl implements CollectTypeService {

    @Resource
    private CollectTypeMapper collectTypeMapper;

    @Override
    public Long createCollectType(CollectTypeSaveReqVO createReqVO) {
        // 插入
        CollectTypeDO collectType = BeanUtils.toBean(createReqVO, CollectTypeDO.class);
        collectTypeMapper.insert(collectType);
        // 返回
        return collectType.getId();
    }

    @Override
    public void updateCollectType(CollectTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateCollectTypeExists(updateReqVO.getId());
        // 更新
        CollectTypeDO updateObj = BeanUtils.toBean(updateReqVO, CollectTypeDO.class);
        collectTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteCollectType(Long id) {
        // 校验存在
        validateCollectTypeExists(id);
        // 删除
        collectTypeMapper.deleteById(id);
    }

    private void validateCollectTypeExists(Long id) {
        if (collectTypeMapper.selectById(id) == null) {
            throw exception(COLLECT_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public CollectTypeDO getCollectType(Long id) {
        return collectTypeMapper.selectById(id);
    }

    @Override
    public PageResult<CollectTypeDO> getCollectTypePage(CollectTypePageReqVO pageReqVO) {
        return collectTypeMapper.selectPage(pageReqVO);
    }

}