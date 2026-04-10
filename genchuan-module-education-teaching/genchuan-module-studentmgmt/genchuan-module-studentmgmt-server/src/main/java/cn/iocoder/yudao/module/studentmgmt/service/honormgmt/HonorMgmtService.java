package cn.iocoder.yudao.module.studentmgmt.service.honormgmt;

import java.util.*;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.honormgmt.HonorMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 荣誉管理 Service 接口
 *
 * @author 芋道源码
 */
public interface HonorMgmtService {

    /**
     * 创建荣誉管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHonorMgmt(@Valid HonorMgmtSaveReqVO createReqVO);

    /**
     * 更新荣誉管理
     *
     * @param updateReqVO 更新信息
     */
    void updateHonorMgmt(@Valid HonorMgmtSaveReqVO updateReqVO);

    /**
     * 删除荣誉管理
     *
     * @param id 编号
     */
    void deleteHonorMgmt(Long id);

    /**
    * 批量删除荣誉管理
    *
    * @param ids 编号
    */
    void deleteHonorMgmtListByIds(List<Long> ids);

    /**
     * 获得荣誉管理
     *
     * @param id 编号
     * @return 荣誉管理
     */
    HonorMgmtDO getHonorMgmt(Long id);

    /**
     * 获得荣誉管理分页
     *
     * @param pageReqVO 分页查询
     * @return 荣誉管理分页
     */
    PageResult<HonorMgmtDO> getHonorMgmtPage(HonorMgmtPageReqVO pageReqVO);

    /**
     * 推送
     * @param reqVO
     * @return
     */
    boolean pushHonorMgmt(@Valid HonorMgmtPushReqVO reqVO);

    /**
     * 荣誉信息分布看板
     * @param reqVO
     * @return
     */
    HonorMgmtChartRespVO getChart(@Valid HonorMgmtChartReqVO reqVO);

    boolean audit(@Valid HonorMgmtAuditReqVO reqVO, LoginUser loginUser);

}