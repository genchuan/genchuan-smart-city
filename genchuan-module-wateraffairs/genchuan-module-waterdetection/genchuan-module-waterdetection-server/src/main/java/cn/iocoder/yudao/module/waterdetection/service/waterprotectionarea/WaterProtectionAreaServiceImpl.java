package cn.iocoder.yudao.module.waterdetection.service.waterprotectionarea;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.waterprotectionarea.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterprotectionarea.WaterProtectionAreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.waterprotectionarea.WaterProtectionAreaMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 水源保护区管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class WaterProtectionAreaServiceImpl implements WaterProtectionAreaService {

    @Resource
    private WaterProtectionAreaMapper waterProtectionAreaMapper;

    @Override
    public Long createWaterProtectionArea(WaterProtectionAreaSaveReqVO createReqVO) {
        // 插入
        WaterProtectionAreaDO waterProtectionArea = BeanUtils.toBean(createReqVO, WaterProtectionAreaDO.class);
        waterProtectionAreaMapper.insert(waterProtectionArea);
        // 返回
        return waterProtectionArea.getId();
    }

    @Override
    public void updateWaterProtectionArea(WaterProtectionAreaSaveReqVO updateReqVO) {
        // 校验存在
        validateWaterProtectionAreaExists(updateReqVO.getId());
        // 更新
        WaterProtectionAreaDO updateObj = BeanUtils.toBean(updateReqVO, WaterProtectionAreaDO.class);
        waterProtectionAreaMapper.updateById(updateObj);
    }

    @Override
    public void deleteWaterProtectionArea(Long id) {
        // 校验存在
        validateWaterProtectionAreaExists(id);
        // 删除
        waterProtectionAreaMapper.deleteById(id);
    }

    private void validateWaterProtectionAreaExists(Long id) {
        if (waterProtectionAreaMapper.selectById(id) == null) {
            throw exception(WATER_PROTECTION_AREA_NOT_EXISTS);
        }
    }

    @Override
    public WaterProtectionAreaDO getWaterProtectionArea(Long id) {
        return waterProtectionAreaMapper.selectById(id);
    }

    @Override
    public PageResult<WaterProtectionAreaDO> getWaterProtectionAreaPage(WaterProtectionAreaPageReqVO pageReqVO) {
        return waterProtectionAreaMapper.selectPage(pageReqVO);
    }

}