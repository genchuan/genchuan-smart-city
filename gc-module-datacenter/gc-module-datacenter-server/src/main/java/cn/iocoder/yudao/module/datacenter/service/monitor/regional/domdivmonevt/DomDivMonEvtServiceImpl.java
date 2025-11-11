package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivmonevt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmonevt.vo.DomDivMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmonevt.vo.DomDivMonEvtSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmonevt.DomDivMonEvtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivmonevt.DomDivMonEvtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测事件分域 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomDivMonEvtServiceImpl implements DomDivMonEvtService {

    @Resource
    private DomDivMonEvtMapper domDivMonEvtMapper;

    @Override
    public Long createDomDivMonEvt(DomDivMonEvtSaveReqVO createReqVO) {
        // 插入
        DomDivMonEvtDO domDivMonEvt = BeanUtils.toBean(createReqVO, DomDivMonEvtDO.class);
        domDivMonEvtMapper.insert(domDivMonEvt);
        // 返回
        return domDivMonEvt.getId();
    }

    @Override
    public void updateDomDivMonEvt(DomDivMonEvtSaveReqVO updateReqVO) {
        // 校验存在
        validateDomDivMonEvtExists(updateReqVO.getId());
        // 更新
        DomDivMonEvtDO updateObj = BeanUtils.toBean(updateReqVO, DomDivMonEvtDO.class);
        domDivMonEvtMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomDivMonEvt(Long id) {
        // 校验存在
        validateDomDivMonEvtExists(id);
        // 删除
        domDivMonEvtMapper.deleteById(id);
    }

    private void validateDomDivMonEvtExists(Long id) {
        if (domDivMonEvtMapper.selectById(id) == null) {
            throw exception(DOM_DIV_MON_EVT_NOT_EXISTS);
        }
    }

    @Override
    public DomDivMonEvtDO getDomDivMonEvt(Long id) {
        return domDivMonEvtMapper.selectById(id);
    }

    @Override
    public PageResult<DomDivMonEvtDO> getDomDivMonEvtPage(DomDivMonEvtPageReqVO pageReqVO) {
        return domDivMonEvtMapper.selectPage(pageReqVO);
    }

}