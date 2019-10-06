
package com.aynu.rent.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.aynu.entity.*;
import com.aynu.rent.service.PaidService;
import com.aynu.rent.service.TopaidService;
import com.aynu.rent.service.UserlistService;
import com.aynu.utils.AlipayConfig;
import com.aynu.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/paid/")
public class PaidController {
	@Autowired
	private PaidService paidService;
	@Autowired
	private TopaidService topaidService;
	@Autowired
	private UserlistService userlistService;
	//管理员查找所有已缴租金列表
	@RequestMapping("selectall.action")
	public String selectall(Model model, QueryVo vo, @RequestParam(defaultValue="1") Integer pageIndex){
        //System.out.println("QueryVo vo="+vo);
		int recourcounts=paidService.adminFindAllUserHasPaidCounts(vo);
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recourcounts);
		pageModel.setPageSize(2);
		pageModel.setPageIndex(pageIndex);
		List<Paid> list=paidService.selectall(vo,pageModel);
		Double sum=paidService.selectsum(vo);
		model.addAttribute("paid", list);
		model.addAttribute("sum", sum);
        model.addAttribute("pageModel",pageModel);
		model.addAttribute("mainPage", "paid.jsp");
		model.addAttribute("vo", vo);
		return "admin/main1";
	}
	//租客查找自己已缴租金列表
		@RequestMapping("findmypaid.action")
		public String findmypaid(HttpSession httpSession,Model model,QueryVo vo,@RequestParam(defaultValue="1") Integer pageIndex){
			User user1= (User) httpSession.getAttribute("user");
			Userlist userlist=userlistService.findhasuserlist(user1.getId());
			
			vo.setUserlist_id(userlist.getId());
			//查看租客已经缴纳租金的数据条数
			int recourcounts = paidService.findZuKeHasPaidCounts(userlist.getId(),vo);
			PageModel pageModel = new PageModel();
			pageModel.setRecordCount(recourcounts);
			pageModel.setPageSize(2);
			pageModel.setPageIndex(pageIndex);
			List<Paid> list=paidService.selectall(vo,pageModel);
			Double sum=paidService.selectsum(vo);
			model.addAttribute("paid", list);
			model.addAttribute("sum", sum);
			model.addAttribute("mainPage", "mypaid.jsp");
			model.addAttribute("vo", vo);
			model.addAttribute("pageModel",pageModel);
			return "zuke/main";
		}
	//管理员删除已缴租金记录
	@RequestMapping("deletepaid")
	public String deletepaid(Integer id){
		paidService.deletepaid(id);
		return "redirect:selectall.action";
	}
	//zuke删除已缴租金记录
		@RequestMapping("/zukedeletepaid.action")
		public String zukedeletepaid(Integer id){
			paidService.deletepaid(id);
			return "redirect:findmypaid.action";
		}
	//跳到我要收租页面
	@RequestMapping("showaddpaid.action")
	public String showaddpaid(Model model,@RequestParam(defaultValue="1") Integer pageIndex)throws Exception{
		int recourcounts=paidService.findZuUserListCounts();
		PageModel pageModel = new PageModel();
		pageModel.setRecordCount(recourcounts);
		pageModel.setPageSize(2);
		pageModel.setPageIndex(pageIndex);
		List<Zulist> list=paidService.findzuuserlist(pageModel);
		model.addAttribute("zulist", list);
		model.addAttribute("pageModel",pageModel);
		model.addAttribute("mainPage", "showaddpaid.jsp");
		return "admin/main1";
	}
	//点击收租后跳转到添加租金信息页面
	@RequestMapping("addpaid.action")
	public String addpaid(Integer id,Model model){
		Zulist zulist=paidService.findzukezulist(id);
		model.addAttribute("zulist", zulist);
		model.addAttribute("mainPage", "addpaid.jsp");
		return "admin/main1";
	}
	//添加租金信息到topaid表
		@RequestMapping("/inserttopaid")
		public String inserttopaid(Topaid topaid,Model model){
			topaidService.inserttopaid(topaid);
			model.addAttribute("error","inserttopaid");
			
			return "redirect:showaddpaid.action";
		}
		//管理员查看所有未缴租金信息
		@RequestMapping("topaidlist.action")
		public String topaidlist(Model model,@RequestParam(defaultValue="1") Integer pageIndex){
			QueryVo vo=new QueryVo();
			int recourcounts=topaidService.adminFindAllHasNotPaidCounts();//kljjklljkklj
			PageModel pageModel = new PageModel();
			pageModel.setRecordCount(recourcounts);
			pageModel.setPageSize(2);
			pageModel.setPageIndex(pageIndex);
			List<Topaid> list=topaidService.adminFindAllUsertopaid(vo,pageModel);
			model.addAttribute("topaid", list);
			model.addAttribute("pageModel",pageModel);
			model.addAttribute("mainPage", "topaid.jsp");
			return "admin/main1";
		}
		//租客查看自己的未缴租金(查询未交租金表)
		@RequestMapping("mytopaidlist.action")
		public String mytopaidlist(Model model,HttpSession httpSession,@RequestParam(defaultValue="1") Integer pageIndex){
			User user1= (User) httpSession.getAttribute("user");
			Userlist userlist=userlistService.findhasuserlist(user1.getId());
			QueryVo vo=new QueryVo();
			vo.setUserlist_id(userlist.getId());
			//查看未交租金的数据条数
			int recourcounts = topaidService.findTotalPaidListCounts(userlist.getId());
			PageModel pageModel = new PageModel();
			pageModel.setRecordCount(recourcounts);
			pageModel.setPageSize(2);
			pageModel.setPageIndex(pageIndex);
			List<Topaid> topaid=topaidService.findtopaid(vo,pageModel);
			model.addAttribute("pageModel",pageModel);
			model.addAttribute("topaid",topaid);
			model.addAttribute("mainPage", "mytopaid.jsp");
			return "zuke/main";
		}
		//租客进行支付操作
		/*@RequestMapping("gotopay.action")
		public String gotopay(Integer id,Model model){
			Date dt=new Date();
		    SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
		    String paydate=matter1.format(dt);
			Topaid topaid=topaidService.findbyid(id);
			Paid paid=new Paid();
			paid.setHouse_id(topaid.getHouse_id());
			paid.setAddress(topaid.getAddress());
			paid.setPrice(topaid.getPrice());
			paid.setDate(topaid.getDate());
			paid.setPaydate(paydate);
			paid.setName(topaid.getName());
			paid.setUserlist_id(topaid.getUserlist_id());
			paid.setStatus("租金已缴");//支付状态修改
			topaidService.gotopay(id, paid);
			model.addAttribute("error", "paysucess");
			return "redirect:findmypaid.action";
		}*/
	//支付
	@RequestMapping("pay.action")
	public void payOrder(String id, String price, String name, HttpServletResponse response,HttpSession session) throws Exception {
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		//HttpSession session=null;
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		//商户订单号，商户网站订单系统中唯一订单号，必填
		//String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		String out_trade_no = id;
		session.setAttribute("topaid_id",out_trade_no);
		//付款金额，必填
		//String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
		String total_amount =price;
		//订单名称，必填
		//String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
		//String subject =name;
		String subject =id;
		//商品描述，可空
		//String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
		String body = "";
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
				+ "\"total_amount\":\""+ total_amount +"\","
				+ "\"subject\":\""+ subject +"\","
				+ "\"body\":\""+ body +"\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
		//		+ "\"total_amount\":\""+ total_amount +"\","
		//		+ "\"subject\":\""+ subject +"\","
		//		+ "\"body\":\""+ body +"\","
		//		+ "\"timeout_express\":\"10m\","
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

		//请求
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		//System.out.println("result="+result);
		//设置相应类容类型
		response.setContentType("text/html");
		//输出
		//out.println(result);
		response.getWriter().print(result);
	}
	//支付成功
	@RequestMapping("paySuccess.action")
	public String paySuccess(HttpServletRequest request,Model model,HttpSession session) throws Exception {
		//通过支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		if (signVerified) {
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
			//orderService.paySuccess(out_trade_no);
			//out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
			//return "/client/paysuccess.jsp";

			String myid = (String) session.getAttribute("topaid_id");
			Integer id = Integer.parseInt(myid);
			Date dt=new Date();
			SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
			String paydate=matter1.format(dt);
			Topaid topaid=topaidService.findbyid(id);
			Paid paid=new Paid();
			paid.setHouse_id(topaid.getHouse_id());
			paid.setAddress(topaid.getAddress());
			paid.setPrice(topaid.getPrice());
			paid.setDate(topaid.getDate());
			paid.setPaydate(paydate);
			paid.setName(topaid.getName());
			paid.setUserlist_id(topaid.getUserlist_id());
			paid.setStatus("租金已缴");//支付状态修改
			topaidService.gotopay(id, paid);
			model.addAttribute("error", "paysucess");
			return "redirect:findmypaid.action";
			//return "gotopay.action";
		} else {
			//out.println("验签失败");
			model.addAttribute("mainPage","payFail.jsp");
			return "zuke/main";
		}
	}
}
