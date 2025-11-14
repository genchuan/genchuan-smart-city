package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivmoncomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmoncomp.vo.DomDivMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmoncomp.vo.DomDivMonCompSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmoncomp.DomDivMonCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivmoncomp.DomDivMonCompMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测部件分域 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomDivMonCompServiceImpl implements DomDivMonCompService {

    @Resource
    private DomDivMonCompMapper domDivMonCompMapper;

    @Override
    public Long createDomDivMonComp(DomDivMonCompSaveReqVO createReqVO) {
        // 插入
        DomDivMonCompDO domDivMonComp = BeanUtils.toBean(createReqVO, DomDivMonCompDO.class);
        domDivMonCompMapper.insert(domDivMonComp);
        // 返回
        return domDivMonComp.getId();
    }

    @Override
    public void updateDomDivMonComp(DomDivMonCompSaveReqVO updateReqVO) {
        // 校验存在
        validateDomDivMonCompExists(updateReqVO.getId());
        // 更新
        DomDivMonCompDO updateObj = BeanUtils.toBean(updateReqVO, DomDivMonCompDO.class);
        domDivMonCompMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomDivMonComp(Long id) {
        // 校验存在
        validateDomDivMonCompExists(id);
        // 删除
        domDivMonCompMapper.deleteById(id);
    }

    private void validateDomDivMonCompExists(Long id) {
        if (domDivMonCompMapper.selectById(id) == null) {
            throw exception(DOM_DIV_MON_COMP_NOT_EXISTS);
        }
    }

    @Override
    public DomDivMonCompDO getDomDivMonComp(Long id) {
        return domDivMonCompMapper.selectById(id);
    }

    @Override
    public PageResult<DomDivMonCompDO> getDomDivMonCompPage(DomDivMonCompPageReqVO pageReqVO) {
        return domDivMonCompMapper.selectPage(pageReqVO);
    }

}