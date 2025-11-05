package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.bizmngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcomp.vo.BizMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcomp.vo.BizMngCompSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcomp.BizMngCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.bizmngcomp.BizMngCompMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理部件信息管理 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class BizMngCompServiceImpl implements BizMngCompService {

    @Resource
    private BizMngCompMapper bizMngCompMapper;

    @Override
    public Long createBizMngComp(BizMngCompSaveReqVO createReqVO) {
        // 插入
        BizMngCompDO bizMngComp = BeanUtils.toBean(createReqVO, BizMngCompDO.class);
        bizMngCompMapper.insert(bizMngComp);
        // 返回
        return bizMngComp.getId();
    }

    @Override
    public void updateBizMngComp(BizMngCompSaveReqVO updateReqVO) {
        // 校验存在
        validateBizMngCompExists(updateReqVO.getId());
        // 更新
        BizMngCompDO updateObj = BeanUtils.toBean(updateReqVO, BizMngCompDO.class);
        bizMngCompMapper.updateById(updateObj);
    }

    @Override
    public void deleteBizMngComp(Long id) {
        // 校验存在
        validateBizMngCompExists(id);
        // 删除
        bizMngCompMapper.deleteById(id);
    }

    private void validateBizMngCompExists(Long id) {
        if (bizMngCompMapper.selectById(id) == null) {
            throw exception(BIZ_MNG_COMP_NOT_EXISTS);
        }
    }

    @Override
    public BizMngCompDO getBizMngComp(Long id) {
        return bizMngCompMapper.selectById(id);
    }

    @Override
    public PageResult<BizMngCompDO> getBizMngCompPage(BizMngCompPageReqVO pageReqVO) {
        return bizMngCompMapper.selectPage(pageReqVO);
    }

}