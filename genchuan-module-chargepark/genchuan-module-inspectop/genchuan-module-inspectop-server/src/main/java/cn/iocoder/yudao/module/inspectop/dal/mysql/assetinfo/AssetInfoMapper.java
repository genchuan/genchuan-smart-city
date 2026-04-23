package cn.iocoder.yudao.module.inspectop.dal.mysql.assetinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetinfo.AssetInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo.vo.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资产信息 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface AssetInfoMapper extends BaseMapperX<AssetInfoDO> {

    default PageResult<AssetInfoDO> selectPage(AssetInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetInfoDO>()
                .likeIfPresent(AssetInfoDO::getName, reqVO.getName())
                .eqIfPresent(AssetInfoDO::getType, reqVO.getType())
                .betweenIfPresent(AssetInfoDO::getPurchaseTime, reqVO.getPurchaseTime())
                .eqIfPresent(AssetInfoDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AssetInfoDO::getStationId, reqVO.getStationId())
                .eqIfPresent(AssetInfoDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(AssetInfoDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(AssetInfoDO::getCreator, reqVO.getCreator())
                .eqIfPresent(AssetInfoDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(AssetInfoDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(AssetInfoDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(AssetInfoDO::getId));
    }

    /**
     * 【新增方法】关联查询分页方法
     * 使用自定义SQL进行关联查询，返回包含场站名称的结果
     *
     * @param page  MyBatis-Plus分页参数，查询后其total、records等属性会被自动填充
     * @param reqVO 查询条件
     * @return 包含场站名称的分页结果
     */
    Page<AssetInfoRespVO> selectPageWithJoin(@Param("page") Page<AssetInfoRespVO> page, @Param("reqVO") AssetInfoPageReqVO reqVO);

    /**
     * 查询资产类型分布数据
     *
     * @return 资产类型分布列表
     */
    List<AssetInfoChartRespVO.TypeData> selectTypeData();

    /**
     * 查询资产卡片统计数据
     *
     * @return 卡片统计数据
     */
    AssetInfoChartRespVO.CardData selectCardData();
}