package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.bizmngcompext;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompext.vo.BizMngCompExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompext.vo.BizMngCompExtSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompext.BizMngCompExtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.bizmngcompext.BizMngCompExtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理部件扩展管理部件配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class BizMngCompExtServiceImpl implements BizMngCompExtService {

    @Resource
    private BizMngCompExtMapper bizMngCompExtMapper;

    @Override
    public Long createBizMngCompExt(BizMngCompExtSaveReqVO createReqVO) {
        // 插入
        BizMngCompExtDO bizMngCompExt = BeanUtils.toBean(createReqVO, BizMngCompExtDO.class);
        bizMngCompExtMapper.insert(bizMngCompExt);
        // 返回
        return bizMngCompExt.getId();
    }

    @Override
    public void updateBizMngCompExt(BizMngCompExtSaveReqVO updateReqVO) {
        // 校验存在
        validateBizMngCompExtExists(updateReqVO.getId());
        // 更新
        BizMngCompExtDO updateObj = BeanUtils.toBean(updateReqVO, BizMngCompExtDO.class);
        bizMngCompExtMapper.updateById(updateObj);
    }

    @Override
    public void deleteBizMngCompExt(Long id) {
        // 校验存在
        validateBizMngCompExtExists(id);
        // 删除
        bizMngCompExtMapper.deleteById(id);
    }

    private void validateBizMngCompExtExists(Long id) {
        if (bizMngCompExtMapper.selectById(id) == null) {
            throw exception(BIZ_MNG_COMP_EXT_NOT_EXISTS);
        }
    }

    @Override
    public BizMngCompExtDO getBizMngCompExt(Long id) {
        return bizMngCompExtMapper.selectById(id);
    }

    @Override
    public PageResult<BizMngCompExtDO> getBizMngCompExtPage(BizMngCompExtPageReqVO pageReqVO) {
        return bizMngCompExtMapper.selectPage(pageReqVO);
    }

}