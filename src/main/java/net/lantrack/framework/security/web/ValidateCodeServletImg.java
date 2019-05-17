package net.lantrack.framework.security.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.session.Session;

import net.lantrack.framework.core.entity.ReturnEntity;
import net.lantrack.framework.core.util.GsonUtil;
import net.lantrack.framework.sysbase.util.UserUtil;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 生成随机验证码
 *
 * @version 2015-7-27
 */
@SuppressWarnings("serial")
public class ValidateCodeServletImg extends HttpServlet {

    public static final String VALIDATE_CODE = "validateCode";
    private String code = "";
    private int w = 70;
    private int h = 26;

    public ValidateCodeServletImg() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public String getCode() {
        return code;
    }

    public static boolean validate(HttpServletRequest request, String validateCode) {
        String code = (String) request.getSession().getAttribute(VALIDATE_CODE);
        return validateCode.toUpperCase().equals(code);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String validateCode = request.getParameter(VALIDATE_CODE); // AJAX验证，成功返回true
        if (StringUtils.isNotBlank(validateCode)) {
            response.getOutputStream().print(validate(request, validateCode) ? "true" : "false");
        } else {
            this.doPost(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        createImage(request, response);
    }

    private void createImage(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//        response.setContentType("image/jpeg");
        
     
		/*
		 * 得到参数高，宽，都为数字时，则使用设置高宽，否则使用默认值
		 */
        String width = request.getParameter("width");
        String height = request.getParameter("height");
        if (StringUtils.isNumeric(width) && StringUtils.isNumeric(height)) {
            w = NumberUtils.toInt(width);
            h = NumberUtils.toInt(height);
        }

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

		/*
		 * 生成背景
		 */
        createBackground(g);

		/*
		 * 生成字符
		 */
        String s = createCharacter(g);
        Session session = UserUtil.getSession();
        session.setAttribute(VALIDATE_CODE, s);
        code = s;
        g.dispose();
//        OutputStream out = response.getOutputStream();
//        ImageIO.write(image, "JPEG", out);
//        out.close();
        //
        @SuppressWarnings("deprecation")
		String imageName =request.getRealPath("/")+System.currentTimeMillis()+".jpeg";
        File file = new File(imageName);
        ImageIO.write(image, "JPEG", file);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        //返回验证码字节流
        ReturnEntity info = new ReturnEntity();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("token", session.getId());
        resultMap.put("image", getImageStr(imageName));
        info.setResult(resultMap);
        if(file.exists()) {
        	file.delete();
        }
        response.getWriter().write(GsonUtil.toJson(info));
       
        
    }
    //将图片验证码转成base64字节流
    public String getImageStr(String imgFile){
        InputStream in=null;
        byte[] data=null;
        try {
            in=new FileInputStream(imgFile);
            data=new byte[in.available()];
            in.read(data);
            in.close();
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            return "";
        } catch (IOException e) {
//            e.printStackTrace();
            return "";
        }
        BASE64Encoder encoder=new BASE64Encoder();
        return encoder.encode(data);
    }

    private Color getRandColor(int fc, int bc) {
        int f = fc;
        int b = bc;
        Random random = new Random();
        if (f > 255) {
            f = 255;
        }
        if (b > 255) {
            b = 255;
        }
        return new Color(f + random.nextInt(b - f), f + random.nextInt(b - f), f + random.nextInt(b - f));
    }

    private void createBackground(Graphics g) {
        // 填充背景
        g.setColor(getRandColor(220, 250));
        g.fillRect(0, 0, w, h);
        // 加入干扰线条
        for (int i = 0; i < 4; i++) {
            g.setColor(getRandColor(40, 150));
            Random random = new Random();
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int x1 = random.nextInt(w);
            int y1 = random.nextInt(h);
            g.drawLine(x, y, x1, y1);
        }
    }

    private String createCharacter(Graphics g) {
        char[] codeSeq = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
                'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};
        String[] fontTypes = {"Arial", "Arial Black", "AvantGarde Bk BT", "Calibri"};
        Random random = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);//random.nextInt(10));
            g.setColor(new Color(50 + random.nextInt(100), 50 + random.nextInt(100), 50 + random.nextInt(100)));
            g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)], Font.BOLD, 26));
            g.drawString(r, 15 * i + 5, 19 + random.nextInt(8));
//			g.drawString(r, i*w/4, h-5);
            s.append(r);
        }
        return s.toString();
    }


}
