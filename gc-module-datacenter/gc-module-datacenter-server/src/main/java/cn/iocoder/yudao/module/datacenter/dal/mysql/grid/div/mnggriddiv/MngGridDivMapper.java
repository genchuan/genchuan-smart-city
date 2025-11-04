package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.div.mnggriddiv;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.mnggriddiv.vo.MngGridDivPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.mnggriddiv.MngGridDivDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理网格划分 Mapper
 *
 * @author zcq
 */
@Mapper
public interface MngGridDivMapper extends BaseMapperX<MngGridDivDO> {

    default PageResult<MngGridDivDO> selectPage(MngGridDivPageReqVO reqVO) {
        LambdaQueryWrapperX<MngGridDivDO> wrapper = new LambdaQueryWrapperX<MngGridDivDO>()
                .eqIfPresent(MngGridDivDO::getMngGridId, reqVO.getMngGridId())
                .likeIfPresent(MngGridDivDO::getMngGridName, reqVO.getMngGridName())
                .eqIfPresent(MngGridDivDO::getTownStreetId, reqVO.getTownStreetId())
                .eqIfPresent(MngGridDivDO::getIncludedUnitIds, reqVO.getIncludedUnitIds())
                .eqIfPresent(MngGridDivDO::getArea, reqVO.getArea())
                .betweenIfPresent(MngGridDivDO::getUpdateTime, reqVO.getUpdateTime())
                .eqIfPresent(MngGridDivDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MngGridDivDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(MngGridDivDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(MngGridDivDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MngGridDivDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(MngGridDivDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MngGridDivDO::getUpdateTimeSys, reqVO.getUpdateTimeSys());

        // 处理排序逻辑
        if (StringUtils.isNotBlank(reqVO.getSortField())) {
            if ("area".equals(reqVO.getSortField())) {
                if ("asc".equals(reqVO.getSortOrder())) {
                    wrapper.orderByAsc(MngGridDivDO::getArea);
                } else {
                    wrapper.orderByDesc(MngGridDivDO::getArea);
                }
            } else if ("createTime".equals(reqVO.getSortField())) {
                if ("asc".equals(reqVO.getSortOrder())) {
                    wrapper.orderByAsc(MngGridDivDO::getCreateTime);
                } else {
                    wrapper.orderByDesc(MngGridDivDO::getCreateTime);
                }
            } else if ("updateTime".equals(reqVO.getSortField())) {
                if ("asc".equals(reqVO.getSortOrder())) {
                    wrapper.orderByAsc(MngGridDivDO::getUpdateTime);
                } else {
                    wrapper.orderByDesc(MngGridDivDO::getUpdateTime);
                }
            }
        } else {
            // 默认排序
            wrapper.orderByDesc(MngGridDivDO::getId);
        }

        return selectPage(reqVO, wrapper);
    }



}