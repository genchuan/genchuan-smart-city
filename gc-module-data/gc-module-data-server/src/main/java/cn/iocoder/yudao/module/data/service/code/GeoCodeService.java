package cn.iocoder.yudao.module.data.service.code;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.data.controller.admin.code.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.code.GeoCodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 地理编码 Service 接口
 *
 * @author zhucongquan
 */
public interface GeoCodeService {

    /**
     * 创建地理编码
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeoCode(@Valid GeoCodeSaveReqVO createReqVO);

    /**
     * 更新地理编码
     *
     * @param updateReqVO 更新信息
     */
    void updateGeoCode(@Valid GeoCodeSaveReqVO updateReqVO);

    /**
     * 删除地理编码
     *
     * @param id 编号
     */
    void deleteGeoCode(Long id);

    /**
     * 获得地理编码
     *
     * @param id 编号
     * @return 地理编码
     */
    GeoCodeDO getGeoCode(Long id);

    /**
     * 获得地理编码分页
     *
     * @param pageReqVO 分页查询
     * @return 地理编码分页
     */
    PageResult<GeoCodeDO> getGeoCodePage(GeoCodePageReqVO pageReqVO);

    /**
     * 获得地理编码树
     *
     * @return 地理编码树
     */
    List<GeoCodeTreeRespVO> getGeoCodeTree();


}