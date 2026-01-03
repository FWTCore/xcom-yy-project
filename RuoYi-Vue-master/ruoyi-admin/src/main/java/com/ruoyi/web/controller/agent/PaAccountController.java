package com.ruoyi.web.controller.agent;

import com.ruoyi.business.domain.entity.PaAccountDO;
import com.ruoyi.business.domain.model.PaAccount;
import com.ruoyi.business.service.PaAccountService;
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
 * PA平台账号;PA平台账号Controller
 *
 * @author xcom
 * @date 2026/1/2
 */

@RestController
@RequestMapping("/business/account")
public class PaAccountController extends BaseController {
    @Resource
    private PaAccountService paAccountService;

    /**
     * 查询PA平台账号;PA平台账号列表
     */
    @PreAuthorize("@ss.hasPermi('system:account:list')")
    @GetMapping("/list")
    public TableDataInfo list(PaAccount paAccount) {
        startPage();
        List<PaAccountDO> list = paAccountService.selectPaAccountList(paAccount);
        return getDataTable(list);
    }

    /**
     * 导出PA平台账号;PA平台账号列表
     */
    @PreAuthorize("@ss.hasPermi('system:account:export')")
    @Log(title = "PA平台账号;PA平台账号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaAccount paAccount) {
        List<PaAccountDO> list = paAccountService.selectPaAccountList(paAccount);
        ExcelUtil<PaAccountDO> util = new ExcelUtil<PaAccountDO>(PaAccountDO.class);
        util.exportExcel(response, list, "PA平台账号;PA平台账号数据");
    }

    /**
     * 获取PA平台账号;PA平台账号详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:account:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(paAccountService.selectPaAccountById(id));
    }

    /**
     * 新增PA平台账号;PA平台账号
     */
    @PreAuthorize("@ss.hasPermi('system:account:add')")
    @Log(title = "PA平台账号;PA平台账号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaAccount paAccount) {
        return toAjax(paAccountService.insertPaAccount(paAccount));
    }

    /**
     * 修改PA平台账号;PA平台账号
     */
    @PreAuthorize("@ss.hasPermi('system:account:edit')")
    @Log(title = "PA平台账号;PA平台账号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaAccount paAccount) {
        return toAjax(paAccountService.updatePaAccount(paAccount));
    }

    /**
     * 删除PA平台账号;PA平台账号
     */
    @PreAuthorize("@ss.hasPermi('system:account:remove')")
    @Log(title = "PA平台账号;PA平台账号", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(paAccountService.deletePaAccountByIds(ids));
    }


    /**
     * 查询账号列表
     */
    @GetMapping("/listAll")
    public AjaxResult listAll() {
        PaAccount paAccount = new PaAccount();
        List<PaAccountDO> list = paAccountService.selectPaAccountList(paAccount);
        return success(list);
    }
}
