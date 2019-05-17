package net.lantrack.framework.monitor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
 
/**
 * SSH工具类
 */
public class SSHUtil {
 
    /**
     * 远程 执行命令并返回结果调用过程 是同步的（执行完才会返回）
     *
     * @param host
     *            主机名
     * @param user
     *            用户名
     * @param psw
     *            密码
     * @param port
     *            端口
     * @param command
     *            命令
     * @return
     */
    public static String exec(String host, String user, String psw, int port, String command) {
        String result = "";
        Session session = null;
        ChannelExec openChannel = null;
        try {
            // String osName = System.getProperty("os.name");
            // System.out.println("os name:"+osName);
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(psw);
            session.connect();
            openChannel = (ChannelExec) session.openChannel("exec");
            openChannel.setCommand(command);
            int exitStatus = openChannel.getExitStatus();
            System.out.println(exitStatus);
            openChannel.connect();
            InputStream in = openChannel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                result += new String(buf.getBytes("gbk"), "UTF-8") + "  \r\n";
            }
        } catch (JSchException | IOException e) {
            result += e.getMessage();
        } finally {
            if (openChannel != null && !openChannel.isClosed()) {
                openChannel.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        return result;
    }
 
    public static void main(String args[]) {
//        String memory = exec("192.168.0.130", "root", "Admin123456", 22, "free\n df -h\n top -b -n 1\n");// 内存
                                                                                                    // (命令\n分割，合起来写也ok)
//         String disk = exec("192.168.0.202", "root", "Admin123456", 22, "df -h\n");//
        // 磁盘
        // cpu
         String cpu = exec("192.168.0.130", "root", "Admin123456", 22, "top -b -n 1\n");
        // 直接写top输出为空，动态命令，只能加参数输出一次
 
//        System.out.println(memory);
//         System.out.println(disk);
         System.out.println(cpu);
    }
}