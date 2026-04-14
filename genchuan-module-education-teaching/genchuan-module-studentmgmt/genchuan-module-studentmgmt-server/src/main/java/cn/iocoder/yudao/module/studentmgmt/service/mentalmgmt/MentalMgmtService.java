package cn.iocoder.yudao.module.studentmgmt.service.mentalmgmt;

import java.util.*;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.mentalmgmt.MentalMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 心理管理 Service 接口
 *
 * @author 芋道源码
 */
public interface MentalMgmtService {

    /**
     * 创建心理管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMentalMgmt(@Valid MentalMgmtSaveReqVO createReqVO);

    /**
     * 更新心理管理
     *
     * @param updateReqVO 更新信息
     */
    void updateMentalMgmt(@Valid MentalMgmtSaveReqVO updateReqVO);

    /**
     * 删除心理管理
     *
     * @param id 编号
     */
    void deleteMentalMgmt(Long id);

    /**
    * 批量删除心理管理
    *
    * @param ids 编号
    */
    void deleteMentalMgmtListByIds(List<Long> ids);

    /**
     * 获得心理管理
     *
     * @param id 编号
     * @return 心理管理
     */
    MentalMgmtDO getMentalMgmt(Long id);

    /**
     * 获得心理管理分页
     *
     * @param pageReqVO 分页查询
     * @return 心理管理分页
     */
    PageResult<MentalMgmtDO> getMentalMgmtPage(MentalMgmtPageReqVO pageReqVO);

    PageResult<MentalMgmtJoinPageRespVO> getMentalMgmtJoinPage(@Valid MentalMgmtPageReqVO pageReqVO);

    boolean consult(@Valid MentalMgmtConsultReqVO reqVO, LoginUser user);

    boolean intervene(@Valid MentalMgmtInterveneReqVO reqVO);

    boolean updateStatus(@Valid MentalMgmtUpdateStatusReqVO reqVO);
}