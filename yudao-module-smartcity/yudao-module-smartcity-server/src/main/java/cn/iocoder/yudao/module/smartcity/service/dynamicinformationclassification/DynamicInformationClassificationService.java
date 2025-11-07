package cn.iocoder.yudao.module.smartcity.service.dynamicinformationclassification;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationclassification.vo.DynamicInformationClassificationPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationclassification.vo.DynamicInformationClassificationSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.dynamicinformationclassification.DynamicInformationClassificationDO;
import jakarta.validation.Valid;

/**
 * 动态信息分类 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface DynamicInformationClassificationService {

    /**
     * 创建动态信息分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDynamicInformationClassification(@Valid DynamicInformationClassificationSaveReqVO createReqVO);

    /**
     * 更新动态信息分类
     *
     * @param updateReqVO 更新信息
     */
    void updateDynamicInformationClassification(@Valid DynamicInformationClassificationSaveReqVO updateReqVO);

    /**
     * 删除动态信息分类
     *
     * @param id 编号
     */
    void deleteDynamicInformationClassification(Long id);

    /**
     * 获得动态信息分类
     *
     * @param id 编号
     * @return 动态信息分类
     */
    DynamicInformationClassificationDO getDynamicInformationClassification(Long id);

    /**
     * 获得动态信息分类分页
     *
     * @param pageReqVO 分页查询
     * @return 动态信息分类分页
     */
    PageResult<DynamicInformationClassificationDO> getDynamicInformationClassificationPage(DynamicInformationClassificationPageReqVO pageReqVO);

}