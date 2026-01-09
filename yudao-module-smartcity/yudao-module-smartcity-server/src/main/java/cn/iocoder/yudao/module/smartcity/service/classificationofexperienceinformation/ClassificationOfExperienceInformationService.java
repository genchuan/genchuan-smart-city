package cn.iocoder.yudao.module.smartcity.service.classificationofexperienceinformation;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofexperienceinformation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofexperienceinformation.ClassificationOfExperienceInformationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import jakarta.validation.Valid;

/**
 * 经验信息分类 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface ClassificationOfExperienceInformationService {

    /**
     * 创建经验信息分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createClassificationOfExperienceInformation(@Valid ClassificationOfExperienceInformationSaveReqVO createReqVO);

    /**
     * 更新经验信息分类
     *
     * @param updateReqVO 更新信息
     */
    void updateClassificationOfExperienceInformation(@Valid ClassificationOfExperienceInformationSaveReqVO updateReqVO);

    /**
     * 删除经验信息分类
     *
     * @param id 编号
     */
    void deleteClassificationOfExperienceInformation(Long id);

    /**
     * 获得经验信息分类
     *
     * @param id 编号
     * @return 经验信息分类
     */
    ClassificationOfExperienceInformationDO getClassificationOfExperienceInformation(Long id);

    /**
     * 获得经验信息分类分页
     *
     * @param pageReqVO 分页查询
     * @return 经验信息分类分页
     */
    PageResult<ClassificationOfExperienceInformationDO> getClassificationOfExperienceInformationPage(ClassificationOfExperienceInformationPageReqVO pageReqVO);

}