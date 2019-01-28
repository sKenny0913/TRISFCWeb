package com.tri.sfc.Dongle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientThreadCode extends Thread
{
    private Socket m_socket;//和伺服器端進行連線
    
    public ClientThreadCode(String ip, int port)
    {
        try
        {
            m_socket = new Socket(ip, port);//建立連線。(ip為伺服器端的ip，port為伺服器端開啟的port)
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void run()
    {
        try
        {
            if (m_socket != null)//連線成功才繼續往下執行0
            {

                //由於Server端使用 PrintStream 和 BufferedReader 來接收和寄送訊息，所以Client端也要相同
                PrintStream writer = new PrintStream(m_socket.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
            
                
                writer.println("Dongle#A01040047PC#789#456#123#Feature_ID:1,perpetual#Feature_ID:2,perpetual#Feature_ID:3,perpetual#Feature_ID:4,perpetual#Feature_ID:16,perpetual#Feature_ID:17,perpetual#Feature_ID:32,perpetual#Feature_ID:33,perpetual#Feature_ID:34,perpetual#Feature_ID:48,perpetual#Feature_ID:64,perpetual#Feature_ID:88,perpetual#Feature_ID:89,perpetual#Feature_ID:96,perpetual#Feature_ID:97,perpetual#Feature_ID:136,perpetual#Feature_ID:137,perpetual#Feature_ID:138,perpetual#");
                writer.flush();
                System.out.println("Server:" + reader.readLine());
            
                m_socket.close();

            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}