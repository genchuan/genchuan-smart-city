package cn.iocoder.yudao.module.data.service.code;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.data.controller.admin.code.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.code.GeoCodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.data.dal.mysql.code.GeoCodeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.data.enums.ErrorCodeConstants.*;

/**
 * 地理编码 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class GeoCodeServiceImpl implements GeoCodeService {

    @Resource
    private GeoCodeMapper geoCodeMapper;

    @Override
    public Long createGeoCode(GeoCodeSaveReqVO createReqVO) {
        // 插入
        GeoCodeDO geoCode = BeanUtils.toBean(createReqVO, GeoCodeDO.class);
        geoCodeMapper.insert(geoCode);
        // 返回
        return geoCode.getId();
    }

    @Override
    public void updateGeoCode(GeoCodeSaveReqVO updateReqVO) {
        // 校验存在
        validateGeoCodeExists(updateReqVO.getId());
        // 更新
        GeoCodeDO updateObj = BeanUtils.toBean(updateReqVO, GeoCodeDO.class);
        geoCodeMapper.updateById(updateObj);
    }

    @Override
    public void deleteGeoCode(Long id) {
        // 校验存在
        validateGeoCodeExists(id);
        // 删除
        geoCodeMapper.deleteById(id);
    }

    private void validateGeoCodeExists(Long id) {
        if (geoCodeMapper.selectById(id) == null) {
            throw exception(GEO_CODE_NOT_EXISTS);
        }
    }

    @Override
    public GeoCodeDO getGeoCode(Long id) {
        return geoCodeMapper.selectById(id);
    }

    @Override
    public PageResult<GeoCodeDO> getGeoCodePage(GeoCodePageReqVO pageReqVO) {
        return geoCodeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GeoCodeTreeRespVO> getGeoCodeTree() {
        // 1. 查询所有地理编码数据
        List<GeoCodeDO> allGeoCodes = geoCodeMapper.selectList();

        // 2. 转换为树节点VO
        List<GeoCodeTreeRespVO> nodeList = BeanUtils.toBean(allGeoCodes, GeoCodeTreeRespVO.class);

        // 3. 构建树形结构
        return buildTree(nodeList);
    }

    /**
     * 构建树形结构
     */
    private List<GeoCodeTreeRespVO> buildTree(List<GeoCodeTreeRespVO> nodeList) {
        // 用于快速查找节点的Map
        Map<String, GeoCodeTreeRespVO> nodeMap = new HashMap<>();
        // 根节点列表
        List<GeoCodeTreeRespVO> rootNodes = new ArrayList<>();

        // 第一遍遍历：将所有节点放入Map，并初始化children列表
        for (GeoCodeTreeRespVO node : nodeList) {
            nodeMap.put(node.getCode(), node);
            node.setChildren(new ArrayList<>());
        }

        // 第二遍遍历：构建父子关系
        for (GeoCodeTreeRespVO node : nodeList) {
            String parentCode = node.getParentGeoCodeId();
            if (parentCode != null && !parentCode.isEmpty()) {
                // 如果有父节点，将自己添加到父节点的children中
                GeoCodeTreeRespVO parentNode = nodeMap.get(parentCode);
                if (parentNode != null && parentNode.getChildren() != null) {
                    parentNode.getChildren().add(node);
                }
            } else {
                // 如果没有父节点，就是根节点
                rootNodes.add(node);
            }
        }

        // 对每个节点的子节点按ID排序（可选）
        for (GeoCodeTreeRespVO node : nodeList) {
            if (node.getChildren() != null && !node.getChildren().isEmpty()) {
                node.getChildren().sort(Comparator.comparing(GeoCodeTreeRespVO::getId));
            }
        }

        // 对根节点排序
        rootNodes.sort(Comparator.comparing(GeoCodeTreeRespVO::getId));

        return rootNodes;
    }

}