package cn.iocoder.yudao.module.park.service.park.user.participatingunit;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.participatingunit.vo.ParticipatingUnitPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.participatingunit.vo.ParticipatingUnitSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.participatingunit.ParticipatingUnitDO;
import jakarta.validation.Valid;

/**
 * 参与单位 Service 接口
 *
 * @author 亘川智城
 */
public interface ParticipatingUnitService {

    /**
     * 创建参与单位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParticipatingUnit(@Valid ParticipatingUnitSaveReqVO createReqVO);

    /**
     * 更新参与单位
     *
     * @param updateReqVO 更新信息
     */
    void updateParticipatingUnit(@Valid ParticipatingUnitSaveReqVO updateReqVO);

    /**
     * 删除参与单位
     *
     * @param id 编号
     */
    void deleteParticipatingUnit(Long id);

    /**
     * 获得参与单位
     *
     * @param id 编号
     * @return 参与单位
     */
    ParticipatingUnitDO getParticipatingUnit(Long id);

    /**
     * 获得参与单位分页
     *
     * @param pageReqVO 分页查询
     * @return 参与单位分页
     */
    PageResult<ParticipatingUnitDO> getParticipatingUnitPage(ParticipatingUnitPageReqVO pageReqVO);

}
