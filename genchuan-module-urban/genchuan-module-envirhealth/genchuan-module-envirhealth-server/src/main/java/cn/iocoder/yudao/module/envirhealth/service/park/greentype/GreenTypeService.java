package cn.iocoder.yudao.module.envirhealth.service.park.greentype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.greentype.GreenTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.greentype.GreenTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.GreenTypeDO;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 绿化品类字典表【通用复用】 Service 接口
 *
 * @author 芋道源码
 */
public interface GreenTypeService {

    /**
     * 创建绿化品类字典表【通用复用】
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGreenType(@Valid GreenTypeSaveReqVO createReqVO);

    /**
     * 更新绿化品类字典表【通用复用】
     *
     * @param updateReqVO 更新信息
     */
    void updateGreenType(@Valid GreenTypeSaveReqVO updateReqVO);

    /**
     * 删除绿化品类字典表【通用复用】
     *
     * @param id 编号
     */
    void deleteGreenType(Long id);

    /**
     * 获得绿化品类字典表【通用复用】
     *
     * @param id 编号
     * @return 绿化品类字典表【通用复用】
     */
    GreenTypeDO getGreenType(Long id);

    /**
     * 获得绿化品类字典表【通用复用】分页
     *
     * @param pageReqVO 分页查询
     * @return 绿化品类字典表【通用复用】分页
     */
    PageResult<GreenTypeDO> getGreenTypePage(GreenTypePageReqVO pageReqVO);

    /**
     * 获得绿化品类下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getGreenTypeOptions();
}