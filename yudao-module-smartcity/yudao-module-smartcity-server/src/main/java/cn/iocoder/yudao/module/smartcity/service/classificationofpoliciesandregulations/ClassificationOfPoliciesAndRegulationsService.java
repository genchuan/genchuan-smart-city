package cn.iocoder.yudao.module.smartcity.service.classificationofpoliciesandregulations;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofpoliciesandregulations.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofpoliciesandregulations.ClassificationOfPoliciesAndRegulationsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import jakarta.validation.Valid;

/**
 * 政策法规分类 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface ClassificationOfPoliciesAndRegulationsService {

    /**
     * 创建政策法规分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createClassificationOfPoliciesAndRegulations(@Valid ClassificationOfPoliciesAndRegulationsSaveReqVO createReqVO);

    /**
     * 更新政策法规分类
     *
     * @param updateReqVO 更新信息
     */
    void updateClassificationOfPoliciesAndRegulations(@Valid ClassificationOfPoliciesAndRegulationsSaveReqVO updateReqVO);

    /**
     * 删除政策法规分类
     *
     * @param id 编号
     */
    void deleteClassificationOfPoliciesAndRegulations(Long id);

    /**
     * 获得政策法规分类
     *
     * @param id 编号
     * @return 政策法规分类
     */
    ClassificationOfPoliciesAndRegulationsDO getClassificationOfPoliciesAndRegulations(Long id);

    /**
     * 获得政策法规分类分页
     *
     * @param pageReqVO 分页查询
     * @return 政策法规分类分页
     */
    PageResult<ClassificationOfPoliciesAndRegulationsDO> getClassificationOfPoliciesAndRegulationsPage(ClassificationOfPoliciesAndRegulationsPageReqVO pageReqVO);

}