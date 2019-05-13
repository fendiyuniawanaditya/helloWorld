package test.poc.nisp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

@RestController
public class HelloWorld {

    String env = System.getenv("env");

    @RequestMapping("/")
    public String index(){
        return "Hello World This is From Env "+ env +" IP "+ getCurrentIP();
    }
    @RequestMapping("/v1")
    public String indexv1(){
        return "Hello World This is From Env "+ env +" IP "+ getIPAddress();
    }


    public String getCurrentIP()  {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String IPAddress = inetAddress.getHostAddress().toString();
        System.out.println("IP Address:- " + IPAddress);
        return IPAddress;
    }

    public String getIPAddress(){
        String ip = new String();
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            ip = socket.getLocalAddress().getHostAddress();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
}
