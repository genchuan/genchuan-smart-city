package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetdataqualck;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdataqualck.vo.AssetDataQualCkPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdataqualck.vo.AssetDataQualCkSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetdataqualck.AssetDataQualCkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetdataqualck.AssetDataQualCkMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产数据质量检查 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetDataQualCkServiceImpl implements AssetDataQualCkService {

    @Resource
    private AssetDataQualCkMapper assetDataQualCkMapper;

    @Override
    public Long createAssetDataQualCk(AssetDataQualCkSaveReqVO createReqVO) {
        // 插入
        AssetDataQualCkDO assetDataQualCk = BeanUtils.toBean(createReqVO, AssetDataQualCkDO.class);
        assetDataQualCkMapper.insert(assetDataQualCk);
        // 返回
        return assetDataQualCk.getId();
    }

    @Override
    public void updateAssetDataQualCk(AssetDataQualCkSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetDataQualCkExists(updateReqVO.getId());
        // 更新
        AssetDataQualCkDO updateObj = BeanUtils.toBean(updateReqVO, AssetDataQualCkDO.class);
        assetDataQualCkMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetDataQualCk(Long id) {
        // 校验存在
        validateAssetDataQualCkExists(id);
        // 删除
        assetDataQualCkMapper.deleteById(id);
    }

    private void validateAssetDataQualCkExists(Long id) {
        if (assetDataQualCkMapper.selectById(id) == null) {
            throw exception(ASSET_DATA_QUAL_CK_NOT_EXISTS);
        }
    }

    @Override
    public AssetDataQualCkDO getAssetDataQualCk(Long id) {
        return assetDataQualCkMapper.selectById(id);
    }

    @Override
    public PageResult<AssetDataQualCkDO> getAssetDataQualCkPage(AssetDataQualCkPageReqVO pageReqVO) {
        return assetDataQualCkMapper.selectPage(pageReqVO);
    }

}