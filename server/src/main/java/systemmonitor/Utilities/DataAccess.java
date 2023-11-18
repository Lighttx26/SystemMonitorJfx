package systemmonitor.Utilities;

import java.util.ArrayList;
import redis.clients.jedis.Jedis;

public class DataAccess {
    // private String clientKey;

    Jedis jedis;
    static int lim = 100;

    public DataAccess() {
        // this.clientKey = "Client " + clientName;
        // System.out.println("Client key: " + clientKey);
        jedis = new Jedis("localhost", 6379);
    }

    public ArrayList<Double> getCpuUsages(String clientName) {
        String clientKey = "Client " + clientName;
        ArrayList<Double> list = new ArrayList<Double>();

        for (String s_cpu : jedis.lrange(clientKey + ":CPU", 0, lim)) {
            list.add(Double.parseDouble(s_cpu));
        }

        return list;
    }

    public Double getCurrentCpuUsage(String clientName) {
        String clientKey = "Client " + clientName;
        return Double.parseDouble(jedis.lindex(clientKey + ":CPU", -1));
    }

    public void addCpuUsage(String clientName, Double cpu) {
        String clientKey = "Client " + clientName;

        if (jedis.llen(clientKey + ":CPU") >= lim)
            jedis.lpop(clientKey + ":CPU");

        jedis.rpush(clientKey + ":CPU", cpu.toString());
    }

    public ArrayList<Long> getMemoryUsages(String clientName) {
        String clientKey = "Client " + clientName;
        ArrayList<Long> list = new ArrayList<Long>();

        for (String s_mem : jedis.lrange(clientKey + ":Memory", 0, lim)) {
            list.add(Long.parseLong(s_mem));
        }

        return list;
    }

    public Long getCurrentMemoryUsage(String clientName) {
        String clientKey = "Client " + clientName;
        return Long.parseLong(jedis.lindex(clientKey + ":Memory", -1));
    }

    public void addMemUsage(String clientName, Long mem) {
        String clientKey = "Client " + clientName;

        if (jedis.llen(clientKey + ":Memory") >= lim)
            jedis.lpop(clientKey + ":Memory");

        jedis.rpush(clientKey + ":Memory", mem.toString());
    }

    public String getIP(String clientName) {
        String clientKey = "Client " + clientName;
        return jedis.get(clientKey + ":IP");
    }

    public void setIP(String clientName, String ip) {
        String clientKey = "Client " + clientName;
        jedis.set(clientKey + ":IP", ip);
    }

    public String getMAC(String clientName) {
        String clientKey = "Client " + clientName;
        return jedis.get(clientKey + ":MAC");
    }

    public void setMAC(String clientName, String mac) {
        String clientKey = "Client " + clientName;
        jedis.set(clientKey + ":MAC", mac);
    }

    public String getOSName(String clientName) {
        String clientKey = "Client " + clientName;
        return jedis.get(clientKey + ":OS");
    }

    public void setOSName(String clientName, String osname) {
        String clientKey = "Client " + clientName;
        jedis.set(clientKey + ":OS", osname);
    }

    public void close() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
