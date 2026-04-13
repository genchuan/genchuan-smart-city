package cn.iocoder.yudao.module.studentmgmt.service.communicatemgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.communicatemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.communicatemgmt.CommunicateMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 沟通管理 Service 接口
 *
 * @author 芋道源码
 */
public interface CommunicateMgmtService {

    /**
     * 创建沟通管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCommunicateMgmt(@Valid CommunicateMgmtSaveReqVO createReqVO);

    /**
     * 更新沟通管理
     *
     * @param updateReqVO 更新信息
     */
    void updateCommunicateMgmt(@Valid CommunicateMgmtSaveReqVO updateReqVO);

    /**
     * 删除沟通管理
     *
     * @param id 编号
     */
    void deleteCommunicateMgmt(Long id);

    /**
    * 批量删除沟通管理
    *
    * @param ids 编号
    */
    void deleteCommunicateMgmtListByIds(List<Long> ids);

    /**
     * 获得沟通管理
     *
     * @param id 编号
     * @return 沟通管理
     */
    CommunicateMgmtDO getCommunicateMgmt(Long id);

    /**
     * 获得沟通管理分页
     *
     * @param pageReqVO 分页查询
     * @return 沟通管理分页
     */
    PageResult<CommunicateMgmtDO> getCommunicateMgmtPage(CommunicateMgmtPageReqVO pageReqVO);

}