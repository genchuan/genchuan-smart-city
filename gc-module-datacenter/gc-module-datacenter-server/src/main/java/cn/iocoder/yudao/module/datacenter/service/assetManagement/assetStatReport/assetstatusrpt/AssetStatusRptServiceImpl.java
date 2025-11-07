package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetStatReport.assetstatusrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetstatusrpt.vo.AssetStatusRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetstatusrpt.vo.AssetStatusRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetStatReport.assetstatusrpt.AssetStatusRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetStatReport.assetstatusrpt.AssetStatusRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产状态统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetStatusRptServiceImpl implements AssetStatusRptService {

    @Resource
    private AssetStatusRptMapper assetStatusRptMapper;

    @Override
    public Long createAssetStatusRpt(AssetStatusRptSaveReqVO createReqVO) {
        // 插入
        AssetStatusRptDO assetStatusRpt = BeanUtils.toBean(createReqVO, AssetStatusRptDO.class);
        assetStatusRptMapper.insert(assetStatusRpt);
        // 返回
        return assetStatusRpt.getId();
    }

    @Override
    public void updateAssetStatusRpt(AssetStatusRptSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetStatusRptExists(updateReqVO.getId());
        // 更新
        AssetStatusRptDO updateObj = BeanUtils.toBean(updateReqVO, AssetStatusRptDO.class);
        assetStatusRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetStatusRpt(Long id) {
        // 校验存在
        validateAssetStatusRptExists(id);
        // 删除
        assetStatusRptMapper.deleteById(id);
    }

    private void validateAssetStatusRptExists(Long id) {
        if (assetStatusRptMapper.selectById(id) == null) {
            throw exception(ASSET_STATUS_RPT_NOT_EXISTS);
        }
    }

    @Override
    public AssetStatusRptDO getAssetStatusRpt(Long id) {
        return assetStatusRptMapper.selectById(id);
    }

    @Override
    public PageResult<AssetStatusRptDO> getAssetStatusRptPage(AssetStatusRptPageReqVO pageReqVO) {
        return assetStatusRptMapper.selectPage(pageReqVO);
    }

}