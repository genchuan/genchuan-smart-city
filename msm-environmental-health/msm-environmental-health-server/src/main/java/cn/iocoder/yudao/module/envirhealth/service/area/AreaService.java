package cn.iocoder.yudao.module.envirhealth.service.area;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user.UserOptionVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.area.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 区域编码 Service 接口
 *
 * @author 芋道源码
 */
public interface AreaService {

    /**
     * 创建区域编码
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createArea(@Valid AreaSaveReqVO createReqVO);

    /**
     * 更新区域编码
     *
     * @param updateReqVO 更新信息
     */
    void updateArea(@Valid AreaSaveReqVO updateReqVO);

    /**
     * 删除区域编码
     *
     * @param id 编号
     */
    void deleteArea(Long id);

    /**
     * 获得区域编码
     *
     * @param id 编号
     * @return 区域编码
     */
    AreaDO getArea(Long id);

    /**
     * 获得区域编码分页
     *
     * @param pageReqVO 分页查询
     * @return 区域编码分页
     */
    PageResult<AreaDO> getAreaPage(AreaPageReqVO pageReqVO);

    /**
     * 获得区域编码下拉框选项
     * @return 下拉框选项列表
     */
    List<AreaOptionVO> getAreaOptions();

    /**
     * 根据区域编码获取区域名称
     * @param areaCode 区域编码（如 310101）
     * @return 区域名称（如 黄浦区），无则返回 null
     */
    String getAreaNameByCode(String areaCode);
}