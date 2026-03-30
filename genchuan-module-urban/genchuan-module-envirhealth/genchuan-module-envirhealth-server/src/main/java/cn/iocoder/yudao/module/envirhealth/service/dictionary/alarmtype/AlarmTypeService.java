package cn.iocoder.yudao.module.envirhealth.service.dictionary.alarmtype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.alarmtype.vo.AlarmTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.alarmtype.vo.AlarmTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.AlarmTypeDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 预警类型字典 Service 接口
 *
 * @author 芋道源码
 */
public interface AlarmTypeService {

    /**
     * 创建预警类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAlarmType(@Valid AlarmTypeSaveReqVO createReqVO);

    /**
     * 更新预警类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateAlarmType(@Valid AlarmTypeSaveReqVO updateReqVO);

    /**
     * 删除预警类型字典
     *
     * @param id 编号
     */
    void deleteAlarmType(Long id);

    /**
     * 获得预警类型字典
     *
     * @param id 编号
     * @return 预警类型字典
     */
    AlarmTypeDO getAlarmType(Long id);

    /**
     * 获得预警类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 预警类型字典分页
     */
    PageResult<AlarmTypeDO> getAlarmTypePage(AlarmTypePageReqVO pageReqVO);

    /**
     * 获得预警类型下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getAlarmTypeOptions();
}