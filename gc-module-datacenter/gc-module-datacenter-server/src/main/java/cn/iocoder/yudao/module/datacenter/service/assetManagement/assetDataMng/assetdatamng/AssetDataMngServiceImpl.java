package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetdatamng;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo.AssetDataMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo.AssetDataMngSaveReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo.AssetDataMngSimpleVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetdatamng.AssetDataMngDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetdatamng.AssetDataMngMapper;

import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资产数据管理 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AssetDataMngServiceImpl implements AssetDataMngService {

    @Resource
    private AssetDataMngMapper assetDataMngMapper;

    @Override
    public Long createAssetDataMng(AssetDataMngSaveReqVO createReqVO) {
        // 插入
        AssetDataMngDO assetDataMng = BeanUtils.toBean(createReqVO, AssetDataMngDO.class);
        assetDataMngMapper.insert(assetDataMng);
        // 返回
        return assetDataMng.getId();
    }

    @Override
    public void updateAssetDataMng(AssetDataMngSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetDataMngExists(updateReqVO.getId());
        // 更新
        AssetDataMngDO updateObj = BeanUtils.toBean(updateReqVO, AssetDataMngDO.class);
        assetDataMngMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetDataMng(Long id) {
        // 校验存在
        validateAssetDataMngExists(id);
        // 删除
        assetDataMngMapper.deleteById(id);
    }

    private void validateAssetDataMngExists(Long id) {
        if (assetDataMngMapper.selectById(id) == null) {
            throw exception(ASSET_DATA_MNG_NOT_EXISTS);
        }
    }

    @Override
    public AssetDataMngDO getAssetDataMng(Long id) {
        return assetDataMngMapper.selectById(id);
    }

    @Override
    public PageResult<AssetDataMngDO> getAssetDataMngPage(AssetDataMngPageReqVO pageReqVO) {
        return assetDataMngMapper.selectPage(pageReqVO);
    }

    /**
     * 获取启用状态的资产数据管理列表
     *
     * @return 资产数据管理列表
     */
    @Override
    public List<AssetDataMngSimpleVO> getEnabledAssetDataMngList() {
        //查询启用状态为1的资产数据管理列表
        List<AssetDataMngDO> dataList = assetDataMngMapper.selectList(
                new LambdaQueryWrapperX<AssetDataMngDO>()
                        .eq(AssetDataMngDO::getAssetStatus, 1)
                        .select(AssetDataMngDO::getAssetDataId, AssetDataMngDO::getAssetName)
                        .orderByAsc(AssetDataMngDO::getAssetDataId)
        );
        //转换为vo
        return dataList.stream().map(data -> {
            AssetDataMngSimpleVO vo = new AssetDataMngSimpleVO();
            vo.setAssetDataId(data.getAssetDataId());
            vo.setAssetName(data.getAssetName());
            return vo;
        }).collect(Collectors.toList());
    }

}