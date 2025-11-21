package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnasset.vo.EarlyWarnAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnasset.vo.EarlyWarnAssetSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnasset.EarlyWarnAssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarnasset.EarlyWarnAssetMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 按资产分域预警告警统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnAssetServiceImpl implements EarlyWarnAssetService {

    @Resource
    private EarlyWarnAssetMapper earlyWarnAssetMapper;

    @Override
    public Long createEarlyWarnAsset(EarlyWarnAssetSaveReqVO createReqVO) {
        // 插入
        EarlyWarnAssetDO earlyWarnAsset = BeanUtils.toBean(createReqVO, EarlyWarnAssetDO.class);
        earlyWarnAssetMapper.insert(earlyWarnAsset);
        // 返回
        return earlyWarnAsset.getId();
    }

    @Override
    public void updateEarlyWarnAsset(EarlyWarnAssetSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnAssetExists(updateReqVO.getId());
        // 更新
        EarlyWarnAssetDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnAssetDO.class);
        earlyWarnAssetMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnAsset(Long id) {
        // 校验存在
        validateEarlyWarnAssetExists(id);
        // 删除
        earlyWarnAssetMapper.deleteById(id);
    }

    private void validateEarlyWarnAssetExists(Long id) {
        if (earlyWarnAssetMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_ASSET_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnAssetDO getEarlyWarnAsset(Long id) {
        return earlyWarnAssetMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnAssetDO> getEarlyWarnAssetPage(EarlyWarnAssetPageReqVO pageReqVO) {
        return earlyWarnAssetMapper.selectPage(pageReqVO);
    }

}