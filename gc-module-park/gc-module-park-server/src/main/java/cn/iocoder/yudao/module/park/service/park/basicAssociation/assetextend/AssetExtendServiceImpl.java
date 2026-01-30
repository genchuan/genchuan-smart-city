package cn.iocoder.yudao.module.park.service.park.basicAssociation.assetextend;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.assetextend.vo.AssetExtendPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.assetextend.vo.AssetExtendSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.assetextend.AssetExtendDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.assetextend.AssetExtendMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 资产扩展 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class AssetExtendServiceImpl implements AssetExtendService {

    @Resource
    private AssetExtendMapper assetExtendMapper;

    @Override
    public Long createAssetExtend(AssetExtendSaveReqVO createReqVO) {
        // 插入
        AssetExtendDO assetExtend = BeanUtils.toBean(createReqVO, AssetExtendDO.class);
        assetExtendMapper.insert(assetExtend);
        // 返回
        return assetExtend.getId();
    }

    @Override
    public void updateAssetExtend(AssetExtendSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetExtendExists(updateReqVO.getId());
        // 更新
        AssetExtendDO updateObj = BeanUtils.toBean(updateReqVO, AssetExtendDO.class);
        assetExtendMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetExtend(Long id) {
        // 校验存在
        validateAssetExtendExists(id);
        // 删除
        assetExtendMapper.deleteById(id);
    }

    private void validateAssetExtendExists(Long id) {
        if (assetExtendMapper.selectById(id) == null) {
            throw exception(ASSET_EXTEND_NOT_EXISTS);
        }
    }

    @Override
    public AssetExtendDO getAssetExtend(Long id) {
        return assetExtendMapper.selectById(id);
    }

    @Override
    public PageResult<AssetExtendDO> getAssetExtendPage(AssetExtendPageReqVO pageReqVO) {
        return assetExtendMapper.selectPage(pageReqVO);
    }

}