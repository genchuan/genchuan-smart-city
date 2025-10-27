package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetStatReport.assetcatrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetcatrpt.vo.AssetCatRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetcatrpt.vo.AssetCatRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetStatReport.assetcatrpt.AssetCatRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetStatReport.assetcatrpt.AssetCatRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产分类统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetCatRptServiceImpl implements AssetCatRptService {

    @Resource
    private AssetCatRptMapper assetCatRptMapper;

    @Override
    public Long createAssetCatRpt(AssetCatRptSaveReqVO createReqVO) {
        // 插入
        AssetCatRptDO assetCatRpt = BeanUtils.toBean(createReqVO, AssetCatRptDO.class);
        assetCatRptMapper.insert(assetCatRpt);
        // 返回
        return assetCatRpt.getId();
    }

    @Override
    public void updateAssetCatRpt(AssetCatRptSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetCatRptExists(updateReqVO.getId());
        // 更新
        AssetCatRptDO updateObj = BeanUtils.toBean(updateReqVO, AssetCatRptDO.class);
        assetCatRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetCatRpt(Long id) {
        // 校验存在
        validateAssetCatRptExists(id);
        // 删除
        assetCatRptMapper.deleteById(id);
    }

    private void validateAssetCatRptExists(Long id) {
        if (assetCatRptMapper.selectById(id) == null) {
            throw exception(ASSET_CAT_RPT_NOT_EXISTS);
        }
    }

    @Override
    public AssetCatRptDO getAssetCatRpt(Long id) {
        return assetCatRptMapper.selectById(id);
    }

    @Override
    public PageResult<AssetCatRptDO> getAssetCatRptPage(AssetCatRptPageReqVO pageReqVO) {
        return assetCatRptMapper.selectPage(pageReqVO);
    }

}