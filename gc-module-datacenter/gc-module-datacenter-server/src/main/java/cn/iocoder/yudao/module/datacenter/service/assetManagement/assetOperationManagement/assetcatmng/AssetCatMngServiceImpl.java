package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetcatmng;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetcatmng.AssetCatMngDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetcatmng.AssetCatMngMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产分类管理 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetCatMngServiceImpl implements AssetCatMngService {

    @Resource
    private AssetCatMngMapper assetCatMngMapper;

    @Override
    public Long createAssetCatMng(AssetCatMngSaveReqVO createReqVO) {
        // 插入
        AssetCatMngDO assetCatMng = BeanUtils.toBean(createReqVO, AssetCatMngDO.class);
        assetCatMngMapper.insert(assetCatMng);
        // 返回
        return assetCatMng.getId();
    }

    @Override
    public void updateAssetCatMng(AssetCatMngSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetCatMngExists(updateReqVO.getId());
        // 更新
        AssetCatMngDO updateObj = BeanUtils.toBean(updateReqVO, AssetCatMngDO.class);
        assetCatMngMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetCatMng(Long id) {
        // 校验存在
        validateAssetCatMngExists(id);
        // 删除
        assetCatMngMapper.deleteById(id);
    }

    private void validateAssetCatMngExists(Long id) {
        if (assetCatMngMapper.selectById(id) == null) {
            throw exception(ASSET_CAT_MNG_NOT_EXISTS);
        }
    }

    @Override
    public AssetCatMngDO getAssetCatMng(Long id) {
        return assetCatMngMapper.selectById(id);
    }

    @Override
    public PageResult<AssetCatMngDO> getAssetCatMngPage(AssetCatMngPageReqVO pageReqVO) {
        return assetCatMngMapper.selectPage(pageReqVO);
    }

}