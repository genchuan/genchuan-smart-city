package cn.iocoder.yudao.module.industry.service.park.vas.parksmstemplate;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parksmstemplate.vo.ParkSmsTemplatePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parksmstemplate.vo.ParkSmsTemplateSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parksmstemplate.ParkSmsTemplateDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 短信模板 Service 接口
 *
 * @author lxs
 */
public interface ParkSmsTemplateService {

    /**
     * 创建短信模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkSmsTemplate(@Valid ParkSmsTemplateSaveReqVO createReqVO);

    /**
     * 更新短信模板
     *
     * @param updateReqVO 更新信息
     */
    void updateParkSmsTemplate(@Valid ParkSmsTemplateSaveReqVO updateReqVO);

    /**
     * 删除短信模板
     *
     * @param id 编号
     */
    void deleteParkSmsTemplate(Long id);

    /**
     * 获得短信模板
     *
     * @param id 编号
     * @return 短信模板
     */
    ParkSmsTemplateDO getParkSmsTemplate(Long id);

    /**
     * 获得短信模板分页
     *
     * @param pageReqVO 分页查询
     * @return 短信模板分页
     */
    PageResult<ParkSmsTemplateDO> getParkSmsTemplatePage(ParkSmsTemplatePageReqVO pageReqVO);

}
