package cn.iocoder.yudao.module.smartcity.service.detectionlocalization;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.detectionlocalization.vo.DetectionLocalizationPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.detectionlocalization.vo.DetectionLocalizationSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.detectionlocalization.DetectionLocalizationDO;
import jakarta.validation.Valid;

/**
 * 故障检测与定位 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface DetectionLocalizationService {

    /**
     * 创建故障检测与定位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDetectionLocalization(@Valid DetectionLocalizationSaveReqVO createReqVO);

    /**
     * 更新故障检测与定位
     *
     * @param updateReqVO 更新信息
     */
    void updateDetectionLocalization(@Valid DetectionLocalizationSaveReqVO updateReqVO);

    /**
     * 删除故障检测与定位
     *
     * @param id 编号
     */
    void deleteDetectionLocalization(Long id);

    /**
     * 获得故障检测与定位
     *
     * @param id 编号
     * @return 故障检测与定位
     */
    DetectionLocalizationDO getDetectionLocalization(Long id);

    /**
     * 获得故障检测与定位分页
     *
     * @param pageReqVO 分页查询
     * @return 故障检测与定位分页
     */
    PageResult<DetectionLocalizationDO> getDetectionLocalizationPage(DetectionLocalizationPageReqVO pageReqVO);

}