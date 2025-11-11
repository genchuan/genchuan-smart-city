package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivadmin;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivadmin.vo.DomDivAdminPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivadmin.vo.DomDivAdminSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivadmin.DomDivAdminDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivadmin.DomDivAdminMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 行政区划分域 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomDivAdminServiceImpl implements DomDivAdminService {

    @Resource
    private DomDivAdminMapper domDivAdminMapper;

    @Override
    public Long createDomDivAdmin(DomDivAdminSaveReqVO createReqVO) {
        // 插入
        DomDivAdminDO domDivAdmin = BeanUtils.toBean(createReqVO, DomDivAdminDO.class);
        domDivAdminMapper.insert(domDivAdmin);
        // 返回
        return domDivAdmin.getId();
    }

    @Override
    public void updateDomDivAdmin(DomDivAdminSaveReqVO updateReqVO) {
        // 校验存在
        validateDomDivAdminExists(updateReqVO.getId());
        // 更新
        DomDivAdminDO updateObj = BeanUtils.toBean(updateReqVO, DomDivAdminDO.class);
        domDivAdminMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomDivAdmin(Long id) {
        // 校验存在
        validateDomDivAdminExists(id);
        // 删除
        domDivAdminMapper.deleteById(id);
    }

    private void validateDomDivAdminExists(Long id) {
        if (domDivAdminMapper.selectById(id) == null) {
            throw exception(DOM_DIV_ADMIN_NOT_EXISTS);
        }
    }

    @Override
    public DomDivAdminDO getDomDivAdmin(Long id) {
        return domDivAdminMapper.selectById(id);
    }

    @Override
    public PageResult<DomDivAdminDO> getDomDivAdminPage(DomDivAdminPageReqVO pageReqVO) {
        return domDivAdminMapper.selectPage(pageReqVO);
    }

}