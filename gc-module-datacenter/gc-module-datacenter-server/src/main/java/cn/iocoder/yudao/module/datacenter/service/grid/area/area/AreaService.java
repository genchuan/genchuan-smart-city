package cn.iocoder.yudao.module.datacenter.service.grid.area.area;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo.AreaDropdownRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo.AreaSaveReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo.AreaTreeRespVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.area.AreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 统一行政区划配置表（树形结构） Service 接口
 *
 * @author zcq
 */
public interface AreaService {

    /**
     * 创建统一行政区划配置表（树形结构）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createArea(@Valid AreaSaveReqVO createReqVO);

    /**
     * 更新统一行政区划配置表（树形结构）
     *
     * @param updateReqVO 更新信息
     */
    void updateArea(@Valid AreaSaveReqVO updateReqVO);

    /**
     * 删除统一行政区划配置表（树形结构）
     *
     * @param id 编号
     */
    void deleteArea(Long id);

    /**
     * 获得统一行政区划配置表（树形结构）
     *
     * @param id 编号
     * @return 统一行政区划配置表（树形结构）
     */
    AreaDO getArea(Long id);

    /**
     * 获得统一行政区划配置表（树形结构）分页
     *
     * @param pageReqVO 分页查询
     * @return 统一行政区划配置表（树形结构）分页
     */
    PageResult<AreaDO> getAreaPage(AreaPageReqVO pageReqVO);


    /**
     * 获得子级行政区划列表
     *
     * @param parentId 父级ID
     * @return 子级行政区划列表
     */
    List<AreaDO> getChildAreaList(Long parentId);

    /**
     * 获得完整的行政区划树
     *
     * @return 完整的行政区划树
     */
    List<AreaTreeRespVO> getAreaTree();

    /**
     * 获得指定节点的子树
     *
     * @param parentId 父级ID
     * @return 子树
     */
    List<AreaTreeRespVO> getAreaTree(Long parentId);

    /**
     * 获得所有祖先节点列表（包含自身）
     *
     * @param id 节点ID
     * @return 祖先节点列表（从根节点到当前节点）
     */
    List<AreaDO> getAncestorList(Long id);

    /**
     * 校验父级是否合法（不能设置自己或自己的子节点为父级）
     *
     * @param id 当前节点ID
     * @param parentId 父级ID
     */
    void validateParentValid(Long id, Long parentId);

    /**
     * 根据社区ID获取行政区划信息
     *
     * @param commId 社区ID
     * @return 行政区划信息
     */
    AreaDO getAreaByCommId(String commId);

    /**
     * 获得所有已生效的乡镇和社区数据
     *
     * @return 下拉列表数据
     */
    AreaDropdownRespVO getAreaDropdownList();
}