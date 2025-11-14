package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domgridmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domgridmonrpt.vo.DomGridMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domgridmonrpt.vo.DomGridMonRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domgridmonrpt.DomGridMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.domgridmonrpt.DomGridMonRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 网格分域监测报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomGridMonRptServiceImpl implements DomGridMonRptService {

    @Resource
    private DomGridMonRptMapper domGridMonRptMapper;

    @Override
    public Long createDomGridMonRpt(DomGridMonRptSaveReqVO createReqVO) {
        // 插入
        DomGridMonRptDO domGridMonRpt = BeanUtils.toBean(createReqVO, DomGridMonRptDO.class);
        domGridMonRptMapper.insert(domGridMonRpt);
        // 返回
        return domGridMonRpt.getId();
    }

    @Override
    public void updateDomGridMonRpt(DomGridMonRptSaveReqVO updateReqVO) {
        // 校验存在
        validateDomGridMonRptExists(updateReqVO.getId());
        // 更新
        DomGridMonRptDO updateObj = BeanUtils.toBean(updateReqVO, DomGridMonRptDO.class);
        domGridMonRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomGridMonRpt(Long id) {
        // 校验存在
        validateDomGridMonRptExists(id);
        // 删除
        domGridMonRptMapper.deleteById(id);
    }

    private void validateDomGridMonRptExists(Long id) {
        if (domGridMonRptMapper.selectById(id) == null) {
            throw exception(DOM_GRID_MON_RPT_NOT_EXISTS);
        }
    }

    @Override
    public DomGridMonRptDO getDomGridMonRpt(Long id) {
        return domGridMonRptMapper.selectById(id);
    }

    @Override
    public PageResult<DomGridMonRptDO> getDomGridMonRptPage(DomGridMonRptPageReqVO pageReqVO) {
        return domGridMonRptMapper.selectPage(pageReqVO);
    }

}