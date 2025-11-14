package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domappscenemonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domappscenemonrpt.vo.DomAppSceneMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domappscenemonrpt.vo.DomAppSceneMonRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domappscenemonrpt.DomAppSceneMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.domappscenemonrpt.DomAppSceneMonRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 应用场景分域监测报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomAppSceneMonRptServiceImpl implements DomAppSceneMonRptService {

    @Resource
    private DomAppSceneMonRptMapper domAppSceneMonRptMapper;

    @Override
    public Long createDomAppSceneMonRpt(DomAppSceneMonRptSaveReqVO createReqVO) {
        // 插入
        DomAppSceneMonRptDO domAppSceneMonRpt = BeanUtils.toBean(createReqVO, DomAppSceneMonRptDO.class);
        domAppSceneMonRptMapper.insert(domAppSceneMonRpt);
        // 返回
        return domAppSceneMonRpt.getId();
    }

    @Override
    public void updateDomAppSceneMonRpt(DomAppSceneMonRptSaveReqVO updateReqVO) {
        // 校验存在
        validateDomAppSceneMonRptExists(updateReqVO.getId());
        // 更新
        DomAppSceneMonRptDO updateObj = BeanUtils.toBean(updateReqVO, DomAppSceneMonRptDO.class);
        domAppSceneMonRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomAppSceneMonRpt(Long id) {
        // 校验存在
        validateDomAppSceneMonRptExists(id);
        // 删除
        domAppSceneMonRptMapper.deleteById(id);
    }

    private void validateDomAppSceneMonRptExists(Long id) {
        if (domAppSceneMonRptMapper.selectById(id) == null) {
            throw exception(DOM_APP_SCENE_MON_RPT_NOT_EXISTS);
        }
    }

    @Override
    public DomAppSceneMonRptDO getDomAppSceneMonRpt(Long id) {
        return domAppSceneMonRptMapper.selectById(id);
    }

    @Override
    public PageResult<DomAppSceneMonRptDO> getDomAppSceneMonRptPage(DomAppSceneMonRptPageReqVO pageReqVO) {
        return domAppSceneMonRptMapper.selectPage(pageReqVO);
    }

}