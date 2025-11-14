package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domassetmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domassetmonrpt.vo.DomAssetMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domassetmonrpt.vo.DomAssetMonRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domassetmonrpt.DomAssetMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.domassetmonrpt.DomAssetMonRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产分域监测报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomAssetMonRptServiceImpl implements DomAssetMonRptService {

    @Resource
    private DomAssetMonRptMapper domAssetMonRptMapper;

    @Override
    public Long createDomAssetMonRpt(DomAssetMonRptSaveReqVO createReqVO) {
        // 插入
        DomAssetMonRptDO domAssetMonRpt = BeanUtils.toBean(createReqVO, DomAssetMonRptDO.class);
        domAssetMonRptMapper.insert(domAssetMonRpt);
        // 返回
        return domAssetMonRpt.getId();
    }

    @Override
    public void updateDomAssetMonRpt(DomAssetMonRptSaveReqVO updateReqVO) {
        // 校验存在
        validateDomAssetMonRptExists(updateReqVO.getId());
        // 更新
        DomAssetMonRptDO updateObj = BeanUtils.toBean(updateReqVO, DomAssetMonRptDO.class);
        domAssetMonRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomAssetMonRpt(Long id) {
        // 校验存在
        validateDomAssetMonRptExists(id);
        // 删除
        domAssetMonRptMapper.deleteById(id);
    }

    private void validateDomAssetMonRptExists(Long id) {
        if (domAssetMonRptMapper.selectById(id) == null) {
            throw exception(DOM_ASSET_MON_RPT_NOT_EXISTS);
        }
    }

    @Override
    public DomAssetMonRptDO getDomAssetMonRpt(Long id) {
        return domAssetMonRptMapper.selectById(id);
    }

    @Override
    public PageResult<DomAssetMonRptDO> getDomAssetMonRptPage(DomAssetMonRptPageReqVO pageReqVO) {
        return domAssetMonRptMapper.selectPage(pageReqVO);
    }

}