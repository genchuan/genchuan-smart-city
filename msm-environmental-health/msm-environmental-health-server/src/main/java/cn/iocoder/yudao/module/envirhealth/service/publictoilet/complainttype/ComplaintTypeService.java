package cn.iocoder.yudao.module.envirhealth.service.publictoilet.complainttype;

import cn.iocoder.yudao.module.envirhealth.controller.admin.area.vo.AreaOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.complainttype.ComplaintTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.complainttype.ComplaintTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.util.options.vo.OptionVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ComplaintTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 投诉类型字典 Service 接口
 *
 * @author 芋道源码
 */
public interface ComplaintTypeService {

    /**
     * 创建投诉类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createComplaintType(@Valid ComplaintTypeSaveReqVO createReqVO);

    /**
     * 更新投诉类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateComplaintType(@Valid ComplaintTypeSaveReqVO updateReqVO);

    /**
     * 删除投诉类型字典
     *
     * @param id 编号
     */
    void deleteComplaintType(Long id);

    /**
     * 获得投诉类型字典
     *
     * @param id 编号
     * @return 投诉类型字典
     */
    ComplaintTypeDO getComplaintType(Long id);

    /**
     * 获得投诉类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 投诉类型字典分页
     */
    PageResult<ComplaintTypeDO> getComplaintTypePage(ComplaintTypePageReqVO pageReqVO);

    /**
     * 获得投诉类型下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getComplaintTypeOptions();
}