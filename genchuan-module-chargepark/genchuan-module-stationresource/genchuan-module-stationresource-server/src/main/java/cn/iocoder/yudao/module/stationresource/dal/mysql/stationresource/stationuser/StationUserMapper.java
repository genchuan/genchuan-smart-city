package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationuser;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser.vo.StationUserPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser.vo.StationUserRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationuser.StationUserDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 站点用户 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface StationUserMapper extends BaseMapperX<StationUserDO> {

    Page<StationUserRespVO> getPage(Page<StationUserRespVO> page, @Param("pageReqVO") StationUserPageReqVO pageReqVO);

}
