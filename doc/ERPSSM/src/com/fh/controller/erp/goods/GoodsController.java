package com.fh.controller.erp.goods;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.util.AppUtil;
import com.fh.util.BarcodeUtil;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.util.PathUtil;
import com.fh.util.TwoDimensionCode;
import com.fh.service.erp.goods.GoodsManager;
import com.fh.service.erp.spbrand.SpbrandManager;
import com.fh.service.erp.sptype.SptypeManager;
import com.fh.service.erp.spunit.SpunitManager;
import com.fh.service.information.pictures.PicturesManager;

/** 
 * 说明：商品管理
 * 创建人：FH Q313596790
 * 创建时间：2016-09-09
 */
@Controller
@RequestMapping(value="/goods")
public class GoodsController extends BaseController {
	
	String menuUrl = "goods/list.do"; //菜单地址(权限用)
	@Resource(name="goodsService")
	private GoodsManager goodsService;
	@Resource(name="picturesService")
	private PicturesManager picturesService;
	@Resource(name="spbrandService")
	private SpbrandManager spbrandService;
	@Resource(name="sptypeService")
	private SptypeManager sptypeService;
	@Resource(name="spunitService")
	private SpunitManager spunitService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Goods");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("GOODS_ID", this.get32UUID());	//主键 
		pd.put("ZCOUNT", 0);					//库存
		pd.put("USERNAME", Jurisdiction.getUsername());	//用户名
		goodsService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Goods");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null ;} //校验权限
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success";
		//当商品下面有图片 或者 此商品已经上架 或者 库存不为0时 不能删除
		if(Integer.parseInt(picturesService.findCount(pd).get("zs").toString()) > 0 || Integer.parseInt( goodsService.findById(pd).get("ZCOUNT").toString()) > 0){
			errInfo = "false";
		}else{
			goodsService.delete(pd);
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Goods");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		goodsService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**二维码页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/erweima")
	public ModelAndView erweima() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String encoderImgId = pd.getString("GOODS_ID")+".png"; //encoderImgId此处二维码的图片名
		String filePath = PathUtil.getClasspath() + Const.FILEPATHTWODIMENSIONCODE + encoderImgId; 		//存放路径
		TwoDimensionCode.encoderQRCode(pd.getString("url"), filePath, "png");							//执行生成二维码
		mv.setViewName("erp/goods/goods_erweima");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**条形码页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/barcode")
	public ModelAndView barcode() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String barcodeImgId = pd.getString("GOODS_ID")+".png"; 									//barcodeImgId此处条形码的图片名
		String filePath = PathUtil.getClasspath() + Const.FILEPATHTBARCODE + barcodeImgId; 		//存放路径
		BarcodeUtil.generateFile(pd.getString("BIANMA"), filePath);								//执行生成条形码
		mv.setViewName("erp/goods/goods_barcode");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Goods");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		pd.put("USERNAME", "admin".equals(Jurisdiction.getUsername())?"":Jurisdiction.getUsername());
		page.setPd(pd);
		List<PageData>	varList = goodsService.list(page);	//列出Goods列表
		List<PageData> spbrandList = spbrandService.listAll(Jurisdiction.getUsername()); 	//品牌列表
		List<PageData> sptypeList = sptypeService.listAll(Jurisdiction.getUsername()); 		//类别列表
		List<PageData> spunitList = spunitService.listAll(Jurisdiction.getUsername()); 		//计量单位列表
		mv.setViewName("erp/goods/goods_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("spbrandList", spbrandList);
		mv.addObject("sptypeList", sptypeList);
		mv.addObject("spunitList", spunitList);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData> spbrandList = spbrandService.listAll(Jurisdiction.getUsername()); 	//品牌列表
		List<PageData> sptypeList = sptypeService.listAll(Jurisdiction.getUsername()); 		//类别列表
		List<PageData> spunitList = spunitService.listAll(Jurisdiction.getUsername()); 		//计量单位列表
		mv.setViewName("erp/goods/goods_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		mv.addObject("spbrandList", spbrandList);
		mv.addObject("sptypeList", sptypeList);
		mv.addObject("spunitList", spunitList);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = goodsService.findById(pd);	//根据ID读取
		List<PageData> spbrandList = spbrandService.listAll(Jurisdiction.getUsername()); 	//品牌列表
		List<PageData> sptypeList = sptypeService.listAll(Jurisdiction.getUsername()); 		//类别列表
		List<PageData> spunitList = spunitService.listAll(Jurisdiction.getUsername()); 		//计量单位列表
		mv.setViewName("erp/goods/goods_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		mv.addObject("spbrandList", spbrandList);
		mv.addObject("sptypeList", sptypeList);
		mv.addObject("spunitList", spunitList);
		return mv;
	}
	
	 /**去查看商品页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goView")
	public ModelAndView goView()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = goodsService.findByIdToCha(pd);	//根据ID读取
		mv.setViewName("erp/goods/goods_view");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**通过产品编码
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/getByBm")
	@ResponseBody
	public Object getByBm() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"通过产品编码获取信息");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success";
		pd.put("USERNAME", Jurisdiction.getUsername());	//用户名
		List<PageData> goodsList = goodsService.listByBm(pd);
		if(goodsList.size()>0){
			pd = goodsList.get(0);	//商品编码可重复,只取库存大的
			map.put("pd", pd);
		}else{
			errInfo = "errer"; 		//此用户此编码下无商品
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
