package cn.iocoder.yudao.module.facility.service.syswarn;

import java.util.*;

import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.syswarn.SysWarnDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 通用预警 Service 接口
 *
 * @author 亘川智城
 */
public interface SysWarnService {

    /**
     * 创建通用预警
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSysWarn(@Valid SysWarnSaveReqVO createReqVO);

    /**
     * 更新通用预警
     *
     * @param updateReqVO 更新信息
     */
    void updateSysWarn(@Valid SysWarnSaveReqVO updateReqVO);

    /**
     * 删除通用预警
     *
     * @param id 编号
     */
    void deleteSysWarn(Long id);

    /**
     * 获得通用预警
     *
     * @param id 编号
     * @return 通用预警
     */
    SysWarnDO getSysWarn(Long id);

    /**
     * 获得通用预警分页
     *
     * @param pageReqVO 分页查询
     * @return 通用预警分页
     */
    PageResult<SysWarnRespVO> getSysWarnPage(SysWarnPageReqVO pageReqVO);

}
