package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetmngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetmngcomp.vo.AssetMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetmngcomp.vo.AssetMngCompSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetmngcomp.AssetMngCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetmngcomp.AssetMngCompMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 资产关联管理部件 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetMngCompServiceImpl implements AssetMngCompService {

    @Resource
    private AssetMngCompMapper assetMngCompMapper;

    @Override
    public Long createAssetMngComp(AssetMngCompSaveReqVO createReqVO) {
        // 插入
        AssetMngCompDO assetMngComp = BeanUtils.toBean(createReqVO, AssetMngCompDO.class);
        assetMngCompMapper.insert(assetMngComp);
        // 返回
        return assetMngComp.getId();
    }

    @Override
    public void updateAssetMngComp(AssetMngCompSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetMngCompExists(updateReqVO.getId());
        // 更新
        AssetMngCompDO updateObj = BeanUtils.toBean(updateReqVO, AssetMngCompDO.class);
        assetMngCompMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetMngComp(Long id) {
        // 校验存在
        validateAssetMngCompExists(id);
        // 删除
        assetMngCompMapper.deleteById(id);
    }

    private void validateAssetMngCompExists(Long id) {
        if (assetMngCompMapper.selectById(id) == null) {
            throw exception(ASSET_MNG_COMP_NOT_EXISTS);
        }
    }

    @Override
    public AssetMngCompDO getAssetMngComp(Long id) {
        return assetMngCompMapper.selectById(id);
    }

    @Override
    public PageResult<AssetMngCompDO> getAssetMngCompPage(AssetMngCompPageReqVO pageReqVO) {
        return assetMngCompMapper.selectPage(pageReqVO);
    }

    /**
     * 批量删除资产关联管理部件
     *
     * @param ids 编号列表
     */
    @Override
    public void deleteAssetMngCompBatch(List<Long> ids) {
        for (Long id : ids) {
            // 校验存在
            validateAssetMngCompExists(id);
        }
        assetMngCompMapper.deleteByIds(ids);
    }

}