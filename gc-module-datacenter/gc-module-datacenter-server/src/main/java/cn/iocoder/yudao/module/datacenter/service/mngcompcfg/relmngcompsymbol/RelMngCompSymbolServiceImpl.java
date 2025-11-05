package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.relmngcompsymbol;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol.vo.RelMngCompSymbolPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol.vo.RelMngCompSymbolSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.relmngcompsymbol.RelMngCompSymbolDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.relmngcompsymbol.RelMngCompSymbolMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理部件图示关联 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RelMngCompSymbolServiceImpl implements RelMngCompSymbolService {

    @Resource
    private RelMngCompSymbolMapper relMngCompSymbolMapper;

    @Override
    public Long createRelMngCompSymbol(RelMngCompSymbolSaveReqVO createReqVO) {
        // 插入
        RelMngCompSymbolDO relMngCompSymbol = BeanUtils.toBean(createReqVO, RelMngCompSymbolDO.class);
        relMngCompSymbolMapper.insert(relMngCompSymbol);
        // 返回
        return relMngCompSymbol.getId();
    }

    @Override
    public void updateRelMngCompSymbol(RelMngCompSymbolSaveReqVO updateReqVO) {
        // 校验存在
        validateRelMngCompSymbolExists(updateReqVO.getId());
        // 更新
        RelMngCompSymbolDO updateObj = BeanUtils.toBean(updateReqVO, RelMngCompSymbolDO.class);
        relMngCompSymbolMapper.updateById(updateObj);
    }

    @Override
    public void deleteRelMngCompSymbol(Long id) {
        // 校验存在
        validateRelMngCompSymbolExists(id);
        // 删除
        relMngCompSymbolMapper.deleteById(id);
    }

    private void validateRelMngCompSymbolExists(Long id) {
        if (relMngCompSymbolMapper.selectById(id) == null) {
            throw exception(REL_MNG_COMP_SYMBOL_NOT_EXISTS);
        }
    }

    @Override
    public RelMngCompSymbolDO getRelMngCompSymbol(Long id) {
        return relMngCompSymbolMapper.selectById(id);
    }

    @Override
    public PageResult<RelMngCompSymbolDO> getRelMngCompSymbolPage(RelMngCompSymbolPageReqVO pageReqVO) {
        return relMngCompSymbolMapper.selectPage(pageReqVO);
    }

}