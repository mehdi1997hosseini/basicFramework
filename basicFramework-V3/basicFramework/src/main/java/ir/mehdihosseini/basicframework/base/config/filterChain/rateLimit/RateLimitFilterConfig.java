package ir.mehdihosseini.basicframework.base.config.filterChain.rateLimit;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimitFilterConfig extends OncePerRequestFilter {

    private static final Map<String, Counter> requestCount = new ConcurrentHashMap<>();

    private int limit;
    private long maxRequestPerSecond;

    public RateLimitFilterConfig(int limit, long maxRequestPerSecond) {
        this.limit = limit;
        this.maxRequestPerSecond = maxRequestPerSecond;
    }

    protected static class Counter {
        int count;
        Instant time;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String clientRemoteAddr = request.getRemoteAddr();

        if (!checkRateLimit(clientRemoteAddr))
            throw new ServletException("sending many request . wait and try again request ");

        filterChain.doFilter(request, response);
    }

    private boolean checkRateLimit(String ip) {
        Instant now = Instant.now();
        Counter clientInfo = requestCount.getOrDefault(ip, null);

        if (clientInfo == null) {
            clientInfo = new Counter();
            clientInfo.count = 1;
            clientInfo.time = now;
            requestCount.put(ip, clientInfo);
            return true;
        }

        if (clientInfo.count < limit && Duration.between(clientInfo.time, now).toSeconds() < maxRequestPerSecond) {
            clientInfo.count++;
            requestCount.put(ip, clientInfo);
            return true;
        }

        if (Duration.between(clientInfo.time, now).toSeconds() > maxRequestPerSecond) {
            clientInfo = new Counter();
            clientInfo.count = 1;
            clientInfo.time = now;
            requestCount.put(ip, clientInfo);
            return true;
        }

        return false;
    }


}
