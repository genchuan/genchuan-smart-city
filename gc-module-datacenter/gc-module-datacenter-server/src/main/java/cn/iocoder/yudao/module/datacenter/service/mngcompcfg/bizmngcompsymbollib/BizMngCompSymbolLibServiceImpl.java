package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.bizmngcompsymbollib;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompsymbollib.vo.BizMngCompSymbolLibPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompsymbollib.vo.BizMngCompSymbolLibSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompsymbollib.BizMngCompSymbolLibDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.bizmngcompsymbollib.BizMngCompSymbolLibMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理部件图示符号库 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class BizMngCompSymbolLibServiceImpl implements BizMngCompSymbolLibService {

    @Resource
    private BizMngCompSymbolLibMapper bizMngCompSymbolLibMapper;

    @Override
    public Long createBizMngCompSymbolLib(BizMngCompSymbolLibSaveReqVO createReqVO) {
        // 插入
        BizMngCompSymbolLibDO bizMngCompSymbolLib = BeanUtils.toBean(createReqVO, BizMngCompSymbolLibDO.class);
        bizMngCompSymbolLibMapper.insert(bizMngCompSymbolLib);
        // 返回
        return bizMngCompSymbolLib.getId();
    }

    @Override
    public void updateBizMngCompSymbolLib(BizMngCompSymbolLibSaveReqVO updateReqVO) {
        // 校验存在
        validateBizMngCompSymbolLibExists(updateReqVO.getId());
        // 更新
        BizMngCompSymbolLibDO updateObj = BeanUtils.toBean(updateReqVO, BizMngCompSymbolLibDO.class);
        bizMngCompSymbolLibMapper.updateById(updateObj);
    }

    @Override
    public void deleteBizMngCompSymbolLib(Long id) {
        // 校验存在
        validateBizMngCompSymbolLibExists(id);
        // 删除
        bizMngCompSymbolLibMapper.deleteById(id);
    }

    private void validateBizMngCompSymbolLibExists(Long id) {
        if (bizMngCompSymbolLibMapper.selectById(id) == null) {
            throw exception(BIZ_MNG_COMP_SYMBOL_LIB_NOT_EXISTS);
        }
    }

    @Override
    public BizMngCompSymbolLibDO getBizMngCompSymbolLib(Long id) {
        return bizMngCompSymbolLibMapper.selectById(id);
    }

    @Override
    public PageResult<BizMngCompSymbolLibDO> getBizMngCompSymbolLibPage(BizMngCompSymbolLibPageReqVO pageReqVO) {
        return bizMngCompSymbolLibMapper.selectPage(pageReqVO);
    }

}