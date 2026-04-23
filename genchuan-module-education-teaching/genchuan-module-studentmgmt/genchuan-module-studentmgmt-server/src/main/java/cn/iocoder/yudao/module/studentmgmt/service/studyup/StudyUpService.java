package cn.iocoder.yudao.module.studentmgmt.service.studyup;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studyup.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studyup.StudyUpDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 升学管理 Service 接口
 *
 * @author 芋道源码
 */
public interface StudyUpService {

    /**
     * 创建升学管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStudyUp(@Valid StudyUpSaveReqVO createReqVO);

    /**
     * 更新升学管理
     *
     * @param updateReqVO 更新信息
     */
    void updateStudyUp(@Valid StudyUpSaveReqVO updateReqVO);

    /**
     * 删除升学管理
     *
     * @param id 编号
     */
    void deleteStudyUp(Long id);

    /**
    * 批量删除升学管理
    *
    * @param ids 编号
    */
    void deleteStudyUpListByIds(List<Long> ids);

    /**
     * 获得升学管理
     *
     * @param id 编号
     * @return 升学管理
     */
    StudyUpDO getStudyUp(Long id);

    /**
     * 获得升学管理分页
     *
     * @param pageReqVO 分页查询
     * @return 升学管理分页
     */
    PageResult<StudyUpDO> getStudyUpPage(StudyUpPageReqVO pageReqVO);

}