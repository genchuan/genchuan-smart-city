package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivmngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngcomp.vo.DomDivMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngcomp.vo.DomDivMngCompSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmngcomp.DomDivMngCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivmngcomp.DomDivMngCompMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理部件分域 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomDivMngCompServiceImpl implements DomDivMngCompService {

    @Resource
    private DomDivMngCompMapper domDivMngCompMapper;

    @Override
    public Long createDomDivMngComp(DomDivMngCompSaveReqVO createReqVO) {
        // 插入
        DomDivMngCompDO domDivMngComp = BeanUtils.toBean(createReqVO, DomDivMngCompDO.class);
        domDivMngCompMapper.insert(domDivMngComp);
        // 返回
        return domDivMngComp.getId();
    }

    @Override
    public void updateDomDivMngComp(DomDivMngCompSaveReqVO updateReqVO) {
        // 校验存在
        validateDomDivMngCompExists(updateReqVO.getId());
        // 更新
        DomDivMngCompDO updateObj = BeanUtils.toBean(updateReqVO, DomDivMngCompDO.class);
        domDivMngCompMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomDivMngComp(Long id) {
        // 校验存在
        validateDomDivMngCompExists(id);
        // 删除
        domDivMngCompMapper.deleteById(id);
    }

    private void validateDomDivMngCompExists(Long id) {
        if (domDivMngCompMapper.selectById(id) == null) {
            throw exception(DOM_DIV_MNG_COMP_NOT_EXISTS);
        }
    }

    @Override
    public DomDivMngCompDO getDomDivMngComp(Long id) {
        return domDivMngCompMapper.selectById(id);
    }

    @Override
    public PageResult<DomDivMngCompDO> getDomDivMngCompPage(DomDivMngCompPageReqVO pageReqVO) {
        return domDivMngCompMapper.selectPage(pageReqVO);
    }

}