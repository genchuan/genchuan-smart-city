package cn.iocoder.yudao.module.datacenter.service.mnggridcode;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.mnggridcode.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mnggridcode.MngGridCodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 管理网格编码 Service 接口
 *
 * @author zcq
 */
public interface MngGridCodeService {

    /**
     * 创建管理网格编码
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMngGridCode(@Valid MngGridCodeSaveReqVO createReqVO);

    /**
     * 更新管理网格编码
     *
     * @param updateReqVO 更新信息
     */
    void updateMngGridCode(@Valid MngGridCodeSaveReqVO updateReqVO);

    /**
     * 删除管理网格编码
     *
     * @param id 编号
     */
    void deleteMngGridCode(Long id);

    /**
     * 获得管理网格编码
     *
     * @param id 编号
     * @return 管理网格编码
     */
    MngGridCodeDO getMngGridCode(Long id);

    /**
     * 获得管理网格编码分页
     *
     * @param pageReqVO 分页查询
     * @return 管理网格编码分页
     */
    PageResult<MngGridCodeDO> getMngGridCodePage(MngGridCodePageReqVO pageReqVO);

}