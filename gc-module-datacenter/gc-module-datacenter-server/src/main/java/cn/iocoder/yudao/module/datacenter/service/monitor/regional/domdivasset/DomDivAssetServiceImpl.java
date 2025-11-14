package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivasset.vo.DomDivAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivasset.vo.DomDivAssetSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivasset.DomDivAssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivasset.DomDivAssetMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产分域 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomDivAssetServiceImpl implements DomDivAssetService {

    @Resource
    private DomDivAssetMapper domDivAssetMapper;

    @Override
    public Long createDomDivAsset(DomDivAssetSaveReqVO createReqVO) {
        // 插入
        DomDivAssetDO domDivAsset = BeanUtils.toBean(createReqVO, DomDivAssetDO.class);
        domDivAssetMapper.insert(domDivAsset);
        // 返回
        return domDivAsset.getId();
    }

    @Override
    public void updateDomDivAsset(DomDivAssetSaveReqVO updateReqVO) {
        // 校验存在
        validateDomDivAssetExists(updateReqVO.getId());
        // 更新
        DomDivAssetDO updateObj = BeanUtils.toBean(updateReqVO, DomDivAssetDO.class);
        domDivAssetMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomDivAsset(Long id) {
        // 校验存在
        validateDomDivAssetExists(id);
        // 删除
        domDivAssetMapper.deleteById(id);
    }

    private void validateDomDivAssetExists(Long id) {
        if (domDivAssetMapper.selectById(id) == null) {
            throw exception(DOM_DIV_ASSET_NOT_EXISTS);
        }
    }

    @Override
    public DomDivAssetDO getDomDivAsset(Long id) {
        return domDivAssetMapper.selectById(id);
    }

    @Override
    public PageResult<DomDivAssetDO> getDomDivAssetPage(DomDivAssetPageReqVO pageReqVO) {
        return domDivAssetMapper.selectPage(pageReqVO);
    }

}