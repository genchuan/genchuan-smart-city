package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.div.evalgriddiv;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.evalgriddiv.vo.EvalGridDivPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.evalgriddiv.EvalGridDivDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价网格划分 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalGridDivMapper extends BaseMapperX<EvalGridDivDO> {

    default PageResult<EvalGridDivDO> selectPage(EvalGridDivPageReqVO reqVO) {
        LambdaQueryWrapperX<EvalGridDivDO> wrapper = new LambdaQueryWrapperX<EvalGridDivDO>()
                .eqIfPresent(EvalGridDivDO::getEvalGridId, reqVO.getEvalGridId())
                .likeIfPresent(EvalGridDivDO::getEvalGridName, reqVO.getEvalGridName())
                .eqIfPresent(EvalGridDivDO::getTownStreetId, reqVO.getTownStreetId())
                .eqIfPresent(EvalGridDivDO::getGridType, reqVO.getGridType())
                .eqIfPresent(EvalGridDivDO::getIncludedMgIds, reqVO.getIncludedMgIds())
                .eqIfPresent(EvalGridDivDO::getArea, reqVO.getArea())
                .betweenIfPresent(EvalGridDivDO::getDivTime, reqVO.getDivTime())
                .betweenIfPresent(EvalGridDivDO::getUpdateTime, reqVO.getUpdateTime())
                .eqIfPresent(EvalGridDivDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EvalGridDivDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalGridDivDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalGridDivDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalGridDivDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalGridDivDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EvalGridDivDO::getUpdateTimeSys, reqVO.getUpdateTimeSys());

        // 新增：根据排序字段和方向进行排序
        applySorting(wrapper, reqVO.getSortField(), reqVO.getSortOrder());

        return selectPage(reqVO, wrapper);
    }

    // 新增：排序逻辑处理方法
    default void applySorting(LambdaQueryWrapperX<EvalGridDivDO> wrapper,
                                      String sortField, String sortOrder) {
        if (sortField == null || sortField.trim().isEmpty()) {
            // 默认排序：按ID降序
            wrapper.orderByDesc(EvalGridDivDO::getId);
            return;
        }

        boolean isAsc = "asc".equalsIgnoreCase(sortOrder);

        switch (sortField.toLowerCase()) {
            case "area":
                if (isAsc) {
                    wrapper.orderByAsc(EvalGridDivDO::getArea);
                } else {
                    wrapper.orderByDesc(EvalGridDivDO::getArea);
                }
                break;
            case "evalgridname":
                if (isAsc) {
                    wrapper.orderByAsc(EvalGridDivDO::getEvalGridName);
                } else {
                    wrapper.orderByDesc(EvalGridDivDO::getEvalGridName);
                }
                break;
            case "gridtype":
                if (isAsc) {
                    wrapper.orderByAsc(EvalGridDivDO::getGridType);
                } else {
                    wrapper.orderByDesc(EvalGridDivDO::getGridType);
                }
                break;
            case "divtime":
                if (isAsc) {
                    wrapper.orderByAsc(EvalGridDivDO::getDivTime);
                } else {
                    wrapper.orderByDesc(EvalGridDivDO::getDivTime);
                }
                break;
            case "createtime":
                if (isAsc) {
                    wrapper.orderByAsc(EvalGridDivDO::getCreateTime);
                } else {
                    wrapper.orderByDesc(EvalGridDivDO::getCreateTime);
                }
                break;
            default:
                // 默认按ID降序
                wrapper.orderByDesc(EvalGridDivDO::getId);
                break;
        }
    }

    // 新增：按面积排序的便捷方法
    default List<EvalGridDivDO> selectListOrderByArea(String sortOrder) {
        LambdaQueryWrapperX<EvalGridDivDO> wrapper = new LambdaQueryWrapperX<>();

        if ("asc".equalsIgnoreCase(sortOrder)) {
            wrapper.orderByAsc(EvalGridDivDO::getArea);
        } else {
            wrapper.orderByDesc(EvalGridDivDO::getArea);
        }

        return selectList(wrapper);
    }

    // 新增：按乡镇和面积排序
    default List<EvalGridDivDO> selectListByTownOrderByArea(String townStreetId, String sortOrder) {
        LambdaQueryWrapperX<EvalGridDivDO> wrapper = new LambdaQueryWrapperX<EvalGridDivDO>()
                .eqIfPresent(EvalGridDivDO::getTownStreetId, townStreetId);

        if ("asc".equalsIgnoreCase(sortOrder)) {
            wrapper.orderByAsc(EvalGridDivDO::getArea);
        } else {
            wrapper.orderByDesc(EvalGridDivDO::getArea);
        }

        return selectList(wrapper);
    }


    // 新增：查询所有数据的便捷方法
    default List<EvalGridDivDO> selectList() {
        return selectList(new LambdaQueryWrapperX<EvalGridDivDO>()
                .orderByDesc(EvalGridDivDO::getId));
    }

    // 新增：根据乡镇查询
    default List<EvalGridDivDO> selectListByTown(String townStreetId) {
        return selectList(new LambdaQueryWrapperX<EvalGridDivDO>()
                .eqIfPresent(EvalGridDivDO::getTownStreetId, townStreetId)
                .orderByAsc(EvalGridDivDO::getEvalGridName));
    }

    // 新增：根据类型查询
    default List<EvalGridDivDO> selectListByType(String gridType) {
        return selectList(new LambdaQueryWrapperX<EvalGridDivDO>()
                .eqIfPresent(EvalGridDivDO::getGridType, gridType)
                .orderByAsc(EvalGridDivDO::getEvalGridName));
    }

}