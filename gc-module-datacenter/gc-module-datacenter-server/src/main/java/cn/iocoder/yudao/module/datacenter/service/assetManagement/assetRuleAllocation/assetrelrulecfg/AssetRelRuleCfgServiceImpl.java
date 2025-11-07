package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetrelrulecfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo.AssetRelRuleCfgBatchUpdateReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo.AssetRelRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo.AssetRelRuleCfgSaveReqVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetrelrulecfg.AssetRelRuleCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetRuleAllocation.assetrelrulecfg.AssetRelRuleCfgMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产关联规则配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetRelRuleCfgServiceImpl implements AssetRelRuleCfgService {

    @Resource
    private AssetRelRuleCfgMapper assetRelRuleCfgMapper;

    @Override
    public Long createAssetRelRuleCfg(AssetRelRuleCfgSaveReqVO createReqVO) {
        // 插入
        AssetRelRuleCfgDO assetRelRuleCfg = BeanUtils.toBean(createReqVO, AssetRelRuleCfgDO.class);
        assetRelRuleCfgMapper.insert(assetRelRuleCfg);
        // 返回
        return assetRelRuleCfg.getId();
    }

    @Override
    public void updateAssetRelRuleCfg(AssetRelRuleCfgSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetRelRuleCfgExists(updateReqVO.getId());
        // 更新
        AssetRelRuleCfgDO updateObj = BeanUtils.toBean(updateReqVO, AssetRelRuleCfgDO.class);
        assetRelRuleCfgMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetRelRuleCfg(Long id) {
        // 校验存在
        validateAssetRelRuleCfgExists(id);
        // 删除
        assetRelRuleCfgMapper.deleteById(id);
    }

    private void validateAssetRelRuleCfgExists(Long id) {
        if (assetRelRuleCfgMapper.selectById(id) == null) {
            throw exception(ASSET_REL_RULE_CFG_NOT_EXISTS);
        }
    }

    @Override
    public AssetRelRuleCfgDO getAssetRelRuleCfg(Long id) {
        return assetRelRuleCfgMapper.selectById(id);
    }

    @Override
    public PageResult<AssetRelRuleCfgDO> getAssetRelRuleCfgPage(AssetRelRuleCfgPageReqVO pageReqVO) {
        return assetRelRuleCfgMapper.selectPage(pageReqVO);
    }

    /**
     * 批量更新“是否必选”
     *
     * @param reqVO
     */
    @Override
    public void batchUpdateIsRequired(AssetRelRuleCfgBatchUpdateReqVO reqVO) {
        // 1. 校验存在
        long exist = assetRelRuleCfgMapper.selectCount(
                new LambdaQueryWrapper<AssetRelRuleCfgDO>()
                        .in(AssetRelRuleCfgDO::getId, reqVO.getIds()));
                if (exist != reqVO.getIds().size()){
                    throw  exception(ASSET_REL_RULE_CFG_NOT_EXISTS);
        }
                // 2. 批量更新（mybatis-plus 内置方法）
        AssetRelRuleCfgDO update = new AssetRelRuleCfgDO();
                update.setIsRequired(String.valueOf(reqVO.getIsRequired()));
                assetRelRuleCfgMapper.update(update,
                        new LambdaQueryWrapper<AssetRelRuleCfgDO>()
                                .in(AssetRelRuleCfgDO::getId, reqVO.getIds()));
    }

}