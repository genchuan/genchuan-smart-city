package cn.iocoder.yudao.module.datacenter.service.grid.code.mnggridcode;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.mnggridcode.vo.MngGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.mnggridcode.vo.MngGridCodeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.mnggridcode.MngGridCodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

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


    /**
     * 根据管理网格ID自动生成编码
     *
     * @param mngGridId 管理网格ID
     * @return 生成的编码ID
     */
    Long generateCodeByMngGrid(String mngGridId);

    /**
     * 校验编码唯一性
     *
     * @param mgGridCode 管理网格标识码
     * @param excludeId 排除的ID
     * @return 是否唯一
     */
    Boolean validateCodeUnique(String mgGridCode, Long excludeId);

    /**
     * 作废编码
     *
     * @param id 编码ID
     * @param reason 作废原因
     */
    void invalidateCode(Long id, String reason);

    /**
     * 根据编码查询管理网格编码信息
     *
     * @param mgGridCode 管理网格编码
     * @return 管理网格编码信息
     */
    MngGridCodeDO getByCode(String mgGridCode);

    /**
     * 根据管理网格ID查询编码信息
     *
     * @param mngGridId 管理网格ID
     * @return 管理网格编码信息
     */
    MngGridCodeDO getByMngGridId(String mngGridId);

    /**
     * 根据行政区划代码查询编码列表
     *
     * @param areaFullCode 行政区划完整代码
     * @return 编码列表
     */
    List<MngGridCodeDO> getListByArea(String areaFullCode);
}