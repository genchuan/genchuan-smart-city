package cn.iocoder.yudao.module.smartcity.service.classificationofguideinformation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofguideinformation.vo.ClassificationOfGuideInformationPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofguideinformation.vo.ClassificationOfGuideInformationSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofguideinformation.ClassificationOfGuideInformationDO;
import jakarta.validation.Valid;

/**
 * 指南信息分类 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface ClassificationOfGuideInformationService {

    /**
     * 创建指南信息分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createClassificationOfGuideInformation(@Valid ClassificationOfGuideInformationSaveReqVO createReqVO);

    /**
     * 更新指南信息分类
     *
     * @param updateReqVO 更新信息
     */
    void updateClassificationOfGuideInformation(@Valid ClassificationOfGuideInformationSaveReqVO updateReqVO);

    /**
     * 删除指南信息分类
     *
     * @param id 编号
     */
    void deleteClassificationOfGuideInformation(Long id);

    /**
     * 获得指南信息分类
     *
     * @param id 编号
     * @return 指南信息分类
     */
    ClassificationOfGuideInformationDO getClassificationOfGuideInformation(Long id);

    /**
     * 获得指南信息分类分页
     *
     * @param pageReqVO 分页查询
     * @return 指南信息分类分页
     */
    PageResult<ClassificationOfGuideInformationDO> getClassificationOfGuideInformationPage(ClassificationOfGuideInformationPageReqVO pageReqVO);

}