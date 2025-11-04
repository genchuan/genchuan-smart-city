package cn.iocoder.yudao.module.datacenter.service.grid.code.unitgridcode;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.unitgridcode.vo.UnitGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.unitgridcode.vo.UnitGridCodeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.unitgridcode.UnitGridCodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 单元网格编码 Service 接口
 *
 * @author zcq
 */
public interface UnitGridCodeService {

    /**
     * 创建单元网格编码
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUnitGridCode(@Valid UnitGridCodeSaveReqVO createReqVO);

    /**
     * 更新单元网格编码
     *
     * @param updateReqVO 更新信息
     */
    void updateUnitGridCode(@Valid UnitGridCodeSaveReqVO updateReqVO);

    /**
     * 删除单元网格编码
     *
     * @param id 编号
     */
    void deleteUnitGridCode(Long id);

    /**
     * 获得单元网格编码
     *
     * @param id 编号
     * @return 单元网格编码
     */
    UnitGridCodeDO getUnitGridCode(Long id);

    /**
     * 获得单元网格编码分页
     *
     * @param pageReqVO 分页查询
     * @return 单元网格编码分页
     */
    PageResult<UnitGridCodeDO> getUnitGridCodePage(UnitGridCodePageReqVO pageReqVO);


    /**
     * 根据单元网格ID自动生成编码
     *
     * @param unitGridId 单元网格ID
     * @return 生成的编码ID
     */
    Long generateCodeByUnitGrid(String unitGridId);

    /**
     * 校验编码唯一性
     *
     * @param unitGridCode 单元网格标识码
     * @param excludeId 排除的ID
     * @return 是否唯一
     */
    Boolean validateCodeUnique(String unitGridCode, Long excludeId);

    /**
     * 作废编码
     *
     * @param id 编码ID
     * @param reason 作废原因
     */
    void invalidateCode(Long id, String reason);

    /**
     * 根据编码查询单元网格编码信息
     *
     * @param unitGridCode 单元网格编码
     * @return 单元网格编码信息
     */
    UnitGridCodeDO getByCode(String unitGridCode);

    /**
     * 根据单元网格ID查询编码信息
     *
     * @param unitGridId 单元网格ID
     * @return 单元网格编码信息
     */
    UnitGridCodeDO getByUnitGridId(String unitGridId);

    /**
     * 根据行政区划代码查询编码列表
     *
     * @param areaFullCode 行政区划完整代码
     * @return 编码列表
     */
    List<UnitGridCodeDO> getListByArea(String areaFullCode);
}