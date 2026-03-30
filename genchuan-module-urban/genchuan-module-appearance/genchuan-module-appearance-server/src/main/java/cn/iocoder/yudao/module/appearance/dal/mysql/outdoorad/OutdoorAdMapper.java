package cn.iocoder.yudao.module.appearance.dal.mysql.outdoorad;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo.OutdoorAdGetReqVO;
import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo.OutdoorAdPageReqVO;
import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.OutdoorAdDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 户外广告 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface OutdoorAdMapper extends BaseMapperX<OutdoorAdDO> {
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
}