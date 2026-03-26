package cn.iocoder.yudao.module.smartcity.service.lawdocument;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawdocument.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawdocument.LawDocumentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 执法文书 Service 接口
 *
 * @author 朱聪权
 */
public interface LawDocumentService {

    /**
     * 创建执法文书
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLawDocument(@Valid LawDocumentSaveReqVO createReqVO);

    /**
     * 更新执法文书
     *
     * @param updateReqVO 更新信息
     */
    void updateLawDocument(@Valid LawDocumentSaveReqVO updateReqVO);

    /**
     * 删除执法文书
     *
     * @param id 编号
     */
    void deleteLawDocument(Long id);

    /**
     * 获得执法文书
     *
     * @param id 编号
     * @return 执法文书
     */
    LawDocumentDO getLawDocument(Long id);

    /**
     * 获得执法文书分页
     *
     * @param pageReqVO 分页查询
     * @return 执法文书分页
     */
    PageResult<LawDocumentDO> getLawDocumentPage(LawDocumentPageReqVO pageReqVO);

}