package cn.iocoder.yudao.module.datacenter.service.grid.div.unitgriddiv;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.unitgriddiv.vo.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.unitgriddiv.UnitGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 单元网格划分 Service 接口
 *
 * @author zcq
 */
public interface UnitGridDivService {

    /**
     * 创建单元网格划分
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUnitGridDiv(@Valid UnitGridDivSaveReqVO createReqVO);

    /**
     * 更新单元网格划分
     *
     * @param updateReqVO 更新信息
     */
    void updateUnitGridDiv(@Valid UnitGridDivSaveReqVO updateReqVO);

    /**
     * 删除单元网格划分
     *
     * @param id 编号
     */
    void deleteUnitGridDiv(Long id);

    /**
     * 获得单元网格划分
     *
     * @param id 编号
     * @return 单元网格划分
     */
    UnitGridDivDO getUnitGridDiv(Long id);

    /**
     * 获得单元网格划分分页
     *
     * @param pageReqVO 分页查询
     * @return 单元网格划分分页
     */
    PageResult<UnitGridDivDO> getUnitGridDivPage(UnitGridDivPageReqVO pageReqVO);

    /**
     * 校验单元网格面积
     *
     * @param area 面积
     * @param commId 社区ID
     * @return 是否通过校验
     */
    Boolean validateArea(Integer area, String commId);

    /**
     * 校验单元网格边界
     *
     * @param validateReqVO 边界校验请求
     * @return 边界校验结果
     */
    UnitGridBoundaryValidateRespVO validateBoundary(UnitGridBoundaryValidateReqVO validateReqVO);

    /**
     * 导入坐标数据创建单元网格
     *
     * @param importReqVO 坐标导入请求
     * @return 创建的单元网格ID
     */
    Long importCoordinates(UnitGridCoordinateImportReqVO importReqVO);

    /**
     * 获取指定社区下的单元网格列表
     *
     * @param commId 社区ID
     * @return 单元网格简单信息列表
     */
    java.util.List<UnitGridSimpleRespVO> getUnitGridsByComm(String commId);

    /**
     * 校验边界是否与现有网格重叠
     *
     * @param boundaryCoords 边界坐标
     * @param excludeId 排除的网格ID
     * @return 重叠的网格信息
     */
    java.util.List<UnitGridSimpleRespVO> checkBoundaryOverlap(String boundaryCoords, Long excludeId);

    /**
     * 根据单元网格ID获取单元网格信息
     *
     * @param unitGridId 单元网格ID
     * @return 单元网格信息
     */
    UnitGridDivDO getUnitGridDivByGridId(String unitGridId);

}