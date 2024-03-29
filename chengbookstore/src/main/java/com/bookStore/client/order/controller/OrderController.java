package com.bookStore.client.order.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.bookStore.client.order.service.OrderService;
import com.bookStore.entity.Order;
import com.bookStore.entity.Product;
import com.bookStore.entity.User;
import com.bookStore.utils.AlipayConfig;
import com.bookStore.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/client/order/")
public class OrderController {
    @Autowired
    private OrderService orderService;
    //创建订单
    @RequestMapping("createOrder.do")
    public String createOrder(Order order, HttpSession session, Model model){
        //System.out.println("order="+order);
        //从session域中获取登陆人信息
        User user = (User) session.getAttribute("login_User");
        order.setId(IdUtils.getUUID());
        order.setUser(user);
        //从session域中获取购物车 商品 --》数量
        Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
        //创建订单
        orderService.createOrder(order,cart);
        //把session域中的购物车移除
        session.removeAttribute("cart");
        //把订单存入Model域
        model.addAttribute("order",order);
        //跳转到支付界面，支付成功后把paystate状态修改为1
        return "/client/confirm.jsp";
    }
    //支付
    @RequestMapping("pay.do")
    public void payOrder(String id, String money, String name, HttpServletResponse response) throws Exception {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        //String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        String out_trade_no = id;
        //付款金额，必填
        //String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
        String total_amount =money;
        //订单名称，必填
        //String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
        String subject =name;
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
    @RequestMapping("paySuccess.do")
    public String paySuccess(HttpServletRequest request) throws Exception {
        //通过支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
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
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            orderService.paySuccess(out_trade_no);
            //out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
            return "/client/paysuccess.jsp";
        }else {
            //out.println("验签失败");
            return "/client/payFail.jsp";
        }
    }






}
