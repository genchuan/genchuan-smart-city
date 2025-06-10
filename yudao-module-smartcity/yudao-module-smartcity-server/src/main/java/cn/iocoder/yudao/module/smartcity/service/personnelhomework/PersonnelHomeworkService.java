package cn.iocoder.yudao.module.smartcity.service.personnelhomework;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.personnelhomework.vo.PersonnelHomeworkPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.personnelhomework.vo.PersonnelHomeworkSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.personnelhomework.PersonnelHomeworkDO;
import jakarta.validation.Valid;

/**
 * 人员作业 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface PersonnelHomeworkService {

    /**
     * 创建人员作业
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPersonnelHomework(@Valid PersonnelHomeworkSaveReqVO createReqVO);

    /**
     * 更新人员作业
     *
     * @param updateReqVO 更新信息
     */
    void updatePersonnelHomework(@Valid PersonnelHomeworkSaveReqVO updateReqVO);

    /**
     * 删除人员作业
     *
     * @param id 编号
     */
    void deletePersonnelHomework(Long id);

    /**
     * 获得人员作业
     *
     * @param id 编号
     * @return 人员作业
     */
    PersonnelHomeworkDO getPersonnelHomework(Long id);

    /**
     * 获得人员作业分页
     *
     * @param pageReqVO 分页查询
     * @return 人员作业分页
     */
    PageResult<PersonnelHomeworkDO> getPersonnelHomeworkPage(PersonnelHomeworkPageReqVO pageReqVO);

}