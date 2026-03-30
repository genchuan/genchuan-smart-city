package cn.iocoder.yudao.module.evaluate.service.scope;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.scope.vo.ScopePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.scope.vo.ScopeSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.scope.ScopeDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.scope.ScopeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.SCOPE_NOT_EXISTS;

/**
 * 范围字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ScopeServiceImpl implements ScopeService {

    @Resource
    private ScopeMapper scopeMapper;

    @Override
    public Long createScope(ScopeSaveReqVO createReqVO) {
        // 插入
        ScopeDO scope = BeanUtils.toBean(createReqVO, ScopeDO.class);
        scopeMapper.insert(scope);
        // 返回
        return scope.getId();
    }

    @Override
    public void updateScope(ScopeSaveReqVO updateReqVO) {
        // 校验存在
        validateScopeExists(updateReqVO.getId());
        // 更新
        ScopeDO updateObj = BeanUtils.toBean(updateReqVO, ScopeDO.class);
        scopeMapper.updateById(updateObj);
    }

    @Override
    public void deleteScope(Long id) {
        // 校验存在
        validateScopeExists(id);
        // 删除
        scopeMapper.deleteById(id);
    }

    private void validateScopeExists(Long id) {
        if (scopeMapper.selectById(id) == null) {
            throw exception(SCOPE_NOT_EXISTS);
        }
    }

    @Override
    public ScopeDO getScope(Long id) {
        return scopeMapper.selectById(id);
    }

    @Override
    public PageResult<ScopeDO> getScopePage(ScopePageReqVO pageReqVO) {
        return scopeMapper.selectPage(pageReqVO);
    }

}