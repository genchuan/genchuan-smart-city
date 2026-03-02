package cn.iocoder.yudao.module.evaluate.service.subjecttype;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.subjecttype.vo.SubjectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.subjecttype.vo.SubjectTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.subjecttype.SubjectTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 主体类型字典 Service 接口
 *
 * @author 亘川智城
 */
public interface SubjectTypeService {

    /**
     * 创建主体类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSubjectType(@Valid SubjectTypeSaveReqVO createReqVO);

    /**
     * 更新主体类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateSubjectType(@Valid SubjectTypeSaveReqVO updateReqVO);

    /**
     * 删除主体类型字典
     *
     * @param id 编号
     */
    void deleteSubjectType(Long id);

    /**
     * 获得主体类型字典
     *
     * @param id 编号
     * @return 主体类型字典
     */
    SubjectTypeDO getSubjectType(Long id);

    /**
     * 获得主体类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 主体类型字典分页
     */
    PageResult<SubjectTypeDO> getSubjectTypePage(SubjectTypePageReqVO pageReqVO);

}