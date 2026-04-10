package cn.iocoder.yudao.module.appearance.dal.mysql.outdoorad;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo.*;
import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.OutdoorAdDO;
import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.OutdoorAdOrderDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 户外广告 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface OutdoorAdMapper extends BaseMapperX<OutdoorAdDO> {


    IPage<OutdoorAdPageRespVO> selectPageWithJoin(Page<?> page, @Param("reqVO") OutdoorAdPageReqVO reqVO);

    OutdoorAdGetRespVO selectDetailById(@Param("id") String id);

    void updateAdIdForAttachments(@Param("fileIds") List<String> fileIds, @Param("adId") String adId);

    void clearAdIdByAdId(@Param("adId") String adId);

    void clearAdIdByAdIds(@Param("adIds") List<String> adIds);

    IPage<OutdoorAdOrderPageRespVO> selectOrderPage(Page<?> page, @Param("reqVO") OutdoorAdOrderPageReqVO reqVO);

    OutdoorAdOrderGetRespVO selectOrderDetailById(@Param("id") String id);

    List<OutdoorAdOrderGetRespVO.ImageItem> selectImagesByOrderIdAndType(@Param("orderId") String orderId, @Param("fileType") String fileType);

    /**
     * 关联查询户外广告列表
     */
    IPage<OutdoorAdDO> selectPageVO( IPage<?> page, @Param("reqVO") OutdoorAdPageReqVO reqVO);

    /**
     * 关联查询户外广告详情
     */
    OutdoorAdDO selectOneWithRelations( @Param("reqVO") OutdoorAdGetReqVO reqVO);

    /**
     * 根据区域编码查询区域ID
     * @param areaCode
     * @return
     */
    Long getAreaIdByCode( String areaCode );

    /**
     * 根据网格编码查询网格ID
     * @param gridCode
     * @return
     */
    Long getGridIdByCode( String gridCode );

    /**
     * 根据广告主键ID查询广告ID
     * @param outdoorAdId
     * @return
     */
    Long getIdByOutdoorAdId( String outdoorAdId );

    /**
     * 关联查询户外广告订单列表
     */
    IPage<OutdoorAdOrderDO> selectOrderPageVO( Page<OutdoorAdOrderDO> page, OutdoorAdOrderPageReqVO pageReqVO );

    /**
     * 关联查询户外广告订单详情
     */
    OutdoorAdOrderDO selectOneOrder( @Param("reqVO") OutdoorAdOrderGetReqVO reqVO );

}