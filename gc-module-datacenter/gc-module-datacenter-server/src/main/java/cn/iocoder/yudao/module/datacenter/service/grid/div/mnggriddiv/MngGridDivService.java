package cn.iocoder.yudao.module.datacenter.service.grid.div.mnggriddiv;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.mnggriddiv.MngGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理网格划分 Service 接口
 *
 * @author zcq
 */
public interface MngGridDivService {

    /**
     * 创建管理网格划分
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMngGridDiv(@Valid MngGridDivSaveReqVO createReqVO);

    /**
     * 更新管理网格划分
     *
     * @param updateReqVO 更新信息
     */
    void updateMngGridDiv(@Valid MngGridDivSaveReqVO updateReqVO);

    /**
     * 删除管理网格划分
     *
     * @param id 编号
     */
    void deleteMngGridDiv(Long id);

    /**
     * 获得管理网格划分
     *
     * @param id 编号
     * @return 管理网格划分
     */
    MngGridDivDO getMngGridDiv(Long id);

    /**
     * 获得管理网格划分分页
     *
     * @param pageReqVO 分页查询
     * @return 管理网格划分分页
     */
    PageResult<MngGridDivRespVO> getMngGridDivPage(MngGridDivPageReqVO pageReqVO);

    /**
     * 根据乡镇ID获取管理网格列表
     *
     * @param townStreetId 乡镇ID
     * @return 管理网格列表
     */
    List<MngGridDivRespVO> getMngGridDivListByTown(String townStreetId);

    /**
     * 校验单元网格是否可以集成
     *
     * @param unitGridIds 单元网格ID列表
     * @param townStreetId 乡镇ID
     * @return 校验结果
     */
    MngGridValidateRespVO validateUnitGrids(List<String> unitGridIds, String townStreetId);

    /**
     * 计算管理网格面积
     *
     * @param unitGridIds 单元网格ID列表
     * @return 总面积
     */
    Integer calculateArea(List<String> unitGridIds);

    /**
     * 批量导入单元网格创建管理网格
     *
     * @param importReqVO 导入请求
     * @return 创建的管理网格ID
     */
    Long importUnitGrids(@Valid MngGridImportReqVO importReqVO);

    /**
     * 根据所含单元网格数量范围筛选
     *
     * @param townStreetId 乡镇ID
     * @param minUnits 最小单元数
     * @param maxUnits 最大单元数
     * @return 管理网格列表
     */
    List<MngGridDivRespVO> getMngGridDivByUnitCount(String townStreetId, Integer minUnits, Integer maxUnits);

    /**
     * 获取单元网格详情列表
     *
     * @param unitGridIds 单元网格ID列表
     * @return 单元网格详情列表
     */
    List<UnitGridSimpleInfo> getUnitGridDetails(List<String> unitGridIds);

}