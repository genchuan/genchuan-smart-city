package cn.iocoder.yudao.module.datacenter.service.grid.area.area;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo.AreaDropdownRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo.AreaSaveReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo.AreaTreeRespVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.area.AreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.area.area.AreaMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 统一行政区划配置表（树形结构） Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createArea(AreaSaveReqVO createReqVO) {
        // 校验父级是否存在
        if (!createReqVO.getParentId().equals(0L)) {
            validateAreaExists(createReqVO.getParentId());
        }

        // 插入
        AreaDO area = BeanUtils.toBean(createReqVO, AreaDO.class);
        areaMapper.insert(area);
        // 返回
        return area.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArea(AreaSaveReqVO updateReqVO) {
        // 校验存在
        validateAreaExists(updateReqVO.getId());

        // 校验父级是否合法
        validateParentValid(updateReqVO.getId(), updateReqVO.getParentId());

        // 更新
        AreaDO updateObj = BeanUtils.toBean(updateReqVO, AreaDO.class);
        areaMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArea(Long id) {
        // 校验存在
        validateAreaExists(id);

        // 检查是否有子节点
        List<AreaDO> children = getChildAreaList(id);
        if (!children.isEmpty()) {
            throw exception(AREA_EXISTS_CHILDREN);
        }

        // 删除
        areaMapper.deleteById(id);
    }

    private void validateAreaExists(Long id) {
        if (areaMapper.selectById(id) == null) {
            throw exception(AREA_NOT_EXISTS);
        }
    }

    @Override
    public AreaDO getArea(Long id) {
        return areaMapper.selectById(id);
    }

    @Override
    public PageResult<AreaDO> getAreaPage(AreaPageReqVO pageReqVO) {
        return areaMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AreaDO> getChildAreaList(Long parentId) {
        return areaMapper.selectList(AreaDO::getParentId, parentId);
    }

    @Override
    public List<AreaTreeRespVO> getAreaTree() {
        return getAreaTree(0L); // 从根节点开始构建树
    }

    @Override
    public List<AreaTreeRespVO> getAreaTree(Long parentId) {
        // 获取所有数据（为了构建完整的树结构）
        List<AreaDO> allAreas = areaMapper.selectList();

        // 构建树形结构
        return buildAreaTree(allAreas, parentId);
    }

    @Override
    public AreaDO getAreaByCommId(String commId) {
        AreaDO area = areaMapper.selectByCommId(commId);
        if (area == null) {
            throw exception(AREA_NOT_EXISTS);
        }
        return area;
    }

    @Override
    public AreaDropdownRespVO getAreaDropdownList() {
        LocalDateTime now = LocalDateTime.now();

        // 查询所有已生效的乡镇和社区数据
        List<AreaDO> effectiveAreas = areaMapper.selectList(new LambdaQueryWrapperX<AreaDO>()
                .in(AreaDO::getLevel, 4, 5) // 只查询乡镇（4）和社区（5）
                .le(AreaDO::getEffectiveTime, now) // 生效时间 <= 当前时间
                .ge(AreaDO::getInvalidTime, now) // 失效时间 >= 当前时间
                .or(wrapper -> wrapper.le(AreaDO::getEffectiveTime, now).isNull(AreaDO::getInvalidTime))
                .orderByAsc(AreaDO::getParentId)
                .orderByAsc(AreaDO::getId));

        // 构建响应对象
        AreaDropdownRespVO respVO = new AreaDropdownRespVO();
        respVO.setTownList(buildTownWithCommunities(effectiveAreas));

        return respVO;
    }

    /**
     * 递归构建树形结构
     */
    private List<AreaTreeRespVO> buildAreaTree(List<AreaDO> allAreas, Long parentId) {
        return allAreas.stream()
                .filter(area -> Objects.equals(area.getParentId(), parentId))
                .map(area -> {
                    AreaTreeRespVO treeVO = BeanUtils.toBean(area, AreaTreeRespVO.class);
                    List<AreaTreeRespVO> children = buildAreaTree(allAreas, area.getId());
                    treeVO.setChildren(children);
                    return treeVO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AreaDO> getAncestorList(Long id) {
        List<AreaDO> ancestors = new ArrayList<>();
        AreaDO current = areaMapper.selectById(id);
        if (current == null) {
            return ancestors;
        }

        ancestors.add(current);

        // 递归查找父级节点
        Long parentId = current.getParentId();
        while (parentId != null && !parentId.equals(0L)) {
            AreaDO parent = areaMapper.selectById(parentId);
            if (parent != null) {
                ancestors.add(0, parent); // 添加到开头，保证顺序是从根节点到当前节点
                parentId = parent.getParentId();
            } else {
                break;
            }
        }

        return ancestors;
    }

    @Override
    public void validateParentValid(Long id, Long parentId) {
        if (id.equals(parentId)) {
            throw exception(AREA_PARENT_IS_SELF);
        }

        // 检查父级节点是否是当前节点的子节点（避免循环引用）
        List<AreaDO> children = getAreaDescendants(id);
        boolean isChild = children.stream()
                .anyMatch(child -> child.getId().equals(parentId));
        if (isChild) {
            throw exception(AREA_PARENT_IS_CHILD);
        }
    }

    /**
     * 获取所有后代节点（包括子节点、孙节点等）
     */
    private List<AreaDO> getAreaDescendants(Long parentId) {
        List<AreaDO> descendants = new ArrayList<>();
        List<AreaDO> children = getChildAreaList(parentId);
        descendants.addAll(children);

        for (AreaDO child : children) {
            descendants.addAll(getAreaDescendants(child.getId()));
        }

        return descendants;
    }

    /**
     * 构建乡镇及其下属社区列表
     */
    private List<AreaDropdownRespVO.TownWithCommunitiesVO> buildTownWithCommunities(List<AreaDO> areas) {
        // 按父级ID分组
        Map<Long, List<AreaDO>> communitiesByTownId = areas.stream()
                .filter(area -> area.getLevel() == 5) // 社区
                .collect(Collectors.groupingBy(AreaDO::getParentId));

        // 构建乡镇列表
        return areas.stream()
                .filter(area -> area.getLevel() == 4) // 乡镇
                .map(town -> {
                    AreaDropdownRespVO.TownWithCommunitiesVO townVO = new AreaDropdownRespVO.TownWithCommunitiesVO();
                    townVO.setId(town.getId());
                    townVO.setName(town.getName());
                    townVO.setFullCode(town.getFullCode());

                    // 添加下属社区
                    List<AreaDropdownRespVO.CommunityVO> communities = communitiesByTownId
                            .getOrDefault(town.getId(), Collections.emptyList())
                            .stream()
                            .map(community -> {
                                AreaDropdownRespVO.CommunityVO communityVO = new AreaDropdownRespVO.CommunityVO();
                                communityVO.setId(community.getId());
                                communityVO.setName(community.getName());
                                communityVO.setFullCode(community.getFullCode());
                                return communityVO;
                            })
                            .collect(Collectors.toList());

                    townVO.setChildren(communities);
                    return townVO;
                })
                .collect(Collectors.toList());
    }
}