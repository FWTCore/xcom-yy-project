package com.ruoyi.web.controller.agent;

import com.ruoyi.business.domain.entity.PaAccountDO;
import com.ruoyi.business.domain.model.PaAccount;
import com.ruoyi.business.domain.model.PaAgent;
import com.ruoyi.business.domain.model.response.PaAgentVO;
import com.ruoyi.business.service.PaAgentService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * PA平台智能体;PA平台智能体Controller
 *
 * @author xcom
 * @date 2026/1/2
 */
@RestController
@RequestMapping("/agent/agent")
public class PaAgentController extends BaseController {
    @Resource
    private PaAgentService paAgentService;

    /**
     * 查询PA平台智能体;PA平台智能体列表
     */
    @PreAuthorize("@ss.hasPermi('system:agent:list')")
    @GetMapping("/list")
    public TableDataInfo list(PaAgent paAgent) {
        startPage();
        List<PaAgentVO> list = paAgentService.selectPaAgentList(paAgent);
        return getDataTable(list);
    }

    /**
     * 导出PA平台智能体;PA平台智能体列表
     */
    @PreAuthorize("@ss.hasPermi('system:agent:export')")
    @Log(title = "PA平台智能体;PA平台智能体", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaAgent paAgent) {
        List<PaAgentVO> list = paAgentService.selectPaAgentList(paAgent);
        ExcelUtil<PaAgentVO> util = new ExcelUtil<PaAgentVO>(PaAgentVO.class);
        util.exportExcel(response, list, "PA平台智能体;PA平台智能体数据");
    }

    /**
     * 获取PA平台智能体;PA平台智能体详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:agent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(paAgentService.selectPaAgentById(id));
    }

    /**
     * 新增PA平台智能体;PA平台智能体
     */
    @PreAuthorize("@ss.hasPermi('system:agent:add')")
    @Log(title = "PA平台智能体;PA平台智能体", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaAgent paAgent) {
        return toAjax(paAgentService.insertPaAgent(paAgent));
    }

    /**
     * 修改PA平台智能体;PA平台智能体
     */
    @PreAuthorize("@ss.hasPermi('system:agent:edit')")
    @Log(title = "PA平台智能体;PA平台智能体", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaAgent paAgent) {
        return toAjax(paAgentService.updatePaAgent(paAgent));
    }

    /**
     * 删除PA平台智能体;PA平台智能体
     */
    @PreAuthorize("@ss.hasPermi('system:agent:remove')")
    @Log(title = "PA平台智能体;PA平台智能体", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(paAgentService.deletePaAgentByIds(ids));
    }

    /**
     * 查询账号列表
     */
    @GetMapping("/listAll")
    public AjaxResult listAll() {
        PaAgent paAgent = new PaAgent();
        List<PaAgentVO> list = paAgentService.selectPaAgentList(paAgent);
        return success(list);
    }
}
