package cn.iocoder.yudao.module.studentmgmt.service.dormcheck;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcheck.DormCheckDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 宿舍考勤 Service 接口
 *
 * @author 芋道源码
 */
public interface DormCheckService {

    /**
     * 创建宿舍考勤
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDormCheck(@Valid DormCheckSaveReqVO createReqVO);

    /**
     * 更新宿舍考勤
     *
     * @param updateReqVO 更新信息
     */
    void updateDormCheck(@Valid DormCheckSaveReqVO updateReqVO);

    /**
     * 删除宿舍考勤
     *
     * @param id 编号
     */
    void deleteDormCheck(Long id);

    /**
    * 批量删除宿舍考勤
    *
    * @param ids 编号
    */
    void deleteDormCheckListByIds(List<Long> ids);

    /**
     * 获得宿舍考勤
     *
     * @param id 编号
     * @return 宿舍考勤
     */
    DormCheckDO getDormCheck(Long id);

    /**
     * 获得宿舍考勤分页
     *
     * @param pageReqVO 分页查询
     * @return 宿舍考勤分页
     */
    PageResult<DormCheckDO> getDormCheckPage(DormCheckPageReqVO pageReqVO);

}