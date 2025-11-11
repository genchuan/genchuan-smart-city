package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivmngmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngmatter.vo.DomDivMngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngmatter.vo.DomDivMngMatterSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmngmatter.DomDivMngMatterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivmngmatter.DomDivMngMatterMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理事项分域 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomDivMngMatterServiceImpl implements DomDivMngMatterService {

    @Resource
    private DomDivMngMatterMapper domDivMngMatterMapper;

    @Override
    public Long createDomDivMngMatter(DomDivMngMatterSaveReqVO createReqVO) {
        // 插入
        DomDivMngMatterDO domDivMngMatter = BeanUtils.toBean(createReqVO, DomDivMngMatterDO.class);
        domDivMngMatterMapper.insert(domDivMngMatter);
        // 返回
        return domDivMngMatter.getId();
    }

    @Override
    public void updateDomDivMngMatter(DomDivMngMatterSaveReqVO updateReqVO) {
        // 校验存在
        validateDomDivMngMatterExists(updateReqVO.getId());
        // 更新
        DomDivMngMatterDO updateObj = BeanUtils.toBean(updateReqVO, DomDivMngMatterDO.class);
        domDivMngMatterMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomDivMngMatter(Long id) {
        // 校验存在
        validateDomDivMngMatterExists(id);
        // 删除
        domDivMngMatterMapper.deleteById(id);
    }

    private void validateDomDivMngMatterExists(Long id) {
        if (domDivMngMatterMapper.selectById(id) == null) {
            throw exception(DOM_DIV_MNG_MATTER_NOT_EXISTS);
        }
    }

    @Override
    public DomDivMngMatterDO getDomDivMngMatter(Long id) {
        return domDivMngMatterMapper.selectById(id);
    }

    @Override
    public PageResult<DomDivMngMatterDO> getDomDivMngMatterPage(DomDivMngMatterPageReqVO pageReqVO) {
        return domDivMngMatterMapper.selectPage(pageReqVO);
    }

}