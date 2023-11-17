package systemmonitor.Utilities;

import java.util.ArrayList;
import redis.clients.jedis.Jedis;

public class DataAccess {
    private String clientKey;

    Jedis jedis;
    static int lim = 100;

    public DataAccess(String clientIP) {
        this.clientKey = "Client " + clientIP;
        jedis = new Jedis("localhost", 6379);
    }

    public ArrayList<Double> getCpuUsages() {
        ArrayList<Double> list = new ArrayList<Double>();

        for (String s_cpu : jedis.lrange(clientKey + ":CPU", 0, lim)) {
            list.add(Double.parseDouble(s_cpu));
        }

        return list;
    }

    public Double getCurrentCpuUsage() {
        return Double.parseDouble(jedis.lindex("CPU", -1));
    }

    public void addCpuUsage(Double cpu) {
        if (jedis.llen(clientKey + ":CPU") >= lim)
            jedis.lpop(clientKey + ":CPU");

        jedis.rpush(clientKey + ":CPU", cpu.toString());
    }

    public ArrayList<Long> getMemoryUsages() {
        ArrayList<Long> list = new ArrayList<Long>();

        for (String s_mem : jedis.lrange(clientKey + ":Memory", 0, lim)) {
            list.add(Long.parseLong(s_mem));
        }

        return list;
    }

    public Long getCurrentMemoryUsage() {
        return Long.parseLong(jedis.lindex(clientKey + ":Memory", -1));
    }

    public void addMemUsage(Long mem) {
        if (jedis.llen(clientKey + ":Memory") >= lim)
            jedis.lpop(clientKey + ":Memory");

        jedis.rpush(clientKey + ":Memory", mem.toString());
    }

    public void close() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
