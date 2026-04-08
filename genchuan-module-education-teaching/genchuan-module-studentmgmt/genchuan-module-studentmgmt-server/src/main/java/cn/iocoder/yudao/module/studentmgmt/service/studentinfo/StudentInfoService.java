package cn.iocoder.yudao.module.studentmgmt.service.studentinfo;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.studentinfo.StudentInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 学生信息 Service 接口
 *
 * @author 芋道源码
 */
public interface StudentInfoService {

    /**
     * 创建学生信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStudentInfo(@Valid StudentInfoSaveReqVO createReqVO);

    /**
     * 更新学生信息
     *
     * @param updateReqVO 更新信息
     */
    void updateStudentInfo(@Valid StudentInfoSaveReqVO updateReqVO);

    /**
     * 删除学生信息
     *
     * @param id 编号
     */
    void deleteStudentInfo(Long id);

    /**
    * 批量删除学生信息
    *
    * @param ids 编号
    */
    void deleteStudentInfoListByIds(List<Long> ids);

    /**
     * 获得学生信息
     *
     * @param id 编号
     * @return 学生信息
     */
    StudentInfoDO getStudentInfo(Long id);

    /**
     * 获得学生信息分页
     *
     * @param pageReqVO 分页查询
     * @return 学生信息分页
     */
    PageResult<StudentInfoDO> getStudentInfoPage(StudentInfoPageReqVO pageReqVO);

    StudentInfoDashboardVO getStudentInfoDashboard(@Valid StudentInfoChartReqVO reqVO);


    StudentInfoDistributionCountRespVO getDistributionCount(@Valid StudentInfoDistributionCountReqVO reqVO);

    StudentInfoCoreIndexRespVO getCoreIndex(@Valid StudentInfoCoreIndexReqVO reqVO);
}