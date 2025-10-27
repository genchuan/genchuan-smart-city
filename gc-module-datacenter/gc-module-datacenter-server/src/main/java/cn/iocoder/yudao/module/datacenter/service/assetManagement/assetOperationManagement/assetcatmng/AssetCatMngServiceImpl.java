package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetcatmng;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngImportExcelVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngImportRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngListReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetcatmng.AssetCatMngDO;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
import cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetcatmng.AssetCatMngMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

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
        // 校验父级编号的有效性
        validateParentAssetCatMng(null, createReqVO.getParentId());
        // 校验名字的唯一性
        validateAssetCatMngNameUnique(null, createReqVO.getParentId(), createReqVO.getName());

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
        // 校验父级编号的有效性
        validateParentAssetCatMng(updateReqVO.getId(), updateReqVO.getParentId());
        // 校验名字的唯一性
        validateAssetCatMngNameUnique(updateReqVO.getId(), updateReqVO.getParentId(), updateReqVO.getName());

        // 更新
        AssetCatMngDO updateObj = BeanUtils.toBean(updateReqVO, AssetCatMngDO.class);
        assetCatMngMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetCatMng(Long id) {
        // 校验存在
        validateAssetCatMngExists(id);
        // 校验是否有子资产分类管理
        if (assetCatMngMapper.selectCountByParentId(id) > 0) {
            throw exception(ASSET_CAT_MNG_EXITS_CHILDREN);
        }
        // 删除
        assetCatMngMapper.deleteById(id);
    }

    private void validateAssetCatMngExists(Long id) {
        if (assetCatMngMapper.selectById(id) == null) {
            throw exception(ASSET_CAT_MNG_NOT_EXISTS);
        }
    }

    private void validateParentAssetCatMng(Long id, Long parentId) {
        if (parentId == null || AssetCatMngDO.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父资产分类管理
        if (Objects.equals(id, parentId)) {
            throw exception(ASSET_CAT_MNG_PARENT_ERROR);
        }
        // 2. 父资产分类管理不存在
        AssetCatMngDO parentAssetCatMng = assetCatMngMapper.selectById(parentId);
        if (parentAssetCatMng == null) {
            throw exception(ASSET_CAT_MNG_PARENT_NOT_EXITS);
        }
        // 3. 递归校验父资产分类管理，如果父资产分类管理是自己的子资产分类管理，则报错，避免形成环路
        if (id == null) { // id 为空，说明新增，不需要考虑环路
            return;
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            parentId = parentAssetCatMng.getParentId();
            if (Objects.equals(id, parentId)) {
                throw exception(ASSET_CAT_MNG_PARENT_IS_CHILD);
            }
            // 3.2 继续递归下一级父资产分类管理
            if (parentId == null || AssetCatMngDO.PARENT_ID_ROOT.equals(parentId)) {
                break;
            }
            parentAssetCatMng = assetCatMngMapper.selectById(parentId);
            if (parentAssetCatMng == null) {
                break;
            }
        }
    }

    private void validateAssetCatMngNameUnique(Long id, Long parentId, String name) {
        AssetCatMngDO assetCatMng = assetCatMngMapper.selectByParentIdAndName(parentId, name);
        if (assetCatMng == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的资产分类管理
        if (id == null) {
            throw exception(ASSET_CAT_MNG_NAME_DUPLICATE);
        }
        if (!Objects.equals(assetCatMng.getId(), id)) {
            throw exception(ASSET_CAT_MNG_NAME_DUPLICATE);
        }
    }

    @Override
    public AssetCatMngDO getAssetCatMng(Long id) {
        return assetCatMngMapper.selectById(id);
    }

    @Override
    public List<AssetCatMngDO> getAssetCatMngList(AssetCatMngListReqVO listReqVO) {
        return assetCatMngMapper.selectList(listReqVO);
    }

    /**
     * 导入资产分类管理
     * @param importAssetCatMng 导入信息列表
     * @param isUpdateSupport 是否支持更新已有数据
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AssetCatMngImportRespVO importAssetCatMngList(List<AssetCatMngImportExcelVO> importAssetCatMng, boolean isUpdateSupport) {
        AssetCatMngImportRespVO respVO = AssetCatMngImportRespVO.builder().createAssetCatCodes(new ArrayList<>())
                .updateAssetCatCodes(new ArrayList<>()).failureAssetCatCodes(new LinkedHashMap<>()).build();
        return respVO;
    }

}