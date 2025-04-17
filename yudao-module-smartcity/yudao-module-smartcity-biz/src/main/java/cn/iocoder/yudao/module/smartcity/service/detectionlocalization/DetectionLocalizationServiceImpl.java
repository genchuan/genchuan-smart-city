package cn.iocoder.yudao.module.smartcity.service.detectionlocalization;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.detectionlocalization.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.detectionlocalization.DetectionLocalizationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.detectionlocalization.DetectionLocalizationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 故障检测与定位 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class DetectionLocalizationServiceImpl implements DetectionLocalizationService {

    @Resource
    private DetectionLocalizationMapper detectionLocalizationMapper;

    @Override
    public Long createDetectionLocalization(DetectionLocalizationSaveReqVO createReqVO) {
        // 插入
        DetectionLocalizationDO detectionLocalization = BeanUtils.toBean(createReqVO, DetectionLocalizationDO.class);
        detectionLocalizationMapper.insert(detectionLocalization);
        // 返回
        return detectionLocalization.getId();
    }

    @Override
    public void updateDetectionLocalization(DetectionLocalizationSaveReqVO updateReqVO) {
        // 校验存在
        validateDetectionLocalizationExists(updateReqVO.getId());
        // 更新
        DetectionLocalizationDO updateObj = BeanUtils.toBean(updateReqVO, DetectionLocalizationDO.class);
        detectionLocalizationMapper.updateById(updateObj);
    }

    @Override
    public void deleteDetectionLocalization(Long id) {
        // 校验存在
        validateDetectionLocalizationExists(id);
        // 删除
        detectionLocalizationMapper.deleteById(id);
    }

    private void validateDetectionLocalizationExists(Long id) {
        if (detectionLocalizationMapper.selectById(id) == null) {
            throw exception(DETECTION_LOCALIZATION_NOT_EXISTS);
        }
    }

    @Override
    public DetectionLocalizationDO getDetectionLocalization(Long id) {
        return detectionLocalizationMapper.selectById(id);
    }

    @Override
    public PageResult<DetectionLocalizationDO> getDetectionLocalizationPage(DetectionLocalizationPageReqVO pageReqVO) {
        return detectionLocalizationMapper.selectPage(pageReqVO);
    }

}