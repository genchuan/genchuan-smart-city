package cn.iocoder.yudao.module.studentmgmt.service.fundsystem;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.fundsystem.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.fundsystem.FundSystemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 资助系统 Service 接口
 *
 * @author 芋道源码
 */
public interface FundSystemService {

    /**
     * 创建资助系统
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFundSystem(@Valid FundSystemSaveReqVO createReqVO);

    /**
     * 更新资助系统
     *
     * @param updateReqVO 更新信息
     */
    void updateFundSystem(@Valid FundSystemSaveReqVO updateReqVO);

    /**
     * 删除资助系统
     *
     * @param id 编号
     */
    void deleteFundSystem(Long id);

    /**
    * 批量删除资助系统
    *
    * @param ids 编号
    */
    void deleteFundSystemListByIds(List<Long> ids);

    /**
     * 获得资助系统
     *
     * @param id 编号
     * @return 资助系统
     */
    FundSystemDO getFundSystem(Long id);

    /**
     * 获得资助系统分页
     *
     * @param pageReqVO 分页查询
     * @return 资助系统分页
     */
    PageResult<FundSystemDO> getFundSystemPage(FundSystemPageReqVO pageReqVO);

}